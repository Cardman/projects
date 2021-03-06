package code.gui.document;

import java.awt.*;
import java.awt.image.BufferedImage;

import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaStyle;
import code.formathtml.render.SegmentPart;
import code.gui.CustGraphics;
import code.gui.PreparedLabel;

public final class DualAnchoredLabel extends DualLabel {

    private String href;

    public DualAnchoredLabel(DualContainer _container, MetaAnchorLabel _component, RenderedPage _page) {
        super(_container, _component, _page);
        PreparedLabel label_ = getLabel();
        label_.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label_.addMouseListener(new AnchorEvent(_component.getAnchor(), _page, this));
        String prefix_ = getPage().getNavigation().getSession().getPrefix();
        String command_ = new StringBuilder(prefix_).append("command").toString();
        command_ = _component.getAnchor().getAttribute(command_);
        if (!command_.isEmpty()) {
            href = command_;
        } else if (!_component.getAnchor().getAttribute("href").isEmpty()) {
            href = _component.getAnchor().getAttribute("href");
        } else {
            href = "";
        }
    }

    public String getHref() {
        return href;
    }

    @Override
    public void paint() {
        PreparedLabel lab_ = getLabel();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ =  newFont(style_);
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();
        String text_ = getText();
        int w_ = fontMetrics_.stringWidth(text_);
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(Color.ORANGE);
        for (SegmentPart s: getSegments()) {
            int beginIndex_ = s.getBegin();
            int b_ = fontMetrics_.stringWidth(text_.substring(0, beginIndex_));
            int d_ = fontMetrics_.stringWidth(text_.substring(beginIndex_, s.getEnd()));
            gr_.fillRect(b_, 0, d_, h_);
        }
        gr_.setColor(Color.BLUE);
        gr_.drawString(text_, 0, h_ - 1);
        gr_.drawLine(0, h_ - 1, w_, h_ - 1);
        lab_.setIcon(img_);
        gr_.dispose();
    }
}
