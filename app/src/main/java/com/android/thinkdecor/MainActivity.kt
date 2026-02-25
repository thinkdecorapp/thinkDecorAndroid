package com.android.thinkdecor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.android.thinkdecor.presentation.dashboard.DashboardActivity
import com.android.thinkdecor.presentation.navigation.NavGraph
import com.android.thinkdecor.presentation.navigation.Routes
import com.android.thinkdecor.presentation.ui.theme.ThinkDecorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkDecorTheme {
                NavGraph(
                    navController = rememberNavController(),
                    startDestination = Routes.Splash.route,
                    onSignInSuccess = {
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}