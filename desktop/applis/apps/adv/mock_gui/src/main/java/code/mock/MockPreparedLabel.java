package code.mock;

import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public final class MockPreparedLabel extends MockCustComponent implements AbsPreparedLabel {
    private int width;
    private int height;

    public MockPreparedLabel() {
        width = 1;
        height = 1;
    }

    public MockPreparedLabel(AbstractImage _abstractImage) {
        width = _abstractImage.getWidth();
        height = _abstractImage.getHeight();
    }
    @Override
    public void setIcon(AbstractImageFactory _abstractImageFactory, AbstractImage _abstractImage) {
        width = _abstractImage.getWidth();
        height = _abstractImage.getHeight();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
