package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class BackToRulesEvent extends AbsMouseListenerRel {

    private SetterSelectedCardList dialog;
    private WindowCards window;

    public BackToRulesEvent(SetterSelectedCardList _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.backToRules(window);
    }
}
