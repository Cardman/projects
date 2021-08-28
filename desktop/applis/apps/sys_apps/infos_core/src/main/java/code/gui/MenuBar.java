package code.gui;
import code.util.CustList;

import javax.swing.JMenuBar;
public final class MenuBar implements AbsMenuBar {

    private final JMenuBar menuBar = new JMenuBar();
    private final CustList<AbsMenu> menus = new CustList<AbsMenu>();

    JMenuBar getMenuBar() {
        return menuBar;
    }

    public void add(AbsMenu _c) {
        menuBar.add(((Menu)_c).getMenu());
        menus.add(_c);
    }

    public AbsMenu getMenu(int _index) {
        return menus.get(_index);
    }

    public int getMenuCount() {
        return menus.size();
    }

    public void remove(AbsMenu _component) {
        menuBar.remove(((Menu)_component).getMenu());
    }
}
