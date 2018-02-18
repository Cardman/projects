package code.gui.document;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaNumberedLabel;

public final class DualNumberedLabel extends DualLabel {

    public DualNumberedLabel(DualContainer _container, MetaNumberedLabel _component,
            RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        getGraphic().setOpaque(true);
        WindowPage._texts_.put(getGraphic(), Integer.toString(_component.getNumber()));
    }

    @Override
    public MetaNumberedLabel getComponent() {
        return (MetaNumberedLabel) super.getComponent();
    }

    @Override
    public void paint() {
        JLabel lab_ = getGraphic();
        Font font_ = lab_.getFont();
        FontMetrics fontMetrics_ = lab_.getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(Integer.toString(getComponent().getNumber()));
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(font_);
        gr_.setColor(lab_.getBackground());
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(lab_.getForeground());
        gr_.drawString(Integer.toString(getComponent().getNumber()), 0, h_ - 1);
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
