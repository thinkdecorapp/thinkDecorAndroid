package com.android.thinkdecor.presentation.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.android.thinkdecor.presentation.cart.CartActivity
import com.android.thinkdecor.presentation.home.FurnitureEssentialsSection
import com.android.thinkdecor.presentation.home.HomeScreenDetails
import com.android.thinkdecor.presentation.home.SearchBar

@Composable
fun HomeScreen() {
    var showSearchScreen by remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (showSearchScreen) {
        SearchScreen(
            onBackClick = { showSearchScreen = false },
            onCartClick = { /* Handle cart click */ }
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenDetails(onCartClick = {
                val navigate = Intent(context, CartActivity::class.java)
                context.startActivity(navigate)
            })
            SearchBar(
                onSearchClick = { showSearchScreen = true }
            )
            FurnitureEssentialsSection()
            PopularSection()
        }
    }
}
