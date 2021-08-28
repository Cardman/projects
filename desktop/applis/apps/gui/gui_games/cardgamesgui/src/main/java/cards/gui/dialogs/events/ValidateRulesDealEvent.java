package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ValidateRulesDealEvent implements AbsActionListener {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCards _parent) {
        dialog = _dialog;
        window = _parent;
    }

    @Override
    public void action() {
        dialog.validateRulesDeal(window);
    }
}
