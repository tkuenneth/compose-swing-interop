package de.thomaskuenneth.counterdemodesktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import counterdemodesktop.generated.resources.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.compose.resources.stringResource

private val mutableStateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
private val counterFlow = mutableStateFlow.asStateFlow()
private fun increaseCounter() = mutableStateFlow.update { it + 1 }

fun main() = application {
    App()
    // App2()
}

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

@Composable
fun ApplicationScope.App2() {
    Window(onCloseRequest = ::exitApplication) {
        StateDemo()
    }
}

@Composable
@Preview
fun StateDemo() {
    val toggle: MutableState<Boolean> = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = if (toggle.value) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { toggle.value = !toggle.value }) {
            Text(text = stringResource(Res.string.toggle))
        }
    }
}

@Composable
fun StateDemoUsingBy() {
    var toggle: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = if (toggle) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { toggle = !toggle }) {
            Text(text = stringResource(Res.string.toggle))
        }
    }
}
