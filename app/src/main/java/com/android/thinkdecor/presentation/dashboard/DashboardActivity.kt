package com.android.thinkdecor.presentation.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.android.thinkdecor.presentation.navigation.NavGraph
import com.android.thinkdecor.presentation.navigation.Routes
import com.android.thinkdecor.presentation.ui.theme.ThinkDecorTheme

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkDecorTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    startDestination = Routes.Dashboard.route
                )
            }
        }
    }
}