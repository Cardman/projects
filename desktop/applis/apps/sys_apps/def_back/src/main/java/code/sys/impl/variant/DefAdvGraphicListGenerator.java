package code.sys.impl.variant;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class DefAdvGraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(AbstractImageFactory _fact, boolean _simple, CustCellRender<T> _render) {
        return new DefCustGrList<T>(_simple,_render);
    }

}
