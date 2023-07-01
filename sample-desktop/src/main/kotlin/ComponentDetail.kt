import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
internal fun ComponentDetail(selectedComponentViewState: ComponentViewState) {
    Column(
        modifier = Modifier.fillMaxHeight().width(700.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val componentRef =
            selectedComponentViewState as ComponentViewState.SingleComponent

        val darkModeSelected = remember { mutableStateOf(false) }

        ConfigurationRadioButton(
            title = "Dark Mode Enabled",
            selected = darkModeSelected.value,
            onClick = { darkModeSelected.value = it }
        )

        val rtlModeSelected = remember { mutableStateOf(false) }

        ConfigurationRadioButton(
            title = "Rtl Configuration Enabled",
            selected = rtlModeSelected.value,
            onClick = { rtlModeSelected.value = it }
        )
        val selectedFontScale = remember { mutableStateOf(1f) }
        val selectedDisplayScale = remember { mutableStateOf(1f) }
        val density = LocalDensity.current
        val customDensity = Density(
            fontScale = density.fontScale * selectedFontScale.value,
            density = density.density * selectedDisplayScale.value
        )

        Text("Font-Scale")
        Slider(
            modifier = Modifier.padding(horizontal = 8.dp),
            value = selectedFontScale.value,
            onValueChange = {
                selectedFontScale.value = it
            },
            valueRange = 1f.rangeTo(2f)
        )

        Text("Display-Scale")
        Slider(
            modifier = Modifier.padding(horizontal = 8.dp),
            value = selectedDisplayScale.value,
            onValueChange = {
                selectedDisplayScale.value = it
            },
            valueRange = 1f.rangeTo(2f)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(
                LocalDensity provides customDensity,
                LocalLayoutDirection provides if (rtlModeSelected.value) LayoutDirection.Rtl else LayoutDirection.Ltr,
            ) {
                componentRef.component.component.invoke()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfigurationRadioButton(
    title: String,
    selected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Checkbox(
            checked = selected,
            onCheckedChange = onClick,
        )
        Text(title)
    }
}
