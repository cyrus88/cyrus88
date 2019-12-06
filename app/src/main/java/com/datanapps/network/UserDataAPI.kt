package com.datanapps.network

import com.datanapps.network.model.BaseUsers
import retrofit2.Call
import retrofit2.http.GET


interface UserDataAPI{
    /**
     * Get the list of the pots from the API
     */
//https://datanapps.com/DNARestAPIs/getUserLists
    @GET("DNARestAPIs/getUserLists")
    fun getNetworkDataList(): Call<BaseUsers>

}
