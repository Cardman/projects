package code.mock;

import code.gui.AbsPlainLabel;

public final class MockPlainLabel extends MockCustComponent implements AbsPlainLabel {
    private String text;

    public MockPlainLabel(String _t) {
        text = _t;
    }
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String _s) {
        this.text = _s;
    }
}
