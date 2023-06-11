import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.airbnb.android.showkase.models.ShowkaseElementsMetadata
import com.airbnb.android.showkase.models.ShowkaseProvider

fun main() = application {
    val component = remember {
        getShowkaseProviderElements("com.airbnb.android.showkasesample.RootModule")
            .componentList
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 300.dp, height = 300.dp)
    ) {
        MaterialTheme {
            LazyColumn(Modifier.fillMaxSize()) {
                items(component) {item ->
                    Text(item.componentName)
                    item.component
                }
            }
        }
    }

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