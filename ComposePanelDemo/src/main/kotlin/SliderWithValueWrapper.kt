import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.awt.ComposePanel
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JPanel

const val CURRENT_VALUE_PROPERTY = "myCustomProperty"

class SliderWithValueWrapper(val initialValue: Int) : JPanel(BorderLayout()) {

    var currentValue: Int = initialValue
        set(value) {
            firePropertyChange(CURRENT_VALUE_PROPERTY, field, value)
            field = value
        }

    private val currentValueFloat = MutableStateFlow(currentValue.toFloat())

    init {
        val composePanel = ComposePanel()
        composePanel.setContent {
            val state by currentValueFloat.collectAsState()
            SliderWithValue(state, ({ newFloat ->
                currentValue = newFloat.toInt()
                currentValueFloat.value = newFloat
            }))
            addPropertyChangeListener(CURRENT_VALUE_PROPERTY) { event ->
                (event.newValue as Int).run {
                    currentValueFloat.value = toFloat()
                }
            }
        }
        preferredSize = Dimension(300, 96)
        add(composePanel, BorderLayout.CENTER)
    }
}
