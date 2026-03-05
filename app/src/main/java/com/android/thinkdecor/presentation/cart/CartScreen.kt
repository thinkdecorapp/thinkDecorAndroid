package com.android.thinkdecor.presentation.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
fun CartScreen(
    onBackClick: () -> Unit
) {

    val cartItems = remember {
        mutableStateListOf(
            CartItem("1","Product 1","Silver",20.0,"url1",1),
            CartItem("2","Product 2","Silver",30.0,"url2",2)
        )
    }

    CartUIDesign(
        cartItems = cartItems,
        onStartShoppingClick = {},
        onQuantityChange = { item, newQuantity ->
            val index = cartItems.indexOf(item)

            if (index != -1) {
                cartItems[index] =
                    item.copy(quantity = newQuantity.coerceAtLeast(0))
            }
        },
        onCheckoutClick = {},
        onBackClick = onBackClick
    )
}