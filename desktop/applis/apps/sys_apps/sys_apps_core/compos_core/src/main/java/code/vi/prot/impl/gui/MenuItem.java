package code.vi.prot.impl.gui;

import code.gui.EnabledMenu;

import javax.swing.*;

public final class MenuItem extends AbsMenuItemImpl implements EnabledMenu {

    public MenuItem() {
        super(new JMenuItem());
    }

    public MenuItem(String _text, int _mnemonic) {
        super(new JMenuItem(_text, _mnemonic));
    }

    public MenuItem(String _text) {
        super(new JMenuItem(_text));
    }
}
