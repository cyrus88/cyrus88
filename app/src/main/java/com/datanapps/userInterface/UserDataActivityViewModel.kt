package com.datanapps.userInterface

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datanapps.di.modules.NetworkModule
import com.datanapps.network.NetworkStatus
import com.datanapps.network.model.BaseUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UserDataActivityViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var networkModule: NetworkModule

    val networkLiveData = MutableLiveData<BaseUsers>()
    val statusLiveData = MutableLiveData<NetworkStatus>()


    fun fetchDataDetail() {

        val networkDataAPI =
            networkModule.provideUserDataApi(networkModule.provideRetrofitInterface())

        val networkDataAPICall =
            networkDataAPI.getNetworkDataList()

        networkDataAPICall.enqueue(object : Callback<BaseUsers> {

            override fun onResponse(
                call: Call<BaseUsers>?,
                response: Response<BaseUsers>?
            ) {
                if(response!!.body()!=null)
                     networkLiveData.value = response?.body()
                else{
                    statusLiveData.value = NetworkStatus.FAIL
                }
            }

            override fun onFailure(call: Call<BaseUsers>?, t: Throwable?) {
                statusLiveData.value = NetworkStatus.FAIL
            }
        })

        if(!networkModule.isConnected) {
            // off line
            statusLiveData.value = NetworkStatus.INTERNET_CONNECTION
        }

    }





}