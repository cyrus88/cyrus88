package com.datanapps.userInterface

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.sph.sgnetworkdata.R
import com.datanapps.network.NetworkStatus
import com.datanapps.network.model.BaseDataStore
import com.datanapps.network.model.Record
import com.datanapps.utils.Constants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_network_data.*
import javax.inject.Inject


class NetworkDataActivity : AppCompatActivity() {

    @Inject
    lateinit var networkDataActivityViewModel: NetworkDataActivityViewModel

    @Inject
    lateinit var networkDataAdapter: NetworkDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_data)
        prepareView()
        callApiAndUpdateUI()
    }

    private fun prepareView() {
        recycleViewNetworkData.itemAnimator = DefaultItemAnimator()
        recycleViewNetworkData.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        this.recycleViewNetworkData.adapter = networkDataAdapter
        networkDataAdapter.onItemClick = { recordList ->

            // do something with your item
            Log.d("TAG", recordList!!.size.toString())
            val bottomSheetFragment =
                NetworkDataDetailFragment(recordList)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }

    private fun callApiAndUpdateUI() {
        networkDataProgressBar.visibility = View.VISIBLE
        networkDataActivityViewModel.networkLiveData.observe(this,
            Observer<BaseDataStore> { baseDataStore ->
                networkDataProgressBar.visibility = View.GONE

                if (baseDataStore != null && baseDataStore.result!!.records != null) {
                    networkDataAdapter.addItemList(getYearNetworkData(baseDataStore.result!!.records!!))
                }
                else{
                    networkDataActivityViewModel.statusLiveData.value = NetworkStatus.FAIL
                }
            })


        // status of actions
        networkDataActivityViewModel.statusLiveData.observe(this,
            Observer<NetworkStatus> { status ->
                networkDataProgressBar.visibility = View.GONE
                onNetWorkStateChanged(status)
            })
        networkDataActivityViewModel.fetchDataDetail()
    }


    private fun onNetWorkStateChanged(state: NetworkStatus) = when (state) {
        NetworkStatus.INTERNET_CONNECTION -> showSnackBar(getString(R.string.msg_no_internet_network))
        NetworkStatus.SERVER_ERROR -> showSnackBar(getString(R.string.msg_server_error))
        NetworkStatus.FAIL -> showSnackBar(getString(R.string.msg_something_went_wrong))
        NetworkStatus.NO_RECORDS -> showSnackBar(getString(R.string.msg_no_records))

        else -> showSnackBar(getString(R.string.msg_unknown))
    }

    private fun getYearNetworkData(recordList: List<Record>): MutableMap<String, MutableList<Record>> {


            val netWorkMap = mutableMapOf<String, MutableList<Record>>()

            recordList.forEach { record ->
                val year = record.quarter!!.split("-")[0]
                if (year.toInt() >= Constants.START_YEAR) {
                    var yearRecordList: MutableList<Record> = mutableListOf()
                    if (netWorkMap.containsKey(year)) {
                        yearRecordList = netWorkMap.get(year)!!
                        yearRecordList.add(element = record)
                    } else {
                        yearRecordList.add(element = record)
                    }
                    netWorkMap.put(year, yearRecordList)
                }
            }


        if(recordList.isEmpty() || netWorkMap.isEmpty()){
            networkDataActivityViewModel.statusLiveData.value = NetworkStatus.NO_RECORDS
        }



        return netWorkMap

    }


    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
            .show()
    }

}