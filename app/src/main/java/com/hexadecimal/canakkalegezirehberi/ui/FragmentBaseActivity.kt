package com.hexadecimal.canakkalegezirehberi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.hexadecimal.canakkalegezirehberi.R

class FragmentBaseActivity : AppCompatActivity() {

    // bu değişkenleri by lazy ile tanımlamamın nedeni, bu fragmentlerin sadece
    // kullanıldıklarında oluşturulmasını için

    private val myRoutesFragment by lazy { FragmentMyRoutes.newInstance() }
    private val fragmentMonuments by lazy { FragmentMonuments.newInstance() }
    private val routeDetailsFragment by lazy { RouteDetailsFragment.newInstance() }
    private val fragmentSavasHakkinda by lazy { FragmentSavasHakkinda.newInstance() }
    private val fragmentAppSettings by lazy { FragmentAppSettings.newInstance() }

    var fbAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_base_main)


        val newRouteFragment =
            FragmentMyRoutes.newInstance()
        openFragment(newRouteFragment)

        // uygulamamda sekmeleri göstermek adına bottom navigation view yapısını kullandım
        // tüm fragmentlere ekranın alt kısmından erişilebilir, bunu tercih etmemin nedeni
        //  uygulama içerisinde sürekli ulaşılan 3 ile 5 arasında fragment bulunduğunda
        //  bu yapının daha iyi gir kullanıcı deneyimi sağlayacak olması

        // declared the bottom navigation bar
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigationBar)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationBarItemSelectedListener)

    }

    // kullanıcı fragment'lerden birini seçtiğinde buradaki when yapısı çalışıyor
    // ve seçilen fragment'in ID değerine göre o fragment'i oluşturuyor

    private val onNavigationBarItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.bottomnav_Myroutes -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, myRoutesFragment).commit()

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_AboutVictory -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentSavasHakkinda).commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Monuments -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentMonuments).commit()

                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottomnav_Settings -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentAppSettings).commit()
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


//    // to sign out when you double tab to back button
//    // TODO use this on sign out section, it is functional in here for now!!!
//    private var doubleBackToExitPressedOnce = false
//
//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            fbAuth.signOut()
//            //val intent = Intent(this, LoginActivity::class.java)
//            //startActivity(intent)
//        }
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(
//            this,
//            "Çıkış yapmak için iki kere geri tuşuna basmalısın!",
//            Toast.LENGTH_SHORT
//        ).show()
//        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
//    }
//

}
