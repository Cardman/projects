package code.gui;
import java.awt.Dimension;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class WrappedLabel extends PaintableLabel {

    private static final char LINE_RETURN = '\n';
    private StringList lines = new StringList();

    public WrappedLabel() {
    }

    public WrappedLabel(AbstractImageFactory _fact,String _text) {
        lines.addAllElts(StringUtil.splitChars(_text, LINE_RETURN));
        setPreferredSize(_fact);
    }

//    public void setLabelText(String _text) {
//        lines.clear();
//        lines.addAllElts(StringUtil.splitChars(_text, LINE_RETURN));
//        setPreferredSize();
//    }

    public void setPreferredSize(AbstractImageFactory _fact) {
        int nbLines_ = lines.size() + 1;
        int h_ = heightFont() * nbLines_;
        int w_ = IndexConstants.SIZE_EMPTY;
        for (String l: lines) {
            int wLine_ = stringWidth(l);
            if (wLine_ > w_) {
                w_ = wLine_;
            }
        }
        setPreferredSize(new Dimension(w_, h_));
        repaintLabel(_fact);
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(getBackground());
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(getForeground());
        int hLine_ = heightFont();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String l: lines) {
            _g.drawString(l, 0, hLine_ * (i_ + 1));
            i_++;
        }
    }
}
