package com.hexadecimal.canakkalegezirehberi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hexadecimal.canakkalegezirehberi.R
import kotlinx.android.synthetic.main.bottom_dialog_cart.*


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-17 - 05:35


class BottomCartMenuFragment : BottomDialogFragment() {

    private val firestoreDb: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

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

        var fiyat: String = ""

        sepetSatinAl.setOnClickListener {
            fragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, CreateNewRouteFragment()).commit()

            dialog!!.dismiss()
        }

        firestoreDb.collection("Kullanıcılar")
            .document("${firebaseAuth.uid}")
            .get().addOnSuccessListener {document ->
                if (document != null) {
                    fiyat = document.data?.get("anitId").toString()

                    fiyatToplam?.text = (fiyat.toLong() * 4).toString()
                }
            }

    }

    companion object {
        fun newInstance() = BottomCartMenuFragment()
    }

}