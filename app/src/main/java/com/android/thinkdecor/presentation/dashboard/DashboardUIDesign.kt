package com.android.thinkdecor.presentation.dashboard

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.screens.ExploreScreen
import com.android.thinkdecor.presentation.screens.HomeScreen
import com.android.thinkdecor.presentation.screens.InboxScreen
import com.android.thinkdecor.presentation.screens.ProfileScreen
import com.android.thinkdecor.presentation.screens.ScanScreen

@Composable
fun DashboardUIDesign() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }, containerColor = Color(0xFFF9F9F9) // Light background as seen in screenshot
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> ExploreScreen()
                2 -> ScanScreen()
                3 -> InboxScreen()
                4 -> ProfileScreen()
                else -> HomeScreen()
            }
        }
    }
}

@Composable
fun CustomBottomNavigation(
    selectedTab: Int, onTabSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp) // Total height to accommodate the FAB overflow
            .background(Color.Transparent)
    ) {
        // Main Navigation Bar Container
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Home
                BottomNavItem(
                    icon = Icons.Outlined.Home,
                    label = "Home",
                    isSelected = selectedTab == 0,
                    onClick = { onTabSelected(0) })

                // Explore
                BottomNavItem(
                    icon = Icons.Outlined.Home,
                    label = "Explore",
                    isSelected = selectedTab == 1,
                    onClick = { onTabSelected(1) })

                // Space for the central FAB
                Spacer(modifier = Modifier.width(60.dp))

                // Inbox
                BottomNavItem(
                    icon = Icons.Outlined.FavoriteBorder,
                    label = "Inbox",
                    isSelected = selectedTab == 3,
                    onClick = { onTabSelected(3) })

                // Profile
                BottomNavItem(
                    icon = Icons.Outlined.Person,
                    label = "Profile",
                    isSelected = selectedTab == 4,
                    onClick = { onTabSelected(4) })
            }
        }

        // Floating Center Button with scan icon
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(CircleShape)
                .clickable { onTabSelected(2) }, contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "Scan",
                modifier = Modifier.size(140.dp),
                tint = Color.Unspecified // Use original colors from the drawable
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit
) {
    val contentColor = if (isSelected) Color(0xFF056D5E) else Color(0xFF9E9E9E)

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = contentColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}