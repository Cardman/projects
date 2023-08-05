package code.expressionlanguage.adv;

import code.gui.AbsPlainLabel;
import code.gui.AbsTextPane;

public interface AbsLightTabEditor {
    void centerSelect(int _selectionStart, int _selectionEnd);
    String centerText();
    void centerText(String _t);
    AbsTextPane getCenter();

    int getTabWidth();

    String getUseFeed();

    AbsPlainLabel getLabel();

}
