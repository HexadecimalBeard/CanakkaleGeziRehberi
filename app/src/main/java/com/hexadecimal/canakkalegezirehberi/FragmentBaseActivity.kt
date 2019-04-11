package com.hexadecimal.canakkalegezirehberi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class FragmentBaseActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_base_main)

        val newRouteFragment = FragmentMyRoutes.newInstance()
        openFragment(newRouteFragment)

        // declared the bottom navigation bar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigationBar)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationBarItemSelectedListener)

    }

    private val onNavigationBarItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.bottomnav_Myroutes -> {
                    val myRoutesFragment = FragmentMyRoutes.newInstance()
                    openFragment(myRoutesFragment)

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_AboutVictory -> {
                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Monuments -> {
                    val monumentsFragment = FragmentMonuments.newInstance()
                    openFragment(monumentsFragment)

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Settings -> {
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    private fun openFragment(fragment: Fragment) {

        // to add the fragment into the UI
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // to sign out when you double tab to back button
    // TODO use this on sign out section, it is functional in here for now!!!
    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            fbAuth.signOut()
            //val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            "Çıkış yapmak için iki kere geri tuşuna basmalısın!",
            Toast.LENGTH_SHORT
        ).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}
