package code.gui.document;

import java.awt.*;

import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaStyle;
import code.gui.PreparedLabel;
import code.gui.images.AbstractImage;

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
        int h_ = lab_.heightFont(copy_);
        String tr_ = new StringBuilder(number).append("  ").toString();
        int diff_ = lab_.stringWidth(copy_,tr_);
        AbstractImage img_ = getPage().getGene().getImageFactory().newImageRgb(diff_, h_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(copy_);
        img_.setColor(new Color(style_.getBgColor()));
        img_.fillRect(0, 0, diff_, h_);
        img_.setColor(new Color(style_.getFgColor()));
        img_.drawString(tr_, 0, h_ - 1);
        lab_.setIcon(getPage().getGene().getImageFactory(), img_);
        img_.dispose();
    }
}
