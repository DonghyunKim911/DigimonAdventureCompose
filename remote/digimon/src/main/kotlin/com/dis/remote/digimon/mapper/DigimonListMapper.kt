package com.dis.remote.digimon.mapper

import com.dis.data.model.DigimonListData
import com.dis.remote.digimon.model.DigimonListResponse

fun DigimonListResponse.toData(): DigimonListData =
    DigimonListData(
        contents = content?.map { it?.toData() },
        pageable = pageable?.toData(),
    )
