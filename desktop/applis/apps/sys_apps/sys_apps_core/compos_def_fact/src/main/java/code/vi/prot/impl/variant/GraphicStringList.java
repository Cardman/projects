package code.vi.prot.impl.variant;

import java.awt.*;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.vi.prot.impl.gui.Panel;
import code.util.Ints;
import code.util.StringList;

public final class GraphicStringList extends GraphicList<String> implements AbsGraphicStringList, Input {

    private final StringList elements;
    private int heightList = 1;
    private final AbstractImageFactory fact;
    private final AbsCompoFactory compoFactory;
    public GraphicStringList(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, StringList _objects) {
        this(_fact,_compoFactory,true, _objects, new Ints());
    }

    private GraphicStringList(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, boolean _simple, StringList _objects, Ints _selectedIndexes) {
        super(_simple, _selectedIndexes,_objects, _objects, new DefaultGraphicListPainter(_fact), new DefaultCellRender(_fact, Panel.newPageBox()));
        fact = _fact;
        compoFactory = _compoFactory;
        elements = _objects;
        setList(elements);
        rebuild();
    }

    @Override
    protected void repaintAdded(int _index) {
        repaintSelect(_index,false);
    }

    @Override
    protected void repaintAll() {
        LabelButtonUtil.repAll(this);
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    @Override
    public int getHeightList() {
        return heightList;
    }

    @Override
    public void setHeightList(int _height) {
        this.heightList = _height;
    }

    public AbstractImageFactory getFact() {
        return fact;
    }

    public void repaintSelect(int _index, boolean _sel) {
        AbstractImage buff_ = LabelButtonUtil.repaintSelected(_index, _sel, this, (DefaultCellRender)getSimpleRender());
        AbsPreparedLabel lab_ = getListComponents().get(_index);
        lab_.setIcon(fact,buff_);
    }

    public StringList getElements() {
        return elements;
    }

    @Override
    protected IndexableListener buildSingleSelect(AbsPreparedLabel _lab, int _index) {
        SimpleSelectCombo i_ = new SimpleSelectCombo(this, _index);
        _lab.addMouseListener(i_);
        return i_;
    }

    @Override
    public int getMaxWidth() {
        return FrameUtil.maxWidth(this,getList());
    }
    @Override
    protected void resetDimensions(){
        int width_ = getMaxWidth();
        int c_ = getListComponents().size();
        getScroll().setPreferredSize(new Dimension(width_ + 24, 2+heightList * Math.min(c_, getVisibleRowCount())));
        getScroll().revalidate();
    }
    @Override
    public AbsCustComponent getGlobal() {
        return getScroll();
    }

}
