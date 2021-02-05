package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.AbsGraphicListPainter;
import code.gui.CustGrList;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class AdvGraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(boolean _simple) {
        return new CustGrList<>(_simple);
    }

    @Override
    public AbsGraphicList<T> create(boolean _simple, AbsGraphicListPainter _abs) {
        return new CustGrList<>(_simple);
    }
}
