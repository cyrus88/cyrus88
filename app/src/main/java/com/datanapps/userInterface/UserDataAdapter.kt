package com.datanapps.userInterface

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.datanapps.network.model.User
import com.sph.sgnetworkdata.R
import kotlinx.android.synthetic.main.layout_user_list.view.*
import javax.inject.Inject


class UserDataAdapter @Inject constructor():
    RecyclerView.Adapter<UserDataAdapter.NetworkDataViewHolder>() {

    private var networkDataList = mutableListOf<User>()

    var onItemClick: ((recordList: User) -> Unit)? = null

    var context: Context? = null
    var layoutInflater: LayoutInflater? = null

    fun addItemList(networkDataList: MutableList<User>) {
        this.networkDataList = networkDataList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkDataViewHolder {

        if (layoutInflater == null) {
            context = parent.context
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val itemView = layoutInflater!!
            .inflate(R.layout.layout_user_list, parent, false)
        return NetworkDataViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NetworkDataViewHolder, position: Int) {
        holder.bindView(networkDataList.get(position))
    }

    override fun getItemCount(): Int {
        return networkDataList.size
    }


    inner class NetworkDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(user: User) {
            itemView.tvLayoutName.text = user.firstName

            Glide.with(context!!).load(user.image).into(itemView.ivUserImage);

            itemView.setOnClickListener {
                onItemClick?.invoke(user)
            }
        }


    }
}
