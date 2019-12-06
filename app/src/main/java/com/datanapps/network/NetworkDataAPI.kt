package com.datanapps.network

import com.datanapps.network.model.BaseDataStore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkDataAPI{
    /**
     * Get the list of the pots from the API
     */
    // https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=5

    @GET("/api/action/datastore_search")
    fun getNetworkDataList(@Query("resource_id") resourceId:String, @Query("limit")  limit : String): Call<BaseDataStore>

}
