package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ValidateRulesDealEvent extends AbsMouseListenerRel {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCards _parent) {
        dialog = _dialog;
        window = _parent;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.validateRulesDeal(window);
    }
}
