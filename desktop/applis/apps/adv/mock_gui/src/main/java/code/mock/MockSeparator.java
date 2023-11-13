package code.mock;

import code.gui.AbsSeparator;

public final class MockSeparator extends MockCustComponent implements AbsSeparator {
    private int orient;
    @Override
    public int orientation() {
        return orient;
    }

    @Override
    public void orientation(int _o) {
        orient = _o;
    }
}
