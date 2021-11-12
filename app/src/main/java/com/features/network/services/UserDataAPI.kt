package com.features.network.services

import com.features.network.model.BaseUsers
import retrofit2.Call
import retrofit2.http.GET


interface UserDataAPI{
    /**
     * Get the list of the anime from the API
     */
    @GET("v1")
    fun getNetworkDataList(): Call<BaseUsers>

}
