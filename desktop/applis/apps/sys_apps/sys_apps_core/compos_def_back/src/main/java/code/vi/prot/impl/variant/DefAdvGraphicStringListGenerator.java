package code.vi.prot.impl.variant;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.util.Ints;
import code.util.StringList;
import code.vi.prot.impl.gui.Panel;

import javax.swing.*;

public final class DefAdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        DefCustGrList<String> l_ = new DefCustGrList<String>(ListSelectionModel.SINGLE_SELECTION, new DefaultCellRender(_fact, Panel.newPageBox()));
        FrameUtil.feed(l_,_objects);
        return l_;
    }

    @Override
    public AbsGraphicList<String> createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        DefCustGrList<String> gr_ = new DefCustGrList<String>(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION, new DefaultCellRender(_fact, Panel.newPageBox()));
        FrameUtil.feed(gr_,_objects);
        gr_.setSelectedIndexes(_selectedIndexes);
        return gr_;
    }
}
