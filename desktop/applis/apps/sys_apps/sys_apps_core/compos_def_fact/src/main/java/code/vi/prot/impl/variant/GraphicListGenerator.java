package code.vi.prot.impl.variant;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.gui.DefaultGraphicListPainter;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class GraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {

    @Override
    public AbsGraphicList<T> createSimple(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new GraphicList<T>(true, new DefaultGraphicListPainter(_fact), _render);
    }

    @Override
    public AbsGraphicList<T> createMult(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new GraphicList<T>(false, new DefaultGraphicListPainter(_fact), _render);
    }
}
