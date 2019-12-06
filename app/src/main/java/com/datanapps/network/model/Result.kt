package com.datanapps.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("resource_id")
    @Expose
    var resourceId: String? = null
    @SerializedName("fields")
    @Expose
    var fields: List<Field>? = null
    @SerializedName("records")
    @Expose
    var records: List<Record>? = null
    @SerializedName("_links")
    @Expose
    var links: Links? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

}