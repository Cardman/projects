package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.AbsGraphicListPainter;
import code.gui.DefaultGraphicListPainter;
import code.gui.GraphicList;

public final class GraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(boolean _simple) {
        return new GraphicList<T>(_simple, new DefaultGraphicListPainter());
    }
    @Override
    public AbsGraphicList<T> create(boolean _simple, AbsGraphicListPainter _abs) {
        return new GraphicList<T>(_simple, _abs);
    }

}
