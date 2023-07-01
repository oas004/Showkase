import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent

@OptIn(ExperimentalMaterial3Api::class)
internal fun LazyGridScope.conmponentOverview(
    onSelectComponent: (component: ComponentViewState) -> Unit,
    component: List<ShowkaseBrowserComponent>,
    selectedComponentViewState: ComponentViewState.Components
) {
    item {
        BackButton(
            onClick = {
                onSelectComponent(ComponentViewState.Group)
            }
        )
    }
    items(
        component
            .distinctBy { it.componentName }
            .filter { it.group == selectedComponentViewState.belongingGroup }
    ) { item ->
        ElevatedCard(
            modifier = Modifier.padding(20.dp),
            onClick = {
                onSelectComponent(ComponentViewState.SingleComponent(item, item.group))
            }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 12.dp),
                text = item.componentName,
                textAlign = TextAlign.Center
            )
        }
    }
}
