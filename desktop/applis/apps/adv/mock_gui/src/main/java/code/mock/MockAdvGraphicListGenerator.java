package code.mock;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class MockAdvGraphicListGenerator implements AbstractAdvGraphicListGenerator {
    private final boolean cust;

    public MockAdvGraphicListGenerator(boolean _c) {
        this.cust = _c;
    }

    @Override
    public AbsGraphicListStr createSimple(AbsGraphicListPainter _p, SpecSelectionCtx _s) {
        return new MockCustGrListStr(cust);
    }

    @Override
    public AbsGraphicListStr createMult(AbsGraphicListPainter _p, SpecSelectionCtx _s) {
        return new MockCustGrListStr(cust);
    }

    @Override
    public boolean isCust() {
        return cust;
    }
}
