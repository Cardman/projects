package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.DefaultGraphicListPainter;
import code.gui.GraphicList;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class GraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(boolean _simple) {
        return new GraphicList<T>(_simple, new DefaultGraphicListPainter());
    }

}
