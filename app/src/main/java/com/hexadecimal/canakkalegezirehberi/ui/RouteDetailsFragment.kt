package com.hexadecimal.canakkalegezirehberi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hexadecimal.canakkalegezirehberi.R


// Created by Melih KOK
// kokmelih@gmail.com
// 10.04.2019 - 18:41

class RouteDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_route_details, container, false)

    // we use the companion object to access this class's members with only the class name
    // it is like static in java, companion object is a singleton
    companion object {
        fun newInstance(): RouteDetailsFragment =
            RouteDetailsFragment()
    }

    // to use this fragment in another class use implement code given below
    // val fragmentRouteDetails = RouteDetailsFragment.newInstance()
    // openFragment(fragmentRouteDetails)
}