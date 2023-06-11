package com.airbnb.android.showkasesample

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.android.showkase.annotation.ShowkaseComposable

@ShowkaseComposable(name = "Some text test", group = "Text")
@Composable
fun CursiveTextComponent() {
    Card {
        Text(
            text = "Some text", modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )
        )
    }
}

