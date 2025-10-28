package com.dis.remote.digimon.mapper

import com.dis.data.model.FieldData
import com.dis.remote.digimon.model.FieldResponse

fun FieldResponse.toData(): FieldData =
    FieldData(
        field = field,
        id = id,
        image = image,
    )
