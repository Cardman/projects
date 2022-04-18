package code.vi.prot.impl.gui;

import code.gui.FrameUtil;
import code.gui.images.AbstractImageFactory;

public final class NoPaintableLabel extends PaintableLabelAbs {


    public void repaintLabel(AbstractImageFactory _fact) {
        FrameUtil.repaintNo(_fact, this);
    }

}
