package com.datanapps.userInterface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sph.sgnetworkdata.R
import com.datanapps.network.model.Record
import kotlinx.android.synthetic.main.dialoge_network_data_detail.*

class NetworkDataDetailFragment(private var recordList: MutableList<Record>?) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.dialoge_network_data_detail, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // update value
        tvDialogeDataDetailYear.text =
            getString(R.string.year, recordList!!.get(0).quarter!!.split("-")[0].toString())
        tvDialogeDetailDataQ1.text = getDataDetail()
        ivDialogeClose.setOnClickListener{
            dismiss()
        }

    }


    private fun getDataDetail(): String {
        var mobileData = ""
        var data = 0.0
        recordList!!.forEachIndexed{index, record ->

            if (index != 0 && data > record.volumeOfMobileData!!.toDouble()) {
                mobileData += record.quarter + " -> " + record.volumeOfMobileData + " \u25BC \n\n"
            }
            else {
                mobileData += record.quarter + " -> " + record.volumeOfMobileData + "\n\n"
            }
            data = record.volumeOfMobileData!!.toDouble()
        }
        return mobileData
    }
}