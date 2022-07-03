package code.mock;

import code.gui.AbsPaintableLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public abstract class MockPaintableLabelAbs extends MockCustComponent implements AbsPaintableLabel {
    private boolean empty = true;
    private int verticalAlignment;
    private int horizontalAlignment;

    @Override
    public void requestFocusInWindow() {
        requestFocus();
    }

    @Override
    public void setEmptyIcon() {
        empty = true;
    }

    @Override
    public void setIcon(AbstractImageFactory _f, AbstractImage _i) {
        empty = false;
    }

    @Override
    public void setVerticalAlignment(int _alignment) {
        verticalAlignment = _alignment;
    }

    @Override
    public void setHorizontalAlignment(int _alignment) {
        horizontalAlignment = _alignment;
    }

    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    public int getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public boolean isEmpty() {
        return empty;
    }
}
