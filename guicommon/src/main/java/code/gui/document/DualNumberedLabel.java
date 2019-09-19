package code.gui.document;

import java.awt.*;
import java.awt.image.BufferedImage;

import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaStyle;
import code.gui.CustGraphics;
import code.gui.PreparedLabel;

public final class DualNumberedLabel extends DualLabel {

    private String number;

    public DualNumberedLabel(DualContainer _container, MetaNumberedLabel _component,
                             RenderedPage _page) {
        super(_container, _component, _page);
        getLabel().setOpaque(true);
        number = _component.getNumber();
    }

    @Override
    public void paint() {
        PreparedLabel lab_ = getLabel();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ =  newFont(style_);
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();
        String tr_ = new StringBuilder(number).append("  ").toString();
        int diff_ = fontMetrics_.stringWidth(tr_);
        BufferedImage img_ = new BufferedImage(diff_, h_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, diff_, h_);
        gr_.setColor(new Color(style_.getFgColor()));
        gr_.drawString(tr_, 0, h_ - 1);
        lab_.setIcon(img_);
        gr_.dispose();
    }
}
