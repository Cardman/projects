package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;

public interface WithFrame {
    AbsCommonFrame getFrame();
    EnabledMenu getMenu();
}
