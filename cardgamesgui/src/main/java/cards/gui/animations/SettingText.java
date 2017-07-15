package cards.gui.animations;
import javax.swing.JLabel;

public final class SettingText extends Thread {

    private JLabel label;

    private String text;

    public SettingText(JLabel _label, String _text) {
        label = _label;
        text = _text;
    }

    @Override
    public void run() {
        label.setText(text);
    }
}
