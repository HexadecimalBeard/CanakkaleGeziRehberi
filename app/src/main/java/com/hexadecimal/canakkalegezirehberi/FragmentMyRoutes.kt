package com.hexadecimal.canakkalegezirehberi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my_routes.*
import java.util.*
import kotlin.concurrent.schedule


// Created by Melih KOK
// kokmelih@gmail.com
// 11.04.2019 - 16:11

class FragmentMyRoutes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_routes, container, false)
    }

    // we use the companion object to access this class's members with only the class name
    // it is like static in java, companion object is a singleton
    companion object {
        fun newInstance() = FragmentMyRoutes()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        yeniRotaOlustur_fragment_myroutes.setOnClickListener {
            val newRouteFragment = CreateNewRouteFragment.newInstance()
            openFragment(newRouteFragment)
        }

        searchView_fragment_myroutes.setOnClickListener {
            // To search the given query in the My Routes Activity toolbar
            // it searches by increasing time whenever you type something
            searchView_fragment_myroutes.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                var timer = Timer()

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    timer.cancel()
                    val sleep = when (newText.length) {
                        1 -> 1000L
                        2, 3 -> 700L
                        4, 5 -> 500L
                        else -> 300L
                    }
                    timer = Timer()
                    timer.schedule(sleep) {
                        if (newText.isNullOrEmpty()) {
                            // search
                            // TODO arama kismi burada mi yapilacak?????
                        }
                    }
                    return true
                }

            })
        }
    }

    private fun openFragment(fragment: Fragment) {

        // to add the fragment into the UI
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}