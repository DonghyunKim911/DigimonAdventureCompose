package com.dis.feature.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Locale

class TtsController(
    private val context: Context,
    private val onSpeakingChanged: (Boolean) -> Unit = {},
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private var tts: TextToSpeech? = null
    private var released = false

    // 최근 설정(옵션)
    private var currentLocale: Locale = Locale.KOREAN
    private var currentRate: Float = 1.0f
    private var currentPitch: Float = 1.0f

    fun speak(
        text: String,
        locale: Locale = Locale.KOREAN,
        rate: Float = 1.0f,
        pitch: Float = 1.0f,
    ) {
        if (released) return
        currentLocale = locale
        currentRate = rate
        currentPitch = pitch

        if (tts == null) {
            tts =
                TextToSpeech(context) { status ->
                    if (status == TextToSpeech.SUCCESS && !released) {
                        setupTts(locale, rate, pitch)
                        internalSpeak(text)
                    }
                }
        } else {
            if (tts?.language != locale) {
                tts?.language = locale
            }
            tts?.setSpeechRate(rate)
            tts?.setPitch(pitch)
            internalSpeak(text)
        }
    }

    fun stop() {
        tts?.stop()
        notifySpeaking(false)
    }

    fun release() {
        if (released) return
        released = true
        scope.cancel()

        val ref = tts
        try {
            ref?.stop()
            ref?.shutdown()
        } finally {
            tts = null
        }
    }

    private fun setupTts(
        locale: Locale,
        rate: Float,
        pitch: Float,
    ) {
        tts?.apply {
            language = locale
            setSpeechRate(rate)
            setPitch(pitch)
            setOnUtteranceProgressListener(
                object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        if (released || !scope.isActive) return
                        scope.launch { notifySpeaking(true) }
                    }

                    override fun onDone(utteranceId: String?) {
                        if (released || !scope.isActive) return
                        scope.launch {
                            if (tts?.isSpeaking != true) notifySpeaking(false)
                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        if (released || !scope.isActive) return
                        scope.launch { notifySpeaking(false) }
                    }
                },
            )
        }
    }

    private fun internalSpeak(text: String) {
        val parts = splitForTts(text)
        parts.forEachIndexed { i, part ->
            val queue = if (i == 0) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD
            tts?.speak(part, queue, /* params */ null, /* utteranceId */ "utt-$i")
        }
    }

    private fun notifySpeaking(speaking: Boolean) {
        onSpeakingChanged(speaking)
    }

    private companion object {
        private const val TTS_MAX = 3800
        private val sentenceRegex =
            Regex("(?<=[.!?\\u3002\\uFF01\\uFF1F\\n])") // Sentence-ending or newline boundaries

        /** For very long text: safely split it into sentence-sized chunks */
        fun splitForTts(text: String): List<String> {
            if (text.length <= TTS_MAX) return listOf(text)
            val sentences = text.split(sentenceRegex)
            val out = mutableListOf<String>()
            var buf = StringBuilder()
            for (s in sentences) {
                if (buf.length + s.length > TTS_MAX) {
                    out += buf.toString()
                    buf = StringBuilder()
                }
                buf.append(s)
            }
            if (buf.isNotEmpty()) out += buf.toString()
            return out
        }
    }
}
