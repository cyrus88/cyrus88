package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseUsers {

    @SerializedName("userlist")
    @Expose
    var userlist: MutableList<User>? = null
    @SerializedName("totalrecords")
    @Expose
    var totalrecords: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}
