package com.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.features.network.model.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sph.sgnetworkdata.R
import kotlinx.android.synthetic.main.dialoge_user_data_detail.*

class AnimeDetailFragment(private var user: User) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.dialoge_user_data_detail, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // update value
        tvDialogeDataDetailYear.text = getDataDetail()

        ivDialogeClose.setOnClickListener{
            dismiss()
        }

    }


    private fun getDataDetail(): String {
        var userData = ""

            if (user!=null) {
                userData = " Anime Id -> " + user.id + " \n\n"+
                  " Anime Name-> " + user.name + " \n\n"
        }
        return userData
    }
}