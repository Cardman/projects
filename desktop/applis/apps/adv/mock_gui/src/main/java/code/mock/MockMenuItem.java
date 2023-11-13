package code.mock;

import code.gui.EnabledMenu;

public final class MockMenuItem extends MockEnabledMenu implements EnabledMenu {

    public MockMenuItem() {
        this("");
    }
    public MockMenuItem(String _s) {
        super(_s);
    }

}
