import javax.swing.*;
import java.awt.*;

public class ComposePanelDemo extends JFrame {

    private static final String[] numbers = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    public ComposePanelDemo() {
        super("ComposePanelDemo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JLabel label = new JLabel();
        box.add(label);
        box.add(Box.createVerticalStrut(8));
        JButton button = new JButton("+1");
        box.add(button);
        SliderWithValue sliderWithValue = new SliderWithValue();
        sliderWithValue.addPropertyChangeListener(SliderWithValue.CUSTOM_PROPERTY, evt -> {
            updateText(label, (int) evt.getNewValue());
        });
        updateText(label, sliderWithValue.getCustomProperty());
        button.addActionListener(e -> {
            int newValue = sliderWithValue.getCustomProperty() + 1;
            sliderWithValue.setCustomProperty(newValue <= 10 ? newValue : 1);
        });
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(sliderWithValue, BorderLayout.CENTER);
        contentPanel.add(box, BorderLayout.SOUTH);
        setContentPane(contentPanel);
        pack();
    }

    private void updateText(JLabel label, int value) {
        label.setText(String.format("%s", numbers[value - 1]));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComposePanelDemo main = new ComposePanelDemo();
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
