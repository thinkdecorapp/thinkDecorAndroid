package com.android.thinkdecor.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.ui.theme.Poppins
import com.android.thinkdecor.presentation.ui.theme.SearchGreen
import com.android.thinkdecor.presentation.ui.theme.SelectedBorderBlue
import com.android.thinkdecor.presentation.ui.theme.TileBgGray
import com.android.thinkdecor.presentation.ui.theme.TileTitleColor

@Composable
fun FurnitureEssentialsSection(
    onSeeAllClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {}
) {
    var selectedCategory by remember { mutableStateOf("Sofa") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        // "Furniture Essentials" row with "See all"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Furniture Essentials",
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
                    color = SearchGreen
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "See all",
                    tint = SearchGreen,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        // 3 category tiles: Chair, Sofa, Desk
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FurnitureCategoryTile(
                modifier = Modifier.weight(1f),
                title = "Chair",
                imageResId = R.drawable.chair,
                isSelected = selectedCategory == "Chair",
                onClick = { selectedCategory = "Chair"; onCategoryClick("Chair") }
            )
            FurnitureCategoryTile(
                modifier = Modifier.weight(1f),
                title = "Sofa",
                imageResId = R.drawable.chair,
                isSelected = selectedCategory == "Sofa",
                onClick = { selectedCategory = "Sofa"; onCategoryClick("Sofa") }
            )
            FurnitureCategoryTile(
                modifier = Modifier.weight(1f),
                title = "Desk",
                imageResId = R.drawable.chair,
                isSelected = selectedCategory == "Desk",
                onClick = { selectedCategory = "Desk"; onCategoryClick("Desk") }
            )
        }
    }
}

@Composable
fun FurnitureCategoryTile(
    modifier: Modifier = Modifier,
    title: String,
    imageResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .background(
                color = TileBgGray,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = SelectedBorderBlue,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TileTitleColor
            )
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.White, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}