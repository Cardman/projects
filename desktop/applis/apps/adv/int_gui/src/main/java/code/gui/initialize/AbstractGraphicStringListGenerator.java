package code.gui.initialize;

import code.gui.AbsGraphicList;
import code.gui.images.AbstractImageFactory;
import code.util.Ints;
import code.util.StringList;

public interface AbstractGraphicStringListGenerator {
    AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo);
    AbsGraphicList<String> createMultStrList(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, StringList _objects, Ints _selectedIndexes, int _visibleRows);
}
