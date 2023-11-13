package code.mock;

import code.gui.EnabledMenu;

public final class MockCheckBoxMenuItem extends MockEnabledMenu implements EnabledMenu {
    public MockCheckBoxMenuItem() {
        this("");
    }
    public MockCheckBoxMenuItem(String _s) {
        this(_s,false);
    }
    public MockCheckBoxMenuItem(String _s, boolean _b) {
        super(_s);
        setSelected(_b);
    }
}
