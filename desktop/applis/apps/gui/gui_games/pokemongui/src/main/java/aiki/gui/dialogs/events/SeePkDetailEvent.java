package aiki.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.dialogs.SelectPokemon;

public class SeePkDetailEvent extends MouseAdapter {

    private SelectPokemon dialog;

    public SeePkDetailEvent(SelectPokemon _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.seePkDetail();
    }
}
