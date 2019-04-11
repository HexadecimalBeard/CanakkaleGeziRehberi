package com.hexadecimal.canakkalegezirehberi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // to show onboarding screen for once
        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
            .getBoolean("isFirstRun", true)

        if (isFirstRun) {
            // show onboarding screen
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
        }
        // we used shared preferences here, when the user saw the onboarding screen
        // boolean variable will change as false and user will never see the onboarding screen again
        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).apply()
        // to show onboarding screen for once

        auth = FirebaseAuth.getInstance()

        // passage to the Register Page
        kayitOlTxt_activity_login.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        // when user entered the account details, we call performSignIn method to login
        loginButton_activity_login.setOnClickListener {
            performSignIn()
        }
    }

    public override fun onStart() {
        super.onStart()
        // if user already signed in, go directly to the My Routes Page
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val intent = Intent(this, FragmentBaseActivity::class.java)
            startActivity(intent)
        } else {
            // No user is signed in
            // TODO kullanici giris yapmadiysa ne olacak, burayı düzenle!
        }
        // [END check_current_user]
    }

    private fun performSignIn() {
        val email = emailEdtText_activity_login.text.toString()
        val password = passEdtText_activity_login.text.toString()

        // if the email and password section is empty show a warning
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen email ve parola kısmını doldurunuz!", Toast.LENGTH_SHORT)
                .show()
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                val intent = Intent(this, FragmentBaseActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Giriş yaparken bir sorun yaşandı. Bilgilerinizi kontrol ediniz!",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}