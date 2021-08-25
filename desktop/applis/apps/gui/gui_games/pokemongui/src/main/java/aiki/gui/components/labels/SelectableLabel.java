package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Font;

import aiki.gui.components.Paginator;
import code.gui.AbsMetaLabel;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;

public abstract class SelectableLabel extends AbsMetaLabel {

    protected static final int HEIGTH_CHARS = Paginator.HEIGTH_CHARS;

    private boolean selected;

    protected SelectableLabel(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        initFont();
    }

    private void initFont() {
        Font f_ = getFont();
        String name_ = f_.getName();
        int style_ = f_.getStyle();
        setFont(new Font(name_, style_, HEIGTH_CHARS));
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }
    }
}
