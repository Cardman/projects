package code.vi.prot.impl.gui;

import code.gui.AbsMetaLabel;
import code.gui.FrameUtil;
import code.gui.images.AbstractImageFactory;

public final class PaintableLabel extends PaintableLabelAbs {

    private final AbsMetaLabel metaLabel;
    public PaintableLabel(AbsMetaLabel _meta) {
        metaLabel = _meta;
    }

    public void repaintLabel(AbstractImageFactory _fact) {
        FrameUtil.repaint(_fact, this, metaLabel);
    }

}
