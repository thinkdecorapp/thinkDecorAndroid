package com.android.thinkdecor.presentation.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.thinkdecor.presentation.ui.theme.ThinkDecorTheme

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkDecorTheme {
                //Write code
                DashboardUIDesign()
            }
        }
    }
}