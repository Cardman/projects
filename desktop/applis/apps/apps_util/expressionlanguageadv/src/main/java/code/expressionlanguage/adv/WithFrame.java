package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsMenuItem;

public interface WithFrame {
    AbsCommonFrame getFrame();
    AbsMenuItem getMenu();
}
