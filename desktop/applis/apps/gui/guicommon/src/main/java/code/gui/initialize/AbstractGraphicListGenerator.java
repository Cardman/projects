package code.gui.initialize;

import code.gui.AbsGraphicList;

public interface AbstractGraphicListGenerator<T> {
    AbsGraphicList<T> create(boolean _simple);
}
