package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.gui.images.AbstractImageFactory;

public interface AbstractGraphicListGenerator<T> {
    AbsGraphicList<T> createSimple(AbstractImageFactory _fact, CustCellRender<T> _render);
    AbsGraphicList<T> createMult(AbstractImageFactory _fact, CustCellRender<T> _render);
}
