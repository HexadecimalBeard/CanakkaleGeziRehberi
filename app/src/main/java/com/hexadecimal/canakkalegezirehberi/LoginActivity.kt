package com.hexadecimal.canakkalegezirehberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        kayitOlTxt_activity_login.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton_activity_login.setOnClickListener {
            performSignIn()
        }
    }

    private fun performSignIn() {
        val email = emailEdtText_activity_login.text.toString()
        val password = passEdtText_activity_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen email ve parola kısmını doldurunuz!", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    val intent = Intent(this, MyRoutesActivity::class.java)
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