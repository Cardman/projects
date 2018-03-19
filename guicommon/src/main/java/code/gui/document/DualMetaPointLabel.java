package code.gui.document;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaPointForm;
import code.formathtml.render.MetaPointLabel;
import code.formathtml.render.MetaStyle;

public final class DualMetaPointLabel extends DualLabel {

    public DualMetaPointLabel(DualContainer _container, MetaPointLabel _component,
            RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        getGraphic().setOpaque(true);
    }

    @Override
    public MetaPointLabel getComponent() {
        return (MetaPointLabel) super.getComponent();
    }

    @Override
    public void paint() {
        JLabel lab_ = getGraphic();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ = new Font(style_.getFontFamily(), style_.getBold() + style_.getItalic(), style_.getRealSize());
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();getComponent().getForm();
        int w_ = h_;
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(new Color(style_.getFgColor()));
        if (getComponent().getForm() == MetaPointForm.DISK) {
            gr_.fillOval(0, 0, w_, h_);
        } else if (getComponent().getForm() == MetaPointForm.CIRCLE) {
            gr_.drawOval(0, 0, w_, h_);
        } else if (getComponent().getForm() == MetaPointForm.SQUARRE) {
            gr_.fillRect(2, 2, w_ - 4, h_ - 4);
        } else {
            gr_.drawRect(2, 2, w_ - 4, h_ - 4);
        }
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
