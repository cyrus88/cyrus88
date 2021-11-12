package com.features.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("anime_id")
    @Expose
    var id: String? = null
    @SerializedName("anime_name")
    @Expose
    var name: String? = null
    @SerializedName("anime_img")
    @Expose
    var image: String? = null
}
