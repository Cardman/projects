package code.vi.prot.impl.gui;
import code.gui.EnabledMenu;
import code.gui.AbsMenuBar;
import code.util.CustList;

import javax.swing.*;

public final class MenuBar extends CustComponent implements AbsMenuBar {

    private final JMenuBar meBar = new JMenuBar();
    private final CustList<EnabledMenu> menus = new CustList<EnabledMenu>();

    public JMenuBar getMeBar() {
        return meBar;
    }

    @Override
    public JComponent getNatComponent() {
        return getMeBar();
    }

    public void add(EnabledMenu _c) {
        meBar.add(((Menu)_c).getMeCo());
        menus.add(_c);
    }

    public EnabledMenu getMenu(int _index) {
        return menus.get(_index);
    }

    public int getMenuCount() {
        return menus.size();
    }

    public void remove(EnabledMenu _component) {
        meBar.remove(((Menu)_component).getMeCo());
    }
}
