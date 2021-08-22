package cards.gui.dialogs.events;

import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class MoveCardsEvent extends AbsMouseListenerRel {

    private SetterSelectedCardList dialog;

    public MoveCardsEvent(SetterSelectedCardList _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.deplacerCartes();
    }
}
