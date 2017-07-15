package aiki.gui.threads;
import javax.swing.JMenuItem;

public final class ChangeEnabledDifficulty extends Thread {

    private JMenuItem difficulty;

    private boolean enabled;

    public ChangeEnabledDifficulty(JMenuItem _difficulty, boolean _enabled) {
        difficulty = _difficulty;
        enabled = _enabled;
    }

    @Override
    public void run() {
        difficulty.setEnabled(enabled);
    }
}
