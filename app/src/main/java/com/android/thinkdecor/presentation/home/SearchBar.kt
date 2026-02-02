package com.android.thinkdecor.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.ui.theme.PlaceholderGray
import com.android.thinkdecor.presentation.ui.theme.SearchGreen

@Composable
fun SearchBar(
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search input area (white, rounded)
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(onClick = onSearchClick)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = SearchGreen,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 12.dp)
                )
                Text(
                    text = "Chair, Lamp, Desk, Table etc",
                    color = PlaceholderGray,
                    fontSize = 14.sp
                )
            }
        }

        // Filter button: dark green rounded square with white icon
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(48.dp)
                .background(
                    color = SearchGreen,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(onClick = onFilterClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}


