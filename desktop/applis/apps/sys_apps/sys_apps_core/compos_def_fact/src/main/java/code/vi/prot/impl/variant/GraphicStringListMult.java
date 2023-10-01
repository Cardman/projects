package code.vi.prot.impl.variant;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;
import code.vi.prot.impl.gui.Panel;
import code.util.Ints;
import code.util.StringList;

import java.awt.*;

public final class GraphicStringListMult extends GraphicList<String> implements Input,AbsGraphicList<String> {

    public GraphicStringListMult(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        super(_fact,false, _selectedIndexes,_objects, _objects,_visibleRows, new DefaultCellRender(_fact,_compoFactory, Panel.newPageBox()));
        rebuild();
    }

    @Override
    public int getMaxWidth() {
        return SelectionUtil.maxWidth(this,getList(),((DefaultCellRender)getSimpleRender()).getCompo());
    }

    @Override
    protected void resetDimensions(){
        AbsPanel panel_ = getPanel();
        int width_ = getMaxWidth();
        int c_ = getListComponents().size();
        getScroll().setPreferredSize(new Dimension(width_ + 24, 2+((DefaultCellRender)getSimpleRender()).getCompo().heightFont(panel_.getMetaFont()) * NumberUtil.min(c_, getVisibleRowCount())));
        getScroll().revalidate();
    }

}
