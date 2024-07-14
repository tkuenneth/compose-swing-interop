import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.awt.ComposePanel
import kotlinx.coroutines.flow.MutableStateFlow
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JPanel

class SliderWithValueWrapper(initialValue: Int = 5) : JPanel(BorderLayout()) {

    var customProperty: Int = initialValue
        set(value) {
            firePropertyChange(SliderWithValue.CUSTOM_PROPERTY, field, value)
            field = value
        }

    private val currentValueFloat = MutableStateFlow(customProperty.toFloat())

    init {
        val composePanel = ComposePanel()
        composePanel.setContent {
            val state by currentValueFloat.collectAsState()
            SliderWithValue(state, ({ newFloat ->
                customProperty = newFloat.toInt()
                currentValueFloat.value = newFloat
            }))
            addPropertyChangeListener(SliderWithValue.CUSTOM_PROPERTY) { event ->
                (event.newValue as Int).run {
                    currentValueFloat.value = toFloat()
                }
            }
        }
        preferredSize = Dimension(300, 96)
        add(composePanel, BorderLayout.CENTER)
    }
}
