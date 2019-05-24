package com.hexadecimal.canakkalegezirehberi.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.hexadecimal.canakkalegezirehberi.R
import kotlinx.android.synthetic.main.fragment_app_settings.*


// Created by Melih KOK
// kokmelih@gmail.com
// 2019-05-22 - 05:13

class FragmentAppSettings : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_app_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fbAuth = FirebaseAuth.getInstance()

        ayarlar_Cikis_Yap.setOnClickListener {
            fbAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity,"Çıkış Yaptınız", Toast.LENGTH_SHORT).show()
        }
    }

    // we use the companion object to access this class's members with only the class name
    // it is like static in java, companion object is a singleton
    companion object {
        fun newInstance() = FragmentAppSettings()
    }
}