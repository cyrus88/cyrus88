package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Record {
    @SerializedName("volume_of_mobile_data")
    @Expose
    var volumeOfMobileData: String? = null
    @SerializedName("quarter")
    @Expose
    var quarter: String? = null
    @SerializedName("_id")
    @Expose
    var id: Int? = null

}