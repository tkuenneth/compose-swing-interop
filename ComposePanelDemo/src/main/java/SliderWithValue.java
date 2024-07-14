import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SliderWithValue extends JPanel {

    public static final String CUSTOM_PROPERTY = "customProperty";
    private int customProperty = -1;

    public SliderWithValue() {
        super(new FlowLayout(FlowLayout.LEADING, 8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setAlignmentY(TOP_ALIGNMENT);
        JSlider slider = new JSlider();
        JLabel label = new JLabel();
        slider.addChangeListener((event) -> setCustomProperty(slider.getModel().getValue()));
        slider.setMinimum(1);
        slider.setMaximum(10);
        addPropertyChangeListener(CUSTOM_PROPERTY, (event) -> {
            int newValue = (int) event.getNewValue();
            slider.setValue(newValue);
            label.setText(String.format("%d", newValue));
        });
        setCustomProperty((slider.getMaximum() - slider.getMinimum()) / 2 + slider.getMinimum());
        add(slider);
        add(label);
    }

    public int getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(int newValue) {
        int oldValue = getCustomProperty();
        int _newValue = min(max(1, newValue), 10);
        customProperty = _newValue;
        firePropertyChange(CUSTOM_PROPERTY, oldValue, _newValue);
    }
}
