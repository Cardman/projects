package code.vi.prot.impl.gui;
import code.gui.AbsCheckBoxMenuItem;

import javax.swing.*;

public class CheckBoxMenuItem extends AbsMenuItemImpl implements AbsCheckBoxMenuItem {

    public CheckBoxMenuItem() {
        super(new JCheckBoxMenuItem());
    }

    public CheckBoxMenuItem(String _text) {
        super(new JCheckBoxMenuItem(_text));
    }

    public CheckBoxMenuItem(String _text, boolean _b) {
        super(new JCheckBoxMenuItem(_text, _b));
    }

}
