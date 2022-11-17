package com.markvtls.phonemarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.markvtls.core.navigation.NavigationActions
import dagger.hilt.android.AndroidEntryPoint


/**Application' Main Activity. */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navHostFragment: NavHostFragment? = null
    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContentView(R.layout.activity_main)


        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment?.navController

        navController?.let { NavigationActions.toMainScreen(it) }


    }


}