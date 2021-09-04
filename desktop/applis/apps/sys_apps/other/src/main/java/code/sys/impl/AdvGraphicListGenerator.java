package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.sys.impl.other.CustGrList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class AdvGraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(AbstractImageFactory _fact, boolean _simple, CustCellRender<T> _render) {
        return new CustGrList<>(_simple,_render);
    }

}
