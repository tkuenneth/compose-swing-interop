package de.thomaskuenneth.counterdemodesktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import counterdemodesktop.generated.resources.Res
import counterdemodesktop.generated.resources.app_name
import counterdemodesktop.generated.resources.click
import counterdemodesktop.generated.resources.not_clicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

private val mutableStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
private val counterFlow = mutableStateFlow.asStateFlow()
private fun increaseCounter() = mutableStateFlow.update { it + 1 }

fun main() = application {
    App()
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ApplicationScope.App() {
    val counter by counterFlow.collectAsState()
    Window(
        state = WindowState(
            width = 400.dp, height = 200.dp
        ), onCloseRequest = ::exitApplication, title = stringResource(Res.string.app_name)
    ) {
        MaterialTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.weight(1.0F), contentAlignment = Alignment.Center
                    ) {
                        if (counter == 0) {
                            Text(
                                text = stringResource(Res.string.not_clicked),
                                style = MaterialTheme.typography.bodyLarge.merge(fontStyle = FontStyle.Italic)
                            )
                        } else {
                            Text(text = counter.toString(), style = MaterialTheme.typography.displayLarge)
                        }
                    }
                    Button(modifier = Modifier.padding(bottom = 16.dp), onClick = ::increaseCounter) {
                        Text(text = stringResource(Res.string.click))
                    }
                }
            }
        }
    }
}
