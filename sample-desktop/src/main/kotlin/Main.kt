import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.airbnb.android.showkase.models.ShowkaseBrowserColor
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent
import com.airbnb.android.showkase.models.ShowkaseElementsMetadata
import com.airbnb.android.showkase.models.ShowkaseProvider

enum class NavItems {
    Components, Colors
}

sealed class ComponentViewState {
    object Group : ComponentViewState()

    data class Components(val belongingGroup: String) : ComponentViewState()
    data class SingleComponent(
        val component: ShowkaseBrowserComponent,
        val belongingGroup: String
    ) : ComponentViewState()
}

fun main() = application {
    val component = remember {
        getShowkaseProviderElements("com.airbnb.android.showkasesample.RootModule")
            .componentList
    }
    val colors = remember {
        getShowkaseProviderElements("com.airbnb.android.showkasesample.RootModule")
            .colorList
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Showkase for Desktop",
        state = rememberWindowState(
            width = 1200.dp, height = 800.dp, position = WindowPosition(
                Alignment.Center
            )
        ),
        resizable = true,

        ) {
        var selectedNavItem by remember { mutableStateOf(NavItems.Components) }
        var selectedComponentViewState: ComponentViewState by remember {
            mutableStateOf(
                ComponentViewState.Group
            )
        }
        MaterialTheme {
            Row(Modifier.fillMaxSize()) {
                NavigationRail {
                    ComponentNavItem(
                        selectedNavItem = selectedNavItem,
                        onClick = { selectedNavItem = NavItems.Components }
                    )
                    ColorNavItem(
                        selectedNavItem = selectedNavItem,
                        onClick = { selectedNavItem = NavItems.Colors }
                    )
                }
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    columns = GridCells.Adaptive(360.dp),
                    contentPadding = PaddingValues(40.dp)
                ) {
                    when (selectedNavItem) {
                        NavItems.Components -> {
                            componentSection(
                                selectedComponentViewState = selectedComponentViewState,
                                onSelectComponent = {
                                    selectedComponentViewState = it
                                },
                                component = component
                            )
                        }

                        NavItems.Colors -> {
                            colorSection(colors)
                        }
                    }
                }
                AnimatedVisibility(selectedComponentViewState is ComponentViewState.SingleComponent) {
                    if (selectedComponentViewState is ComponentViewState.SingleComponent) {
                        ComponentDetail(selectedComponentViewState)
                    }
                }
            }
        }
    }
}

private fun LazyGridScope.componentSection(
    selectedComponentViewState: ComponentViewState,
    onSelectComponent: (component: ComponentViewState) -> Unit,
    component: List<ShowkaseBrowserComponent>
) {
    when (selectedComponentViewState) {
        is ComponentViewState.Group -> {
            componentGroup(component = component, onSelectComponent = onSelectComponent)
        }

        is ComponentViewState.SingleComponent -> {
            singleComponent(
                onSelectComponent = onSelectComponent,
                selectedComponentViewState = selectedComponentViewState
            )
        }

        is ComponentViewState.Components -> {
            conmponentOverview(
                onSelectComponent = onSelectComponent,
                component = component,
                selectedComponentViewState = selectedComponentViewState
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
private fun LazyGridScope.colorSection(colors: List<ShowkaseBrowserColor>) {
    items(colors) { item ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ElevatedCard(
                modifier = Modifier.size(100.dp),
                colors = CardDefaults.cardColors(containerColor = item.color)
            ) {}
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Color name: ${item.colorName}",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ComponentNavItem(
    selectedNavItem: NavItems,
    onClick: () -> Unit
) {
    NavigationRailItem(
        selected = selectedNavItem == NavItems.Components,
        icon = {
            Icon(
                Icons.Filled.Cake,
                ""
            )
        },
        onClick = onClick
    )
}

@Composable
private fun ColorNavItem(
    selectedNavItem: NavItems,
    onClick: () -> Unit,
) {
    NavigationRailItem(
        selected = selectedNavItem == NavItems.Colors,
        icon = {
            Icon(
                Icons.Filled.Coffee,
                ""
            )
        },
        onClick = onClick
    )
}

private const val AUTOGEN_CLASS_NAME = "Codegen"
private fun getShowkaseProviderElements(
    classKey: String
): ShowkaseElementsMetadata {
    return try {
        val showkaseComponentProvider =
            Class.forName("$classKey$AUTOGEN_CLASS_NAME").newInstance()

        val showkaseMetadata = (showkaseComponentProvider as ShowkaseProvider).metadata()

        ShowkaseElementsMetadata(
            componentList = showkaseMetadata.componentList,
            colorList = showkaseMetadata.colorList,
            typographyList = showkaseMetadata.typographyList
        )
    } catch (exception: ClassNotFoundException) {
        println("ClassNotFoundException!")
        ShowkaseElementsMetadata()
    }
}