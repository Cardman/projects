package code.gui.document;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaNumberedLabel;
import code.formathtml.render.MetaStyle;

public final class DualNumberedLabel extends DualLabel {

    public DualNumberedLabel(DualContainer _container, MetaNumberedLabel _component,
            RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        getGraphic().setOpaque(true);
    }

    @Override
    public MetaNumberedLabel getComponent() {
        return (MetaNumberedLabel) super.getComponent();
    }

    @Override
    public void paint() {
        JLabel lab_ = getGraphic();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ = new Font(style_.getFontFamily(), style_.getBold() + style_.getItalic(), style_.getRealSize());
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();
        String tr_ = new StringBuilder(getComponent().getNumber()).append("  ").toString();
        int diff_ = fontMetrics_.stringWidth(tr_);
        BufferedImage img_ = new BufferedImage(diff_, h_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, diff_, h_);
        gr_.setColor(new Color(style_.getFgColor()));
        gr_.drawString(tr_, 0, h_ - 1);
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
