import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.android.showkase.models.ShowkaseBrowserComponent

@OptIn(ExperimentalMaterial3Api::class)
internal fun LazyGridScope.componentGroup(
    component: List<ShowkaseBrowserComponent>,
    onSelectComponent: (component: ComponentViewState) -> Unit
) {
    items(component.distinctBy { it.group }) { item ->
        ElevatedCard(
            modifier = Modifier.padding(20.dp),
            onClick = {
                onSelectComponent(ComponentViewState.Components(item.group))
            }
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 12.dp)
                    .align(Alignment.CenterHorizontally),
                text = item.group,
                textAlign = TextAlign.Center
            )
        }
    }
}

