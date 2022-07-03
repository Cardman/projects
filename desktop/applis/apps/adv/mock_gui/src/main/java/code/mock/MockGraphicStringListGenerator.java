package code.mock;

import code.gui.AbsGraphicList;
import code.gui.Input;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class MockGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _i, StringList _objects, AbsCompoFactory _compo) {
        MockCustGrList<String> l_ = new MockCustGrList<String>();
        feed(_objects,l_);
        return l_;
    }

    @Override
    public Input createMultStrList(AbstractImageFactory _i, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        MockCustGrMultList l_ = new MockCustGrMultList();
        feed(_objects,l_);
        return l_;
    }

    public static void feed(StringList _objects, AbsGraphicList<String> _l) {
        for (String s: _objects) {
            _l.add(s);
        }
    }
}
