import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun BackButton(onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier.padding(20.dp),
        onClick = onClick,
        shape = CircleShape
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            "Back arrow"
        )
    }
}
