import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SliderWithValuePreview() {
    SliderWithValue()
}

@Composable
fun SliderWithValue(
    value: Float = 1F, callback: (Float) -> Unit = {}
) {
    MaterialTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Slider(
                modifier = Modifier.weight(1F),
                value = value,
                onValueChange = callback,
                valueRange = 1F..10F,
                steps = 10
            )
            Text(
                modifier = Modifier.padding(start = 8.dp).width(32.dp),
                text = "${value.toInt()}",
                textAlign = TextAlign.End
            )
        }
    }
}
