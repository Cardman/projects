package code.gui;
import java.awt.Dimension;
import java.awt.FontMetrics;

import code.util.CustList;
import code.util.StringList;

public class WrappedLabel extends PaintableLabel {

    private static final char LINE_RETURN = '\n';
    private StringList lines = new StringList();

    public WrappedLabel() {
    }

    public WrappedLabel(String _text) {
        lines.addAllElts(StringList.splitChars(_text, LINE_RETURN));
        setPreferredSize();
    }

    public void setLabelText(String _text) {
        lines.clear();
        lines.addAllElts(StringList.splitChars(_text, LINE_RETURN));
        setPreferredSize();
    }

    public void setPreferredSize() {
        FontMetrics f_ = getFontMetrics(getFont());
        int nbLines_ = lines.size() + 1;
        int h_ = f_.getHeight() * nbLines_;
        int w_ = CustList.SIZE_EMPTY;
        for (String l: lines) {
            int wLine_ = f_.stringWidth(l);
            if (wLine_ > w_) {
                w_ = wLine_;
            }
        }
        setPreferredSize(new Dimension(w_, h_));
        repaint();
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(getBackground());
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(getForeground());
        FontMetrics f_ = getFontMetrics(getFont());
        int hLine_ = f_.getHeight();
        int i_ = CustList.FIRST_INDEX;
        for (String l: lines) {
            _g.drawString(l, 0, hLine_ * (i_ + 1));
            i_++;
        }
    }
}
