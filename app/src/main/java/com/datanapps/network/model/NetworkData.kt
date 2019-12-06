package com.datanapps.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkData(
    val totalData: String = "",
    val q1: String = "",
    val q2: String = "",
    val q3: String = "",
    val q4: String = "",
    val year: Int = 0,
    val isDownVsPrevious: Int = 0
) : Parcelable