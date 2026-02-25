package com.android.thinkdecor.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.dashboard.CustomBottomNavigation
import com.android.thinkdecor.presentation.ui.theme.GrayLight
import com.android.thinkdecor.presentation.ui.theme.Poppins
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen
import com.android.thinkdecor.presentation.ui.theme.SearchGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartUIDesign(cartItems: List<CartItem>, onStartShoppingClick: () -> Unit, onQuantityChange: (CartItem, Int) -> Unit, onCheckoutClick: () -> Unit,onBackClick: () -> Unit) {

    val title = if(cartItems.isEmpty()) "Cart" else "Cart ("+cartItems.size+")"
    Scaffold(
        containerColor = Color(0xFFF9F9F9),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontFamily = Poppins,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                modifier = Modifier.shadow(4.dp)
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (cartItems.isEmpty()) {
                EmptyCartScreen(onStartShoppingClick)
            } else {
                CartWithItems(
                    cartItems = cartItems,
                    onQuantityChange = onQuantityChange,
                    onCheckoutClick = onCheckoutClick
                )
            }
        }
    }
}

@Composable
fun EmptyCartScreen(onStartShoppingClick: () -> Unit) {
    Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 30.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.empty_cart)
                .crossfade(true)
                .build(),
            contentDescription = "empty_cart",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Your cart is empty",
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Looks like you haven’t added anything\nto your cart yet",
            fontFamily = Poppins,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onStartShoppingClick,
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryGreen,   // background color
                contentColor = Color.White           // text color
            )
        ) {
            Text(text = "Start Shopping")
        }
    }
}

@Composable
fun CartWithItems(cartItems: List<CartItem>, onQuantityChange: (CartItem, Int) -> Unit, onCheckoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartItems) { cartItem ->
                CartItemView(cartItem, onQuantityChange)
            }
        }
        val totalPrice = cartItems.sumOf { it.price * it.quantity }
        CartSummaryCard(totalItems = cartItems.size, totalAmount = totalPrice.toString(), onSendRequestClick = {})
    }
}

@Composable
fun CartSummaryCard(
    totalItems: Int,
    totalAmount: String,
    onSendRequestClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // LEFT SIDE (Texts)
            Column {
                Text(
                    text = "Total ($totalItems products)",
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "$ $totalAmount",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            // RIGHT SIDE (Button)
            Button(
                onClick = onSendRequestClick,
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0A5C50),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Send req to buyers",
                    fontFamily = Poppins,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Composable
fun CartItemView(cartItem: CartItem, onQuantityChange: (CartItem, Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartItem.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher),
            error = painterResource(R.drawable.ic_launcher),
            contentDescription = cartItem.name,
            modifier = Modifier.size(64.dp)
        )
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Top) {
            Text(text = cartItem.name,fontFamily = Poppins,color = Color.Black,fontSize = 14.sp)
            Text(text = cartItem.color, fontFamily = Poppins,color = Color.Gray,fontSize = 12.sp)
            Text(
                text = "$${cartItem.price} / pcs",
                fontFamily = Poppins,
                color = Color.Black,
                fontSize = 12.sp,
            )
        }
        QuantitySelector(cartItem, onQuantityChange)
    }
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = GrayLight)
}

@Composable
fun QuantitySelector(cartItem: CartItem, onQuantityChange: (CartItem, Int) -> Unit) {
    Column( horizontalAlignment = Alignment.End) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { onQuantityChange(cartItem, cartItem.quantity - 1) }) {
                Icon(painter = painterResource(id = R.drawable.ic_cart_minus), contentDescription = "Decrease quantity")
            }
            Text(text = "${cartItem.quantity}",modifier = Modifier.width(10.dp), fontFamily = Poppins, fontSize = 18.sp)
            IconButton(onClick = { onQuantityChange(cartItem, cartItem.quantity + 1) }) {
                Icon(painter = painterResource(id = R.drawable.ic_cart_add), contentDescription = "Increase quantity")
            }
        }
        var totalItemPrice = cartItem.quantity*cartItem.price
        Spacer(Modifier.weight(1f))
        Text(
            text = "Total $$totalItemPrice",
            fontFamily = Poppins,
            color = Color.Black,
            fontSize = 12.sp,

        )
    }
}

data class CartItem(
    val id: String,
    val name: String,
    val color: String,
    val price: Double,
    val imageUrl: String,
    var quantity: Int
)