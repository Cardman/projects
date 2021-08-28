package code.gui.document;

import java.awt.Color;


import code.formathtml.render.MetaAnchorLabel;
import code.formathtml.render.MetaStyle;
import code.formathtml.render.SegmentPart;
import code.gui.AbsPreparedLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

public final class DualAnchoredLabel extends DualLabel {

    private String href;

    public DualAnchoredLabel(DualContainer _container, MetaAnchorLabel _component, RenderedPage _page) {
        super(_container, _component, _page);
        AbsPreparedLabel label_ = getLabel();
        label_.setCursor(GuiConstants.HAND_CURSOR);
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
        AbsPreparedLabel lab_ = getLabel();
        MetaStyle style_ = getComponent().getStyle();
        MetaFont copy_ =  newFont(style_);
        int h_ = lab_.heightFont(copy_);
        String text_ = getText();
        int w_ = lab_.stringWidth(copy_,text_);
        AbstractImage img_ = getPage().getGene().getImageFactory().newImageRgb(w_, h_);
        img_.setFont(copy_);
        img_.setColor(new Color(style_.getBgColor()));
        img_.fillRect(0, 0, w_, h_);
        img_.setColor(Color.ORANGE);
        for (SegmentPart s: getSegments()) {
            int beginIndex_ = s.getBegin();
            int b_ = lab_.stringWidth(copy_,text_.substring(0, beginIndex_));
            int d_ = lab_.stringWidth(copy_,text_.substring(beginIndex_, s.getEnd()));
            img_.fillRect(b_, 0, d_, h_);
        }
        img_.setColor(Color.BLUE);
        img_.drawString(text_, 0, h_ - 1);
        img_.drawLine(0, h_ - 1, w_, h_ - 1);
        lab_.setIcon(getPage().getGene().getImageFactory(), img_);
        img_.dispose();
    }
}
