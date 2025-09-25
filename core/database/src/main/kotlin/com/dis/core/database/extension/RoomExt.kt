package com.dis.core.database.extension

import androidx.room.RoomDatabase

fun <T : RoomDatabase> RoomDatabase.Builder<T>.addTypeConverters(
    vararg converters: Any
): RoomDatabase.Builder<T> {
    converters.forEach { addTypeConverter(it) }
    return this
}
