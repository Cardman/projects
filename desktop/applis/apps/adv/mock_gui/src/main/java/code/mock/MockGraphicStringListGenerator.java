package code.mock;

import code.gui.AbsGraphicList;
import code.util.Ints;
import code.util.StringList;

public final class MockGraphicStringListGenerator {

    public AbsGraphicList<String> createStrList(StringList _objects) {
        MockCustGrList<String> l_ = new MockCustGrList<String>();
        feed(_objects,l_);
        return l_;
    }

    public AbsGraphicList<String> createMultStrList(StringList _objects, Ints _selectedIndexes) {
        MockCustGrList<String> gr_ = new MockCustGrList<String>();
        feed(_objects,gr_);
        gr_.setSelectedIndexes(_selectedIndexes);
        return gr_;
    }

    public static void feed(StringList _objects, AbsGraphicList<String> _l) {
        for (String s: _objects) {
            _l.add(s);
        }
    }
}
