package com.hexadecimal.canakkalegezirehberi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hexadecimal.canakkalegezirehberi.R
import kotlinx.android.synthetic.main.bottom_dialog_cart.*


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-17 - 05:35


class BottomCartMenuFragment : BottomDialogFragment() {

    var selectedMonumentsList = ArrayList<Long>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottom_dialog_cart, container)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sepetSatinAl.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, CreateNewRouteFragment()).commit()

            dialog!!.dismiss()
        }

    }

    companion object {
        fun newInstance() = BottomCartMenuFragment()
    }

}