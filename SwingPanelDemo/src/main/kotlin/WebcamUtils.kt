import com.github.eduramiba.webcamcapture.drivers.NativeDriver
import com.github.sarxos.webcam.Webcam
import com.github.sarxos.webcam.WebcamPanel
import com.github.sarxos.webcam.WebcamResolution
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

private val nativeDriverInstalled: Boolean by lazy {
    Webcam.setDriver(NativeDriver())
    true
}

/** Prefer an external/UVC camera over the lid-closed FaceTime camera when both exist. */
private fun selectWebcam(): Webcam? {
    val webcams = Webcam.getWebcams()
    if (webcams.isEmpty()) return null
    return webcams.firstOrNull { !it.name.contains("FaceTime", ignoreCase = true) }
        ?: webcams.last()
}

class WebcamHostPanel(isImageSizeDisplayed: Boolean) : JPanel(BorderLayout()) {

    private val webcamPanel: WebcamPanel?
    private val content: JComponent
    private val sizeLabel = JLabel("640 × 480 px", SwingConstants.CENTER).apply {
        font = font.deriveFont(Font.BOLD, 16f)
        isOpaque = true
        background = Color(0, 0, 0, 180)
        foreground = Color.WHITE
        isVisible = false
    }
    private val fallbackLabel = JLabel("", SwingConstants.CENTER).apply {
        font = font.deriveFont(Font.BOLD, 28f)
        isOpaque = true
    }

    var isImageSizeDisplayed: Boolean = isImageSizeDisplayed
        set(value) {
            field = value
            sizeLabel.isVisible = value && webcamPanel != null
            webcamPanel?.setImageSizeDisplayed(value)
            if (webcamPanel == null) {
                fallbackLabel.text =
                    if (value) "Image size: 640 x 480" else "Webcam unavailable"
                fallbackLabel.background =
                    if (value) Color(0xFF, 0xCC, 0x00) else Color(0xDD, 0xDD, 0xDD)
                fallbackLabel.foreground = Color.BLACK
            }
            revalidate()
            repaint()
        }

    init {
        preferredSize = Dimension(640, 480)
        webcamPanel = try {
            nativeDriverInstalled
            selectWebcam()?.also { it.viewSize = WebcamResolution.VGA.size }?.let { webcam ->
                WebcamPanel(webcam).apply {
                    isMirrored = true
                    setImageSizeDisplayed(isImageSizeDisplayed)
                }
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            null
        }

        content = webcamPanel ?: fallbackLabel
        add(content, BorderLayout.CENTER)
        add(sizeLabel, BorderLayout.SOUTH)
        this.isImageSizeDisplayed = isImageSizeDisplayed
    }
}

fun createWebcamPanel(isImageSizeDisplayed: Boolean): WebcamHostPanel =
    WebcamHostPanel(isImageSizeDisplayed)
