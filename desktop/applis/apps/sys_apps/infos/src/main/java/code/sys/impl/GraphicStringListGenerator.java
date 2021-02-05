package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.gui.GraphicStringList;
import code.gui.GraphicStringListMult;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class GraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(StringList _objects) {
        return new GraphicStringList(_objects);
    }

    @Override
    public AbsInputGraphicList<String> createMultStrList(StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        return new GraphicStringListMult(_objects, _selectedIndexes, _visibleRows);
    }
}
