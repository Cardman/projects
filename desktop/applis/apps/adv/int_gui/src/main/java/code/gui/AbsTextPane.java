package code.gui;

import code.gui.events.AbsEnabledAction;
import code.util.StringMap;

public interface AbsTextPane extends AbsTxtComponent {
    void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b);

    StringMap<AbsEnabledAction> getActionsMap();

    void setFontSize(int _size);
    void clearCharacterAttributes(int _begin, int _length);
    void setCharacterAttributes(int _begin, int _length, AbsAttrSet _attrs, boolean _replace);
}
