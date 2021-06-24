package code.gui;

import code.gui.images.AbstractImageFactory;
import code.util.Ints;
import code.util.StringList;

import java.awt.*;

public final class GraphicStringListMult extends GraphicList<String> implements AbsInputGraphicList<String> {

    public GraphicStringListMult(AbstractImageFactory _fact, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        super(_fact,false, _selectedIndexes, _objects,_visibleRows);
        buildList(_fact);
        rebuild();
    }

    protected void buildList(AbstractImageFactory _fact) {
        DefaultCellRender cellRender_ = new DefaultCellRender(_fact);
        cellRender_.setMaxWidth(getMaxWidth());
        setRender(cellRender_);
    }

    @Override
    public int getMaxWidth() {
        Panel panel_ = getPanel();
        int width_ = 4;
        for (String s: getList()) {
            width_ = Math.max(width_, panel_.stringWidth(s));
        }
        return width_;
    }
    @Override
    protected void resetDimensions(){
        Panel panel_ = getPanel();
        int width_ = getMaxWidth();
        int c_ = getListComponents().size();
        getScroll().setPreferredSize(new Dimension(width_ + 24, 2+panel_.heightFont() * Math.min(c_, getVisibleRowCount())));
        getScroll().revalidate();
    }
    @Override
    public CustComponent getGlobal() {
        return getScroll();
    }

}
