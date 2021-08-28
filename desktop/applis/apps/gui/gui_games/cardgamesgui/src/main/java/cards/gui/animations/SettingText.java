package cards.gui.animations;
import code.gui.AbsPlainLabel;

public final class SettingText implements Runnable {

    private AbsPlainLabel label;

    private String text;

    public SettingText(AbsPlainLabel _label, String _text) {
        label = _label;
        text = _text;
    }

    @Override
    public void run() {
        label.setText(text);
    }
}
