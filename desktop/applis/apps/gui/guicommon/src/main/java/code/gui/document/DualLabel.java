package code.gui.document;

import java.awt.*;
import java.awt.image.BufferedImage;

import code.formathtml.render.MetaLabel;
import code.formathtml.render.MetaStyle;
import code.formathtml.render.SegmentPart;
import code.gui.CustComponent;
import code.gui.CustGraphics;
import code.gui.PreparedLabel;
import code.util.CustList;

public abstract class DualLabel extends DualLeaf {

    private final CustList<SegmentPart> segments = new CustList<SegmentPart>();

    private final String text;
    private final PreparedLabel label;

    public DualLabel(DualContainer _container, MetaLabel _component, RenderedPage _page) {
        super(_container, _component, _page);
        text = _component.getText();
        label = new PreparedLabel();
        updateGraphics(label,_component);
    }

    public final void clearSegments() {
        segments.clear();
    }

    public final void addSegment(SegmentPart _segment) {
        segments.add(_segment);
    }

    public CustList<SegmentPart> getSegments() {
        return segments;
    }

    @Override
    protected void postAdd() {
        Color bGr_ = label.getParent().getBackground();
        Color fGr_ = label.getParent().getForeground();
        label.setBackground(bGr_);
        label.setForeground(fGr_);
        paint();
    }

    @Override
    public CustComponent getGraphic() {
        return getLabel();
    }

    public PreparedLabel getLabel() {
        return label;
    }

    public void paint() {
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ = newFont(style_);
        int h_ = label.heightFont(copy_);
        int w_ = label.stringWidth(copy_,text);
        if (w_ == 0) {
            w_ = label.stringWidth(copy_," ");
        }
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(Color.ORANGE);
        for (SegmentPart s: segments) {
            int beginIndex_ = s.getBegin();
            int b_ = label.stringWidth(copy_,text.substring(0, beginIndex_));
            int d_ = label.stringWidth(copy_,text.substring(beginIndex_, s.getEnd()));
            gr_.fillRect(b_, 0, d_, h_);
        }
        gr_.setColor(new Color(style_.getFgColor()));
        gr_.drawString(text, 0, h_ - 1);
        label.setIcon(img_);
        gr_.dispose();
    }

    public String getText() {
        return text;
    }
    protected static Font newFont(MetaStyle _style) {
        String fontFamily_ = _style.getFontFamily();
        int realSize_ = _style.getRealSize();
        if (_style.getBold() == Font.BOLD) {
            if (_style.getItalic() == Font.ITALIC) {
                return new Font(fontFamily_,Font.BOLD+Font.ITALIC,realSize_);
            }
            return new Font(fontFamily_,Font.BOLD,realSize_);
        }
        if (_style.getItalic() == Font.ITALIC) {
            return new Font(fontFamily_,Font.ITALIC,realSize_);
        }
        return new Font(fontFamily_,Font.PLAIN,realSize_);
    }
}
