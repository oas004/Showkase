import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

internal fun LazyGridScope.singleComponent(
    onSelectComponent: (component: ComponentViewState) -> Unit,
    selectedComponentViewState: ComponentViewState.SingleComponent
) {
    item {
        BackButton(
            onClick = {
                onSelectComponent(
                    ComponentViewState.Components(
                        selectedComponentViewState.belongingGroup
                    )
                )

            }
        )
    }
    // Item in default config spec
    item {
        val item = selectedComponentViewState.component
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item.component.invoke()
            Spacer(Modifier.size(12.dp))
            Text(item.componentName)
            Spacer(Modifier.size(12.dp))
            Text("Default")
        }
    }

    // Item in Ltr mode spec
    item {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            val item = selectedComponentViewState.component
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item.component.invoke()
                Spacer(Modifier.size(12.dp))
                Text(item.componentName)
                Spacer(Modifier.size(12.dp))
                Text("Rtl enabled")
            }
        }
    }

    // Item in font mode mode spec
    item {
        //TODO: Provide font maybe with like a slide or something when LocalConfiguration is possible
        CompositionLocalProvider() {
            val item = selectedComponentViewState.component
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item.component.invoke()
                Spacer(Modifier.size(12.dp))
                Text(item.componentName)
                Spacer(Modifier.size(12.dp))
                Text("Dark mode")
            }
        }
    }
    // Item in font mode mode spec
    item {
        val item = selectedComponentViewState.component
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val selectedFontScale = remember { mutableStateOf(2f) }
            val density = LocalDensity.current
            val customDensity = Density(
                fontScale = density.fontScale * selectedFontScale.value,
                density = density.density
            )
            Slider(
                modifier = Modifier.padding(horizontal = 8.dp),
                value = selectedFontScale.value,
                onValueChange = {
                    selectedFontScale.value = it
                },
                valueRange = 1f.rangeTo(2f)
            )

            CompositionLocalProvider(LocalDensity provides customDensity) {
                item.component.invoke()
                Spacer(Modifier.size(12.dp))
                Text(item.componentName)
                Spacer(Modifier.size(12.dp))
                Text("${selectedFontScale.value} Font scale")
            }
        }
    }


    // Item in display mode mode spec
    item {
        val item = selectedComponentViewState.component
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val selectedDisplayScale = remember { mutableStateOf(2f) }
            val density = LocalDensity.current
            val customDensity = Density(
                density = density.density * selectedDisplayScale.value
            )
            Slider(
                modifier = Modifier.padding(horizontal = 8.dp),
                value = selectedDisplayScale.value,
                onValueChange = {
                    selectedDisplayScale.value = it
                },
                valueRange = 1f.rangeTo(2f)
            )

            CompositionLocalProvider(LocalDensity provides customDensity) {
                item.component.invoke()
                Spacer(Modifier.size(12.dp))
                Text(item.componentName)
                Spacer(Modifier.size(12.dp))
                Text("${selectedDisplayScale.value} Font scale")
            }
        }
    }
}
