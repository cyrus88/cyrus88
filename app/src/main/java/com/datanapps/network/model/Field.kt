package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Field {
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null

}