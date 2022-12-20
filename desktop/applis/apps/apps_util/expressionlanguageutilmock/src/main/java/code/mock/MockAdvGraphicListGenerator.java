package code.mock;

import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class MockAdvGraphicListGenerator implements AbstractAdvGraphicListGenerator {
    private final boolean cust;

    public MockAdvGraphicListGenerator(boolean _c) {
        this.cust = _c;
    }

    @Override
    public AbsGraphicListStr createSimple(SpecSelectionCtx _s, AbsCompoFactory _compoFactory) {
        return new MockCustGrListStr(cust);
    }

    @Override
    public AbsGraphicListStr createMult(SpecSelectionCtx _s, AbsCompoFactory _compoFactory) {
        return new MockCustGrListStr(cust);
    }

    @Override
    public boolean isCust() {
        return cust;
    }
}
