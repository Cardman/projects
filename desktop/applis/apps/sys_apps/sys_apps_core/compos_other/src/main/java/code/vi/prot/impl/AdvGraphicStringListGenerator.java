package code.vi.prot.impl;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.gui.DefaultCellRender;
import code.gui.FrameUtil;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.other.CustGrList;
import code.vi.prot.impl.other.CustGrMultList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;

public final class AdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        CustGrList<String> l_ = new CustGrList<>(true, new DefaultCellRender(_fact, Panel.newPageBox()));
        FrameUtil.feed(l_,_objects);
        return l_;
    }

    @Override
    public AbsInputGraphicList<String> createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        CustGrMultList l_ = new CustGrMultList(false,_fact);
        FrameUtil.feed(l_,_objects);
        return l_;
    }
}
