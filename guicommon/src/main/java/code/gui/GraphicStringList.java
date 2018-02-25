package code.gui;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import code.util.StringList;

public class GraphicStringList extends GraphicList<String> implements Input {

    public GraphicStringList(boolean _owned, boolean _simple, StringList _objects) {
        super(_owned, _simple, _objects.toArray());
    }

    @Override
    protected void buildList() {
        setRender(new CustCellRender());
        super.buildList();
    }
    @Override
    public int getMaxWidth() {
        JPanel panel_ = getPanel();
        Font font_ = panel_.getFont();
        FontMetrics fontMetics_ = panel_.getFontMetrics(font_);
        int width_ = 0;
        for (String s: getList()) {
            width_ = Math.max(width_, fontMetics_.stringWidth(s));
        }
        return width_;
    }

    @Override
    public JScrollPane getGlobal() {
        return getScroll();
    }

    @Override
    public StringList getSelectedValues() {
        StringList values_ = new StringList();
        for (int i: getSelectedIndexes()) {
            values_.add(getList().get(i));
        }
        return values_;
    }
}
