package com.datanapps.userInterface

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sph.sgnetworkdata.R
import com.datanapps.network.model.Record
import kotlinx.android.synthetic.main.layout_city_list.view.*
import javax.inject.Inject


class NetworkDataAdapter @Inject constructor():
    RecyclerView.Adapter<NetworkDataAdapter.NetworkDataViewHolder>() {

    private var keysSet =  arrayOf<String>()
    private var networkDataList = mutableMapOf<String, MutableList<Record>>()

    var onItemClick: ((recordList: MutableList<Record>?) -> Unit)? = null



    fun addItemList(networkDataList: MutableMap<String, MutableList<Record>>){
        keysSet = networkDataList.keys.toTypedArray()
        this.networkDataList = networkDataList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkDataViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_city_list, parent, false)
        return NetworkDataViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NetworkDataViewHolder, position: Int) {
        holder.bindView(keysSet[position], networkDataList[keysSet[position]])
    }

    override fun getItemCount(): Int {
        return networkDataList.size
    }


    inner class NetworkDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(key: String, recordList: MutableList<Record>?) {
            itemView.tvLayoutYear.text = key
            itemView.tvLayoutNetWorkData.text = calculateYearData(recordList).toString()
            itemView.ivDecreaseIndicator.visibility =   if (compareQuarterData(recordList)) View.VISIBLE else View.INVISIBLE

            itemView.ivDecreaseIndicator.setOnClickListener {
                onItemClick?.invoke(networkDataList[key])
            }
        }


        private fun calculateYearData(recordList: MutableList<Record>?): Double {

            var totatYearData = 0.0
            recordList!!.forEach { record ->
                totatYearData += record.volumeOfMobileData!!.toDouble()
            }
            return totatYearData
        }


        private fun compareQuarterData(recordList: MutableList<Record>?): Boolean {

            var data = 0.0
            var compare = false

            recordList!!.forEachIndexed({ index, record ->

                if (index != 0 && data > record.volumeOfMobileData!!.toDouble()) {
                    compare = true
                    return true
                }
                data = record.volumeOfMobileData!!.toDouble()
            })

            return compare


        }


    }
}
