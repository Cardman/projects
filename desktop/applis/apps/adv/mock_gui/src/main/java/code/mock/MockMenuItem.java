package code.mock;

import code.gui.AbsMenuItem;

public final class MockMenuItem extends MockAbsMenuItem implements AbsMenuItem {

    public MockMenuItem() {
        this("");
    }
    public MockMenuItem(String _s) {
        super(_s);
    }

}
