package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.AbsGraphicListPainter;

public interface AbstractGraphicListGenerator<T> {
    AbsGraphicList<T> create(boolean _simple);
    AbsGraphicList<T> create(boolean _simple, AbsGraphicListPainter _abs);
}
