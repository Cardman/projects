package code.vi.prot.impl.gui;

import code.gui.EnabledMenu;

import javax.swing.*;

public final class Menu extends AbsMenuItemImpl implements EnabledMenu {


    public Menu() {
        super(new JMenu());
    }

    public Menu(String _s, boolean _b) {
        super(new JMenu(_s, _b));
    }

    public Menu(String _s) {
        super(new JMenu(_s));
    }
    JMenu getMeCo() {
        return (JMenu)getMenu();
    }
}
