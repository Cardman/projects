package code.sys.impl;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.gui.FrameUtil;
import code.sys.impl.other.CustGrList;
import code.sys.impl.other.CustGrMultList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class AdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        CustGrList<String> l_ = new CustGrList<>(true);
        FrameUtil.feed(l_,_objects);
        return l_;
    }

    @Override
    public AbsInputGraphicList<String> createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        CustGrMultList l_ = new CustGrMultList(false);
        FrameUtil.feed(l_,_objects);
        return l_;
    }
}
