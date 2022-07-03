package code.mock;

import code.gui.AbsMetaLabelInt;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.core.NumberUtil;

public final class MockPaintableLabel extends MockPaintableLabelAbs{

    private final AbsMetaLabelInt metaLabel;
    public MockPaintableLabel(AbsMetaLabelInt _meta) {
        metaLabel = _meta;
    }

    @Override
    public void repaintLabel(AbstractImageFactory _i) {
        int w_ = getWidth();
        int h_ = getHeight();
        if (NumberUtil.signum(w_) + NumberUtil.signum(h_) <= 1) {
            setEmptyIcon();
            return;
        }
        AbstractImage img_ = _i.newImageArgb(w_, h_);
        img_.setFont(metaLabel);
        metaLabel.paintComponent(img_);
        setIcon(_i,img_);
    }
}
