package com.android.thinkdecor.presentation.auth.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.thinkdecor.presentation.auth.components.FilledInput
import com.android.thinkdecor.presentation.auth.components.PrimaryButton
import com.android.thinkdecor.presentation.auth.components.InterestChip
import com.android.thinkdecor.presentation.navigation.AuthScaffold
import com.android.thinkdecor.presentation.ui.theme.BlackText
import com.android.thinkdecor.presentation.ui.theme.HintColor
import com.android.thinkdecor.presentation.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseInterestsScreen(
    onFinishClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val interests = listOf(
        "Dining Table", "Working Table",
        "Chairs", "Wardrobes",
        "Console Table", "Streamer",
        "Personal Development", "Sofa",
        "Side Table"
    )

    val filteredInterests = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            interests
        } else {
            interests.filter {
                it.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    var selected by remember { mutableStateOf(setOf<String>()) }

    AuthScaffold(onBackClick = onBackClick) {

        Spacer(Modifier.height(46.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose Interests",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = BlackText,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Choose the items you are interested in to\nfulfill your dream home",
                fontSize = 14.sp,
                color = HintColor
            )
        }

        Spacer(Modifier.height(20.dp))

        FilledInput(
            value = searchQuery,
            placeholder = "Search...",
            onValueChange = { searchQuery = it }
        )

        Spacer(Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            filteredInterests.chunked(2).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { item ->
                        InterestChip(
                            text = item,
                            isSelected = selected.contains(item),
                            onToggle = {
                                selected = if (selected.contains(item))
                                    selected - item
                                else
                                    selected + item
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (row.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            text = "Finish",
            onClick = onFinishClick
        )
    }
}
