package com.hexadecimal.canakkalegezirehberi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.hexadecimal.canakkalegezirehberi.R
import com.hexadecimal.canakkalegezirehberi.RoomDB.MonumentsDB
import com.hexadecimal.canakkalegezirehberi.dao.MonumentsDao

class FragmentBaseActivity : AppCompatActivity() {

    private val myRoutesFragment by lazy { FragmentMyRoutes.newInstance() }
    private val fragmentMonuments by lazy { FragmentMonuments.newInstance() }
    private val routeDetailsFragment by lazy { RouteDetailsFragment.newInstance() }

    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_base_main)

//        createDatabase()


        val newRouteFragment =
            FragmentMyRoutes.newInstance()
        openFragment(newRouteFragment)

        // declared the bottom navigation bar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigationBar)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationBarItemSelectedListener)

    }

//    fun createDatabase(){
//
//    }

    private val onNavigationBarItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.bottomnav_Myroutes -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, myRoutesFragment).commit()

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_AboutVictory -> {

                    // supportFragmentManager.beginTransaction()
                    //     .replace(R.id.fragment_container, aboutVictory).commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Monuments -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentMonuments).commit()

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Settings -> {

                    // supportFragmentManager.beginTransaction()
                    //     .replace(R.id.fragment_container, fragmentSettings).commit()
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
