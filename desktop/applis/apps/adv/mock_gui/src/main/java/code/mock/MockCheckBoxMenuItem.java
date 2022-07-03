package code.mock;

import code.gui.AbsCheckBoxMenuItem;

public final class MockCheckBoxMenuItem extends MockAbsMenuItem implements AbsCheckBoxMenuItem {

    private boolean selected;
    public MockCheckBoxMenuItem() {
        this("");
    }
    public MockCheckBoxMenuItem(String _s) {
        this(_s,false);
    }
    public MockCheckBoxMenuItem(String _s, boolean _b) {
        super(_s);
        selected = _b;
    }
    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean _b) {
        selected = _b;
    }
}
