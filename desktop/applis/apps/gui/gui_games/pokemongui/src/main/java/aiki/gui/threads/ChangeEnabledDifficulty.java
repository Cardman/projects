package aiki.gui.threads;
import code.gui.EnabledMenu;
import code.gui.MenuItemUtils;

public final class ChangeEnabledDifficulty implements Runnable {

    private EnabledMenu difficulty;

    private boolean enabled;

    public ChangeEnabledDifficulty(EnabledMenu _difficulty, boolean _enabled) {
        difficulty = _difficulty;
        enabled = _enabled;
    }

    @Override
    public void run() {
        MenuItemUtils.setEnabledMenu(difficulty,enabled);
    }
}
