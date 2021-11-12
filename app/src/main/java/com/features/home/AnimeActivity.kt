package com.features.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.sph.sgnetworkdata.R
import com.features.network.utils.NetworkStatus
import com.features.network.model.BaseUsers
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_data.*
import javax.inject.Inject


class AnimeActivity : AppCompatActivity() {

    @Inject
    lateinit var networkDataActivityViewModel: AnimeActivityViewModel

    @Inject
    lateinit var networkDataAdapter: AnimeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)
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
        networkDataAdapter.onItemClick = { user ->

            // do something with your item
           val bottomSheetFragment =
                AnimeDetailFragment(user)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }

    private fun callApiAndUpdateUI() {
        networkDataProgressBar.visibility = View.VISIBLE
        networkDataActivityViewModel.networkLiveData.observe(this,
            Observer<BaseUsers> { baseDataStore ->
                networkDataProgressBar.visibility = View.GONE

                if (baseDataStore != null) {
                    networkDataAdapter.addItemList(baseDataStore.userlist!!)
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
    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
            .show()
    }

}