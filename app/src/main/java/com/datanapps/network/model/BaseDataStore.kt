package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseDataStore {
    @SerializedName("help")
    @Expose
    var help: String? = null
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("result")
    @Expose
    var result: Result? = null

}