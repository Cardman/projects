package code.vi.prot.impl;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;
import code.vi.prot.impl.gui.Panel;
import code.vi.prot.impl.other.CustGrList;

import javax.swing.*;

public final class AdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        CustGrList<String> l_ = new CustGrList<>(ListSelectionModel.SINGLE_SELECTION, new DefaultCellRender(_fact, Panel.newPageBox()));
        FrameUtil.feed(l_,_objects);
        return l_;
    }

    @Override
    public Input createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        CustGrList<String> gr_ = new CustGrList<>(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION, new DefaultCellRender(_fact, Panel.newPageBox()));
        GrMultList l_ = new GrMultList(gr_);
        FrameUtil.feed(l_,_objects);
        gr_.setSelectedIndexes(_selectedIndexes);
        return l_;
    }
}
