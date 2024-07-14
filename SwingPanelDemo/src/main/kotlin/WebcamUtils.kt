import com.github.sarxos.webcam.Webcam
import com.github.sarxos.webcam.WebcamPanel
import com.github.sarxos.webcam.WebcamResolution

fun createWebcamPanel(isImageSizeDisplayed: Boolean): WebcamPanel = with(Webcam.getDefault()) {
    viewSize = WebcamResolution.VGA.size
    val panel = WebcamPanel(this)
    panel.isMirrored = true
    panel.isImageSizeDisplayed = isImageSizeDisplayed
    panel
}
