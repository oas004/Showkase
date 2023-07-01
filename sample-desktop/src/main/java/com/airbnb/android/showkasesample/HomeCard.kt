package com.airbnb.android.showkasesample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.android.showkase.annotation.ShowkaseComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCard() {
    ElevatedCard {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f).height(300.dp).padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "This is on the apps frontpage",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.size(12.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "This is on the apps frontpage. Here you can explain more about the application if you want. Cards are really cool that way!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )
        }
    }
}

@ShowkaseComposable(name = "HomeCard")
@Composable
fun HomeCardPreview() {
    MaterialTheme {
        HomeCard()
    }
}