package code.gui;

import java.awt.Font;
import java.awt.FontMetrics;

import code.util.Ints;
import code.util.StringList;

public class GraphicStringList extends GraphicList<String> implements Input {

    public GraphicStringList(boolean _simple, StringList _objects) {
        this(_simple, _objects, new Ints());
    }

    public GraphicStringList(boolean _simple, StringList _objects, Ints _selectedIndexes) {
        super(_simple, _selectedIndexes, _objects);
    }

    public GraphicStringList(boolean _simple, StringList _objects, Ints _selectedIndexes, int _visibleRows) {
        super(_simple, _selectedIndexes, _objects,_visibleRows);
    }

    @Override
    protected void buildList() {
        DefaultCellRender render_ = new DefaultCellRender();
        render_.setMaxWidth(getMaxWidth());
        setRender(render_);
        super.buildList();
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
        Font font_ = panel_.getFont();
        FontMetrics fontMetics_ = panel_.getFontMetrics(font_);
        int width_ = 4;
        for (String s: getList()) {
            width_ = Math.max(width_, fontMetics_.stringWidth(s));
        }
        return width_;
    }

    @Override
    public CustComponent getGlobal() {
        return getScroll();
    }

}
