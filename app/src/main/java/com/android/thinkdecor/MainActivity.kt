package com.android.thinkdecor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.android.thinkdecor.presentation.navigation.NavGraph
import com.android.thinkdecor.presentation.navigation.Routes
import com.android.thinkdecor.ui.theme.ThinkDecorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkDecorTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    startDestination = Routes.SignIn.route
                )
            }
        }
    }
}
