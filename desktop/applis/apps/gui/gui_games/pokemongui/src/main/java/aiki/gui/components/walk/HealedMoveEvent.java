package aiki.gui.components.walk;

import aiki.facade.FacadeGame;
import aiki.gui.dialogs.SelectHealedMove;
import code.gui.events.AbsActionListener;

public class HealedMoveEvent implements AbsActionListener {

    private SelectHealedMove dialog;

    private FacadeGame facade;

    private String key;

    public HealedMoveEvent(SelectHealedMove _dialog, FacadeGame _facade, String _key) {
        dialog = _dialog;
        facade = _facade;
        key = _key;
    }

    @Override
    public void action() {
        facade.healMove(key);
        dialog.getAbsDialog().closeWindow();
    }
}
