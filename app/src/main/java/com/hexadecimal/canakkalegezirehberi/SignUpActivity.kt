package com.hexadecimal.canakkalegezirehberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        girisyapTxt_activity_signup.setOnClickListener {
            val intent = Intent (this,LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton_activity_signup.setOnClickListener {
            performRegister()
        }
    }

    private fun performRegister() {
        val email = emailEdtText_activity_signup.text.toString()
        val password = passEdtText_activity_signup.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen email ve parola kısmını doldurunuz!", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    // TODO eger kullanici giris yaparsa MyRoutesActivity sinifina gecis yap
                    val intent = Intent(this, MyRoutesActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Lütfen geçerli bir email adresi giriniz!", Toast.LENGTH_LONG).show()
                }
    }

}
