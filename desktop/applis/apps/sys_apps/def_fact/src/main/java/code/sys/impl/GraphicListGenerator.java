package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.DefaultGraphicListPainter;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class GraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(AbstractImageFactory _fact, boolean _simple) {
        return new GraphicList<T>(_simple, new DefaultGraphicListPainter(_fact));
    }

}
