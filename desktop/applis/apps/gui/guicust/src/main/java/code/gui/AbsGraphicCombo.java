package code.gui;

import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public interface AbsGraphicCombo extends GraphicComboGrIntBase,WithListListener {
    AbstractImageFactory getFact();
    AbsGraphicStringList getGrList();
    AbsPanel getPanel();
    void setSelectedIndex(int _selectedIndex);
    AbsPreparedLabel getLabel();
    void showMenu();
    AbsCompoFactory getCompoFactory();
}
