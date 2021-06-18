package code.gui;

import java.awt.*;
import java.awt.image.BufferedImage;

import code.util.Ints;
import code.util.StringList;

public final class GraphicStringList extends GraphicList<String> implements Input {

    private DefaultCellRender cellRender;
    private final StringList elements;
    private int height = 1;
    public GraphicStringList(StringList _objects) {
        this(true, _objects, new Ints());
    }

    private GraphicStringList(boolean _simple, StringList _objects, Ints _selectedIndexes) {
        super(_simple, _selectedIndexes, _objects, new DefaultGraphicListPainter());
        buildList();
        elements = _objects;
        setList(elements);
        rebuild();
    }

    private void buildList() {
        cellRender = new DefaultCellRender();
        cellRender.setMaxWidth(getMaxWidth());
        setRender(cellRender);
    }

    @Override
    protected void repaintAdded(int _index) {
        repaintSelect(_index,false);
    }

    @Override
    protected void repaintAll() {
        Panel panel_ = getPanel();
        int len_ = elements.size();
        for (int i = 0; i < len_; i++) {
            BufferedImage buff_ = repaintSelected(i, getSelectedIndexes().containsObj(i));
            PreparedLabel lab_ = new PreparedLabel(buff_);
            getListComponents().add(lab_);
            panel_.add(lab_);
        }
    }

    void repaintSelect(int _index, boolean _sel) {
        BufferedImage buff_ = repaintSelected(_index, _sel);
        PreparedLabel lab_ = getListComponents().get(_index);
        lab_.setIcon(buff_);
    }

    private BufferedImage repaintSelected(int _index, boolean _sel) {
        String elt_ = elements.get(_index);
        Panel panel_ = getPanel();
        Font font_ = panel_.getFont();
        height = Math.max(height,panel_.heightFont());
        cellRender.setMaxWidth(Math.max(cellRender.getMaxWidth(),panel_.stringWidth(elt_)));
        BufferedImage buff_ = new BufferedImage(cellRender.getWidth(),panel_.heightFont(),BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(buff_.getGraphics());
        gr_.setFont(font_);
        int h_ = panel_.heightFont();
        int w_ = panel_.stringWidth(elt_);
        if (_sel) {
            LabelButtonUtil.paintDefaultLabel(gr_, elt_, w_, cellRender.getMaxWidth(), h_, Color.WHITE, Color.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(gr_, elt_, w_, cellRender.getMaxWidth(), h_, Color.BLACK, Color.WHITE);
        }
        return buff_;
    }

    @Override
    protected IndexableListener buildSingleSelect(PreparedLabel _lab, int _index) {
        SimpleSelectCombo i_ = new SimpleSelectCombo(this, _index);
        _lab.addMouseListener(i_);
        return i_;
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
        int width_ = getMaxWidth();
        int c_ = getListComponents().size();
        getScroll().setPreferredSize(new Dimension(width_ + 24, 2+height * Math.min(c_, getVisibleRowCount())));
        getScroll().revalidate();
    }
    @Override
    public CustComponent getGlobal() {
        return getScroll();
    }

}
