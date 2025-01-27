package aiki.gui.threads;
import code.gui.*;

public final class ChangeEnabledDifficulty implements Runnable {

    private final EnabledMenu difficulty;

    private final boolean enabled;

    public ChangeEnabledDifficulty(EnabledMenu _difficulty, boolean _enabled) {
        difficulty = _difficulty;
        enabled = _enabled;
    }

    @Override
    public void run() {
        difficulty.setEnabled(enabled);
    }
}
