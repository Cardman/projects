package cards.gui.dialogs.events;

import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class MoveCardsEvent implements AbsActionListener {

    private SetterSelectedCardList dialog;

    public MoveCardsEvent(SetterSelectedCardList _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.deplacerCartes();
    }
}
