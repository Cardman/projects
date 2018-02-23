package aiki.gui.threads;
import code.gui.MenuItem;

public final class ChangeEnabledDifficulty extends Thread {

    private MenuItem difficulty;

    private boolean enabled;

    public ChangeEnabledDifficulty(MenuItem _difficulty, boolean _enabled) {
        difficulty = _difficulty;
        enabled = _enabled;
    }

    @Override
    public void run() {
        difficulty.setEnabledMenu(enabled);
    }
}
