package com.android.thinkdecor.presentation.cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.thinkdecor.presentation.ui.theme.ThinkDecorTheme

class CartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cartItems = remember { mutableStateListOf<CartItem>(
                CartItem(id = "1", name = "Product 1", color = "Silver", price = 20.0, imageUrl = "url1", quantity = 1),
                CartItem(id = "2", name = "Product 2", color = "Silver", price = 30.0, imageUrl = "url2", quantity = 2)
            ) }
            ThinkDecorTheme {
                CartUIDesign(
                    cartItems = cartItems,
                    onStartShoppingClick = {
                        // Navigate to shopping screen or product list
                    },
                    onQuantityChange = { item, newQuantity ->
                        // Update the quantity for this item in the cart
                        val index = cartItems.indexOf(item)
                        if (index != -1) {
                            cartItems[index] = item.copy(quantity = newQuantity.coerceAtLeast(0)) // Prevent negative quantities
                        }
                    },
                    onCheckoutClick = {
                        // Navigate to the checkout screen
                    },
                    onBackClick = {
                        onBackPressed()
                    }
                )
            }
        }
    }
}