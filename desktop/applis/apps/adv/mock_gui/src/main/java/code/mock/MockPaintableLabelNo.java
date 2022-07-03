package code.mock;

import code.gui.images.AbstractImageFactory;

public final class MockPaintableLabelNo extends MockPaintableLabelAbs{
    @Override
    public void repaintLabel(AbstractImageFactory _i) {
        _i.newImageArgb(1,1);
    }
}
