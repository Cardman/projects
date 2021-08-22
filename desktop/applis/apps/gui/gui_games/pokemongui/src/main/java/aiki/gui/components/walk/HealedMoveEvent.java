package aiki.gui.components.walk;

import aiki.facade.FacadeGame;
import aiki.gui.dialogs.SelectHealedMove;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class HealedMoveEvent extends AbsMouseListenerRel {

    private SelectHealedMove dialog;

    private FacadeGame facade;

    private String key;

    public HealedMoveEvent(SelectHealedMove _dialog, FacadeGame _facade, String _key) {
        dialog = _dialog;
        facade = _facade;
        key = _key;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        facade.healMove(key);
        dialog.getAbsDialog().closeWindow();
    }
}
