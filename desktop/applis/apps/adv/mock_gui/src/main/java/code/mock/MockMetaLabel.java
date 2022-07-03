package code.mock;

import code.gui.AbsMetaLabelInt;
import code.gui.AbsPaintableLabel;
import code.gui.images.AbstractImage;

public final class MockMetaLabel implements AbsMetaLabelInt {

    private final MockPaintableLabelNo mockPaintableLabelNo = new MockPaintableLabelNo();

    @Override
    public AbsPaintableLabel getPaintableLabel() {
        return mockPaintableLabelNo;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        mockPaintableLabelNo.setEmptyIcon();
    }
}
