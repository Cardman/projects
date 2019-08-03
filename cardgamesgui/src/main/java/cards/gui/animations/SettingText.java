package cards.gui.animations;
import code.gui.TextLabel;

public final class SettingText extends Thread {

    private TextLabel label;

    private String text;

    public SettingText(TextLabel _label, String _text) {
        label = _label;
        text = _text;
    }

    @Override
    public void run() {
        label.setText(text);
    }
}
