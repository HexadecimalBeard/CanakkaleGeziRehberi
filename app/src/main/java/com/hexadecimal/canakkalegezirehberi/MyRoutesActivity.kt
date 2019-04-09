package com.hexadecimal.canakkalegezirehberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MyRoutesActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_routes)
    }

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
       if (doubleBackToExitPressedOnce) {
           super.onBackPressed()
           fbAuth.signOut()
           //val intent = Intent(this, LoginActivity::class.java)
           //startActivity(intent)
       }
       this.doubleBackToExitPressedOnce = true
       Toast.makeText(this, "Çıkış yapmak için iki kere geri tuşuna basmalısın!", Toast.LENGTH_SHORT).show()
       Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}
