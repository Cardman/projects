package aiki.gui.dialogs.events;

import aiki.gui.dialogs.SelectPokemon;
import code.gui.events.AbsActionListener;

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
