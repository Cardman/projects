package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectPokemon;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class SeePkDetailEvent implements AbsActionListener {

    private SelectPokemon dialog;

    public SeePkDetailEvent(SelectPokemon _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.seePkDetail();
    }
}
