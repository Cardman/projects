package code.vi.sys.impl.variant;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class DefAdvGraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {
    @Override
    public AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new DefCustGrListStr(_simple,_create);
    }

    @Override
    public boolean isCust() {
        return true;
    }
}
