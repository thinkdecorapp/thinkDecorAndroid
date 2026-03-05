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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Explore
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.thinkdecor.R
import com.android.thinkdecor.presentation.chat.mockData.FakeConversations
import com.android.thinkdecor.presentation.chat.screens.ConversationsScreen
import com.android.thinkdecor.presentation.navigation.Routes
import com.android.thinkdecor.presentation.screens.ExploreScreen
import com.android.thinkdecor.presentation.screens.HomeScreen
import com.android.thinkdecor.presentation.screens.ProfileScreen
import com.android.thinkdecor.presentation.screens.ScanScreen

@Composable
fun DashboardUIDesign(
    parentNavController: NavHostController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController = navController)
        },
        containerColor = Color(0xFFF9F9F9)
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .navigationBarsPadding()
        ) {

            composable(Routes.Home.route) {
                HomeScreen(
                    onCartClick = {
                        parentNavController.navigate(Routes.Cart.route)
                    }
                )
            }

            composable(Routes.Explore.route) { ExploreScreen() }

            composable(Routes.Scan.route) { ScanScreen() }

            composable(Routes.Conversations.route) {
                ConversationsScreen(
                    items = FakeConversations.mockConversations,
                    onOpenChat = { conversation ->
                        parentNavController.navigate(
                            Routes.Chat.createRoute(conversation.id)
                        )
                    }
                )
            }

            composable(Routes.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun CustomBottomNavigation(
    navController: NavHostController
) {
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.Transparent)
    ) {

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

                BottomNavItem(
                    icon = Icons.Outlined.Home,
                    label = "Home",
                    isSelected = currentRoute == Routes.Home.route,
                    onClick = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )

                BottomNavItem(
                    icon = Icons.Outlined.Explore,
                    label = "Explore",
                    isSelected = currentRoute == Routes.Explore.route,
                    onClick = {
                        navController.navigate(Routes.Explore.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )

                Spacer(modifier = Modifier.width(60.dp))

                BottomNavItem(
                    icon = Icons.Outlined.ChatBubbleOutline,
                    label = "Chat",
                    isSelected = currentRoute == Routes.Conversations.route,
                    onClick = {
                        navController.navigate(Routes.Conversations.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )

                BottomNavItem(
                    icon = Icons.Outlined.Person,
                    label = "Profile",
                    isSelected = currentRoute == Routes.Profile.route,
                    onClick = {
                        navController.navigate(Routes.Profile.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clip(CircleShape)
                .clickable { navController.navigate(Routes.Scan.route) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_scan),
                contentDescription = "Scan",
                modifier = Modifier.size(140.dp),
                tint = Color.Unspecified
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