package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.GraphicList;

public final class GraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {
    @Override
    public AbsGraphicList<T> create(boolean _simple) {
        return new GraphicList<T>(_simple);
    }

}
