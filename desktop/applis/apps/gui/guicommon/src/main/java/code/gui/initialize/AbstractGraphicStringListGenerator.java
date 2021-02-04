package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.util.Ints;
import code.util.StringList;

public interface AbstractGraphicStringListGenerator {
    AbsGraphicList<String> createStrList(StringList _objects);
    AbsInputGraphicList<String> createMultStrList(StringList _objects, Ints _selectedIndexes, int _visibleRows);
}
