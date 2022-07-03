package code.mock;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class MockAdvGraphicListGeneratorGene<T> implements AbstractGraphicListGenerator<T> {

    @Override
    public AbsGraphicList<T> createMult(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new MockCustGrList<T>();
    }

    @Override
    public AbsGraphicList<T> createSimple(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new MockCustGrList<T>();
    }

}
