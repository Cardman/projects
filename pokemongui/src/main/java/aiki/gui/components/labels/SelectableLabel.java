package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import aiki.gui.components.Paginator;
import code.gui.PaintableLabel;

public class SelectableLabel extends PaintableLabel {

    protected static final int HEIGTH_CHARS = Paginator.HEIGTH_CHARS;

    private boolean selected;

    protected SelectableLabel() {
        Font f_ = getFont();
        String name_ = f_.getName();
        int style_ = f_.getStyle();
        setFont(new Font(name_, style_, HEIGTH_CHARS));
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(Graphics _g) {
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
