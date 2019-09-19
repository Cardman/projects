package code.gui.document;

import java.awt.*;
import java.awt.image.BufferedImage;

import code.formathtml.render.MetaPointForm;
import code.formathtml.render.MetaPointLabel;
import code.formathtml.render.MetaStyle;
import code.gui.CustGraphics;
import code.gui.PreparedLabel;

public final class DualMetaPointLabel extends DualLabel {

    private MetaPointForm form;
    public DualMetaPointLabel(DualContainer _container, MetaPointLabel _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        getLabel().setOpaque(true);
        form = _component.getForm();
    }

    @Override
    public void paint() {
        PreparedLabel lab_ = getLabel();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ =  newFont(style_);
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();
        int w_ = h_;
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(new Color(style_.getFgColor()));
        if (form == MetaPointForm.DISK) {
            gr_.fillOval(0, 0, w_, h_);
        } else if (form == MetaPointForm.CIRCLE) {
            gr_.drawOval(0, 0, w_, h_);
        } else if (form == MetaPointForm.SQUARRE) {
            gr_.fillRect(2, 2, w_ - 4, h_ - 4);
        } else {
            gr_.drawRect(2, 2, w_ - 4, h_ - 4);
        }
        lab_.setIcon(img_);
        gr_.dispose();
    }
}
