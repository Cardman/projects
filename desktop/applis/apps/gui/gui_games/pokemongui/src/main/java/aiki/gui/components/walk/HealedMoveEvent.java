package aiki.gui.components.walk;

import aiki.gui.dialogs.SelectHealingItem;
import code.gui.events.AbsActionListener;

public class HealedMoveEvent implements AbsActionListener {

    private final SelectHealingItem dialog;

    private final String key;

    public HealedMoveEvent(SelectHealingItem _dialog, String _key) {
        dialog = _dialog;
        key = _key;
    }

    @Override
    public void action() {
        dialog.getMainWindow().getModal().set(false);
        dialog.getMainWindow().getScenePanel().healMoveAndLog(key);
        dialog.getSelectDial().setVisible(false);
    }
}
