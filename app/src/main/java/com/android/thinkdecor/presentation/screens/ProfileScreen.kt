package com.android.thinkdecor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.ui.theme.BlackText
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.Poppins
import com.android.thinkdecor.presentation.ui.theme.PrimaryGreen

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {},
    onDeliveryAddressClick: () -> Unit = {},
    onPaymentMethodClick: () -> Unit = {},
    onLoginSecurityClick: () -> Unit = {},
    onSettingClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        ProfileAvatar(initials = "YF")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Yus Febrian",
            fontSize = 36.sp,
            lineHeight = 40.sp,
            color = Color.Black
        )

        Text(
            text = "Edit profile",
            fontSize = 16.sp,
            color = PrimaryGreen,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onEditProfileClick() }
        )

        Spacer(modifier = Modifier.height(36.dp))

        ProfileItemRow(
            icon = Icons.Outlined.FavoriteBorder,
            title = "My wishlist",
            onClick = onWishlistClick
        )
        ProfileItemRow(
            icon = Icons.Outlined.LocalShipping,
            title = "Delivery address",
            onClick = onDeliveryAddressClick
        )
        ProfileItemRow(
            icon = Icons.Outlined.CreditCard,
            title = "Payment method",
            onClick = onPaymentMethodClick
        )

        SectionDivider()

        Text(
            text = "Setting",
            fontSize = 17.sp,
            color = BlackText,
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileItemRow(
            icon = Icons.Outlined.Shield,
            title = "Login & Security",
            onClick = onLoginSecurityClick
        )
        ProfileItemRow(
            icon = Icons.Outlined.Settings,
            title = "Setting",
            onClick = onSettingClick
        )

        SectionDivider()

        Text(
            text = "Support",
            fontSize = 17.sp,
            color = BlackText,
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileItemRow(
            icon = Icons.Outlined.HelpOutline,
            title = "Help",
            onClick = onHelpClick
        )
        ProfileItemRow(
            icon = Icons.Outlined.Language,
            title = "Language",
            trailingText = "EN",
            onClick = onLanguageClick
        )

        SectionDivider()

        Text(
            text = "Logout",
            color = Color(0xFFE10000),
            fontSize = 18.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable { onLogoutClick() }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun ProfileAvatar(initials: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .size(74.dp)
            .clip(CircleShape)
            .background(Color(0xFFECC970)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = 22.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun ProfileItemRow(
    icon: ImageVector,
    title: String,
    trailingText: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF2D3142),
            modifier = Modifier.size(23.dp)
        )

        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF2D3142),
            modifier = Modifier.padding(start = 18.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        if (trailingText != null) {
            Text(
                text = trailingText,
                fontSize = 16.sp,
                color = PrimaryGreen,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = "Open $title",
                tint = HintColor,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun SectionDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 20.dp),
        thickness = 1.dp,
        color = Color(0xFFE5E5E5)
    )
}
