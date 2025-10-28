package com.dis.remote.digimon.mapper

import com.dis.data.model.AttributeData
import com.dis.remote.digimon.model.AttributeResponse

fun AttributeResponse.toData(): AttributeData =
    AttributeData(
        attribute = attribute,
        id = id,
    )
