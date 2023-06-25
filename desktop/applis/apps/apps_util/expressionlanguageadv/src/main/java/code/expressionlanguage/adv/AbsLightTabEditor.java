package code.expressionlanguage.adv;

import code.gui.AbsPlainLabel;
import code.gui.AbsTextPane;

public interface AbsLightTabEditor {
    AbsTextPane getCenter();

    int getTabWidth();

    String getUseFeed();

    AbsPlainLabel getLabel();

}
