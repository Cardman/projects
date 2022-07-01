package code.gui;


import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class WrappedLabel {

    private static final char LINE_RETURN = '\n';
    private final StringList lines = new StringList();
    private final AbsPaintableLabel paintableLabel;

    public WrappedLabel(AbstractImageFactory _fact, String _text, AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel();
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
        int h_ = paintableLabel.heightFont() * nbLines_;
        int w_ = IndexConstants.SIZE_EMPTY;
        for (String l: lines) {
            int wLine_ = paintableLabel.stringWidth(l);
            if (wLine_ > w_) {
                w_ = wLine_;
            }
        }
        if (w_ <= 0) {
            paintableLabel.setEmptyIcon();
            return;
        }
        paintableLabel.setPreferredSize(new MetaDimension(w_, h_));
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
        img_.setFont(paintableLabel);
        paintComponent(img_);
        paintableLabel.setIcon(_fact,img_);
    }

    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }

    public void paintComponent(AbstractImage _g) {
        _g.setColorBg(getPaintableLabel());
        _g.fillRect(0, 0, paintableLabel.getWidth(), paintableLabel.getHeight());
        _g.setColorFg(getPaintableLabel());
        int hLine_ = paintableLabel.heightFont();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String l: lines) {
            _g.drawString(l, 0, hLine_ * (i_ + 1));
            i_++;
        }
    }
}
