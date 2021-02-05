package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.gui.CustGrList;
import code.gui.CustGrMultList;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class AdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(StringList _objects) {
        CustGrList<String> l_ = new CustGrList<>(true);
        for (String s: _objects) {
            l_.add(s);
        }
        return l_;
    }

    @Override
    public AbsInputGraphicList<String> createMultStrList(StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        CustGrMultList l_ = new CustGrMultList(false);
        for (String s: _objects) {
            l_.add(s);
        }
        return l_;
    }
}
