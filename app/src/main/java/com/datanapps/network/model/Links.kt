package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links {
    @SerializedName("start")
    @Expose
    var start: String? = null
    @SerializedName("next")
    @Expose
    var next: String? = null

}