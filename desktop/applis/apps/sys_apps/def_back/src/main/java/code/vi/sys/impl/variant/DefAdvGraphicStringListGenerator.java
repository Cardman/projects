package code.vi.sys.impl.variant;

import code.gui.AbsGraphicList;
import code.gui.AbsInputGraphicList;
import code.gui.DefaultCellRender;
import code.gui.FrameUtil;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.vi.sys.impl.gui.Panel;
import code.util.Ints;
import code.util.StringList;

public final class DefAdvGraphicStringListGenerator implements AbstractGraphicStringListGenerator {
    @Override
    public AbsGraphicList<String> createStrList(AbstractImageFactory _fact, StringList _objects, AbsCompoFactory _compo) {
        DefCustGrList<String> l_ = new DefCustGrList<String>(true, new DefaultCellRender(_fact, Panel.newPageBox()));
        FrameUtil.feed(l_,_objects);
        return l_;
    }

    @Override
    public AbsInputGraphicList<String> createMultStrList(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        DefCustGrMultList l_ = new DefCustGrMultList(false,_fact);
        FrameUtil.feed(l_,_objects);
        return l_;
    }
}
