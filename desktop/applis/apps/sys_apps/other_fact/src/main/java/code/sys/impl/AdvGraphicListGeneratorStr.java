package code.sys.impl;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.sys.impl.other.CustGrListStr;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class AdvGraphicListGeneratorStr  implements AbstractAdvGraphicListGenerator {
    @Override
    public AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs) {
        return new CustGrListStr(_simple);
    }

    @Override
    public boolean isCust() {
        return true;
    }
}
