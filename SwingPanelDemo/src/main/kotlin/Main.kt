import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import eu.thomaskuenneth.swingpaneldemo.swingpaneldemo.generated.resources.Res
import eu.thomaskuenneth.swingpaneldemo.swingpaneldemo.generated.resources.app_name
import eu.thomaskuenneth.swingpaneldemo.swingpaneldemo.generated.resources.logo
import eu.thomaskuenneth.swingpaneldemo.swingpaneldemo.generated.resources.toggle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


fun main() = application {
    App()
}

@Preview
@Composable
fun ApplicationScope.App() {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center)),
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.logo),
    ) {
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var width by remember { mutableStateOf(0.dp) }
                var height by remember { mutableStateOf(0.dp) }
                var isImageSizeDisplayed by remember { mutableStateOf(false) }
                val density = LocalDensity.current
                SwingPanel(background = Color.Red, factory = {
                    createWebcamPanel(
                        isImageSizeDisplayed = isImageSizeDisplayed
                    ).also {
                        with(density) {
                            it.preferredSize.let { preferredSize ->
                                width = preferredSize.width.toDp()
                                height = preferredSize.height.toDp()
                            }
                        }
                    }
                }, update = {
                    it.isImageSizeDisplayed = isImageSizeDisplayed
                }, modifier = Modifier.size(width = width, height = height)
                )
                Spacer(modifier = Modifier.height(32.dp))
                TextButton(onClick = { isImageSizeDisplayed = !isImageSizeDisplayed }) {
                    Text(text = stringResource(Res.string.toggle))
                }
            }
        }
    }
}
