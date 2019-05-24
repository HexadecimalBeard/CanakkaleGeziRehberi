package com.hexadecimal.canakkalegezirehberi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hexadecimal.canakkalegezirehberi.R
import com.hexadecimal.canakkalegezirehberi.RoomDB.MonumentsDB
import com.hexadecimal.canakkalegezirehberi.adapter.MonumentsAdapter
import com.hexadecimal.canakkalegezirehberi.dao.MonumentsDao
import com.hexadecimal.canakkalegezirehberi.model.MonumentsEntity
import kotlinx.android.synthetic.main.fragment_create_new_route.*


// Created by Melih KOK
// kokmelih@gmail.com
// 10.04.2019 - 23:22

class CreateNewRouteFragment : Fragment() {

    private val firestoreDb: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val monumentsDB: MonumentsDB? by lazy { MonumentsDB.getInstance(context!!) }

    private val monumentsDao: MonumentsDao? by lazy { monumentsDB?.getMonumentsDao() }

    private var docRef =
        firestoreDb.collection("Kullanıcılar").document(firebaseAuth.uid.toString())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create_new_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var denemeList = ArrayList<Long>()

        with(create_New_Route_Recycler_View) {
            adapter = MonumentsAdapter { monument ->

                //getUserMonumentData()

            }
            layoutManager = LinearLayoutManager(activity!!)

        }

        val monIDs = HashMap<String, Any?>()

        val docRef =
            firestoreDb.collection("Kullanıcılar").document(firebaseAuth.uid.toString())

        docRef.get().addOnSuccessListener { document ->
            if (document != null) {

                //monIDs.add(document.get("anitID").toString().toLong())

                monIDs["anitID"] = document.get("anitID")
                Log.e("dennenenenene", "${monIDs.size}")

                //denemeList.addAll(monIDs.values)

                //monIDs.values.size
            }
        }

    }

    // we use the companion object to access this class's members with only the class name
    // it is like static in java, companion object is a singleton
    companion object {
        fun newInstance() = CreateNewRouteFragment()
    }

}
