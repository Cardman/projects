package code.gui;

import code.gui.events.AbsEnabledAction;
import code.util.CustList;

public interface AbsTextPane extends AbsCustComponent {
    void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b);
    AbsEnabledAction getAction(int _a, int _b);
    CustList<AbsEnabledAction> getActions();
    CustList<String> getKeysAction();
    void setFontSize(int _size);
}
