package aiki.gui.threads;
import code.gui.AbsMenuItem;
import code.gui.MenuItemUtils;

public final class ChangeEnabledDifficulty implements Runnable {

    private AbsMenuItem difficulty;

    private boolean enabled;

    public ChangeEnabledDifficulty(AbsMenuItem _difficulty, boolean _enabled) {
        difficulty = _difficulty;
        enabled = _enabled;
    }

    @Override
    public void run() {
        MenuItemUtils.setEnabledMenu(difficulty,enabled);
    }
}
