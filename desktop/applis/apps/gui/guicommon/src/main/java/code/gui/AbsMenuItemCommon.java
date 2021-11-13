package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;

public interface AbsMenuItemCommon extends EnabledMenu {
    void addActionListener(AbsActionListener _showDataFightEvent);
    void addActionListener(AbsAdvActionListener _showDataFightEvent);

    void setAccelerator(char _a);

    void setAccelerator(String _a);

    void setAccelerator(int _a, int _b);
}
