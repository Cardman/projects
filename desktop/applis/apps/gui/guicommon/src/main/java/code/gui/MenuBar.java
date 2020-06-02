package code.gui;
import code.util.CustList;

import javax.swing.JMenuBar;
public final class MenuBar {

    private JMenuBar menuBar = new JMenuBar();
    private CustList<Menu> menus = new CustList<Menu>();

    JMenuBar getMenuBar() {
        return menuBar;
    }

    public void add(Menu _c) {
        menuBar.add(_c.getMenu());
        menus.add(_c);
    }

    public Menu getMenu(int _index) {
        return menus.get(_index);
    }

    public int getMenuCount() {
        return menus.size();
    }

    public void remove(Menu _component) {
        menuBar.remove(_component.getMenu());
    }
}
