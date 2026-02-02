package com.android.thinkdecor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.ui.theme.Poppins
import com.android.thinkdecor.presentation.ui.theme.SearchGreen

@Composable
fun PopularSection(
    onSeeAllClick: () -> Unit = {},
    onProductClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // "Popular" header with "See all"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(onClick = onSeeAllClick)
            ) {
                Text(
                    text = "See all",
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "See all",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // 2x2 grid of product cards
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // First row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProductCard(
                    modifier = Modifier.weight(1f),
                    title = "Leatherette Sofa",
                    price = "$30.99",
                    onClick = { onProductClick("Leatherette Sofa") }
                )
                ProductCard(
                    modifier = Modifier.weight(1f),
                    title = "Modern Sofa",
                    price = "$45.99",
                    onClick = { onProductClick("Modern Sofa") }
                )
            }
            // Second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProductCard(
                    modifier = Modifier.weight(1f),
                    title = "Leatherette Sofa",
                    price = "$30.99",
                    onClick = { onProductClick("Leatherette Sofa") }
                )
                ProductCard(
                    modifier = Modifier.weight(1f),
                    title = "Modern Sofa",
                    price = "$45.99",
                    onClick = { onProductClick("Modern Sofa") }
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    title: String,
    price: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Placeholder image box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Image\nPlaceholder",
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Product title
            Text(
                text = title,
                fontFamily = Poppins,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Price and navigation icon row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = price,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SearchGreen
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "View details",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
