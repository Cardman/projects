package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import aiki.gui.components.Paginator;
import code.gui.PaintableLabel;
import code.util.CustList;
import code.util.PairNumber;

public class Header extends PaintableLabel {

    private CustList<WordPoint> strings = new CustList<WordPoint>();

    public Header() {
        Font f_ = getFont();
        String name_ = f_.getName();
        int style_ = f_.getStyle();
        setFont(new Font(name_, style_, Paginator.HEIGTH_CHARS));
    }

    public int width(String _string) {
        return getFontMetrics(getFont()).stringWidth(_string);
    }

    public void addString(String _text, int _x) {
        strings.add(new WordPoint(_text, new PairNumber<Integer, Integer>(_x, Paginator.HEIGTH_CHARS)));
    }

    public void addString(String _text, int _x, int _y) {
        strings.add(new WordPoint(_text, new PairNumber<Integer, Integer>(_x, Paginator.HEIGTH_CHARS+_y)));
    }

    public void clearStrings() {
        strings.clear();
    }

    @Override
    public void paintComponent(Graphics _g) {
        _g.setColor(Color.YELLOW);
        _g.fillRect(0,0,getWidth(),getHeight());
        _g.setColor(Color.BLACK);
        for (WordPoint p: strings) {
            _g.drawString(p.getWord(), p.getPoint().getFirst(), p.getPoint().getSecond());
        }
    }
}
