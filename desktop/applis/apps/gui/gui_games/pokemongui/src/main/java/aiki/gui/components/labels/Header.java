package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Font;

import aiki.gui.components.Paginator;
import code.gui.AbsMetaLabel;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.images.IntPoint;
import code.util.CustList;

public final class Header extends AbsMetaLabel {

    private CustList<WordPoint> strings = new CustList<WordPoint>();

    public Header(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        MetaFont f_ = getMetaFont();
        String name_ = f_.getFontFamily();
        int style_ = f_.getFont();
        setFont(name_, style_, Paginator.HEIGTH_CHARS);
    }

    public int width(String _string) {
        return stringWidth(_string);
    }

    public void addString(String _text, int _x) {
        strings.add(new WordPoint(_text, new IntPoint(_x, Paginator.HEIGTH_CHARS)));
    }

    public void addString(String _text, int _x, int _y) {
        strings.add(new WordPoint(_text, new IntPoint(_x, Paginator.HEIGTH_CHARS+_y)));
    }

    public void clearStrings() {
        strings.clear();
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.YELLOW);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(Color.BLACK);
        for (WordPoint p: strings) {
            _g.drawString(p.getWord(), p.getPoint().getXcoords(), p.getPoint().getYcoords());
        }
    }
}
