package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectPokemon;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SeePkDetailEvent extends AbsMouseListenerRel {

    private SelectPokemon dialog;

    public SeePkDetailEvent(SelectPokemon _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.seePkDetail();
    }
}
