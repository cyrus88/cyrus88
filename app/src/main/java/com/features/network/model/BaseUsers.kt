package com.features.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseUsers {
    @SerializedName("data")
    @Expose
    var userlist: MutableList<User>? = null

}
