package code.vi.prot.impl.gui;
import code.gui.AbsMenu;
import code.gui.AbsMenuBar;
import code.util.CustList;

import javax.swing.JMenuBar;
public final class MenuBar implements AbsMenuBar {

    private final JMenuBar meBar = new JMenuBar();
    private final CustList<AbsMenu> menus = new CustList<AbsMenu>();

    public JMenuBar getMeBar() {
        return meBar;
    }

    public void add(AbsMenu _c) {
        meBar.add(((Menu)_c).getMeCo());
        menus.add(_c);
    }

    public AbsMenu getMenu(int _index) {
        return menus.get(_index);
    }

    public int getMenuCount() {
        return menus.size();
    }

    public void remove(AbsMenu _component) {
        meBar.remove(((Menu)_component).getMeCo());
    }
}
