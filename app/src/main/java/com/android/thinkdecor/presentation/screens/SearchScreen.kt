package com.android.thinkdecor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.ui.theme.Poppins

@Composable
fun SearchScreen(
    onBackClick: () -> Unit = {}, onCartClick: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Automatically request focus and show keyboard when screen opens
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top App Bar with Search
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            // Search Input Field
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(
                        text = "Try \"Table\", \"Sofa\", \"Chair\"",
                        color = Color.Gray,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF056D5E), // This is the border color in M3
                    unfocusedIndicatorColor = Color(0xFF056D5E),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            IconButton(onClick = onCartClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_shopping_bag),
                    contentDescription = "Cart",
                    tint = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            // Recent Search Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent search",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Clear", fontSize = 14.sp, color = Color(0xFF056D5E), // Green color
                    modifier = Modifier.clickable { /* Clear recent searches */ })
            }

            // Recent Search Items
            RecentSearchItem("Decorative light", onRemove = {})
            RecentSearchItem("Storage", onRemove = {})
            RecentSearchItem("Kitchen set", onRemove = {})

            Spacer(modifier = Modifier.height(24.dp))

            // Recommended for you Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recommended for you",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* See all */ }) {
                    Text(
                        text = "See all", fontSize = 14.sp, color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "See all",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
                    )
                }
            }

            // Product Cards (Horizontal Scroll)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProductCard("Table Lamp", "Gatsby", "£12")
                ProductCard("Pillow", "Kentangs", "£12")
                ProductCard("Wooden Chair", "Catylax", "£12")
            }
        }
    }
}

@Composable
fun RecentSearchItem(
    text: String, onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Clock",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text, fontSize = 14.sp, color = Color.Black
            )
        }
        IconButton(
            onClick = onRemove, modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ProductCard(
    name: String, brand: String, price: String
) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(
                color = Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        // Placeholder for product image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color.LightGray, shape = RoundedCornerShape(8.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Image", color = Color.Gray, fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
        )
        Text(
            text = brand, fontSize = 12.sp, color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = price, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black
        )
    }
}
