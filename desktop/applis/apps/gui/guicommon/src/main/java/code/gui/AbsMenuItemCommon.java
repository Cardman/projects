package code.gui;

import code.gui.events.AbsActionListener;

public interface AbsMenuItemCommon extends EnabledMenu {
    void addActionListener(AbsActionListener _showDataFightEvent);

    void setAccelerator(char _a);

    void setAccelerator(String _a);

    void setAccelerator(int _a, int _b);
}
