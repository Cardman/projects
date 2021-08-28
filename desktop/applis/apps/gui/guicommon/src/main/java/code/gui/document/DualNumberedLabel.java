package code.gui.document;

import java.awt.Color;


import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaStyle;
import code.gui.AbsPreparedLabel;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

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
        AbsPreparedLabel lab_ = getLabel();
        MetaStyle style_ = getComponent().getStyle();
        MetaFont copy_ =  newFont(style_);
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
