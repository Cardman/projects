package code.gui.document;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaLabel;

public abstract class DualLabel extends DualLeaf {

    public DualLabel(DualContainer _container, MetaLabel _component, JLabel _label, RenderedPage _page) {
        super(_container, _component, _label, _page);
    }

    public String getHref() {
        return "";
    }

    @Override
    protected void postAdd() {
        JLabel current_ = getGraphic();
        Color bGr_ = current_.getParent().getBackground();
        Color fGr_ = current_.getParent().getForeground();
        current_.setBackground(bGr_);
        current_.setForeground(fGr_);
        paint();
    }

    @Override
    public JLabel getGraphic() {
        return (JLabel) super.getGraphic();
    }

    @Override
    public MetaLabel getComponent() {
        return (MetaLabel) super.getComponent();
    }

    public void paint() {
        JLabel lab_ = getGraphic();
        Font font_ = lab_.getFont();
        FontMetrics fontMetrics_ = lab_.getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(getComponent().getText());
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(font_);
        gr_.setColor(lab_.getBackground());
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(lab_.getForeground());
        gr_.drawString(getComponent().getText(), 0, h_ - 1);
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
