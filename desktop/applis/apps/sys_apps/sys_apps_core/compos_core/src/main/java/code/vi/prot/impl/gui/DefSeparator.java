package code.vi.prot.impl.gui;

import code.gui.AbsSeparator;

import javax.swing.*;

public final class DefSeparator extends CustComponent implements AbsSeparator {

    private final JSeparator separator = new JSeparator();
    @Override
    public int orientation() {
        return separator.getOrientation();
    }

    @Override
    public void orientation(int _o) {
        separator.setOrientation(_o);
    }

    @Override
    public JComponent getNatComponent() {
        return separator;
    }
}
