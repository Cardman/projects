package code.gui.document;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import code.formathtml.render.MetaAnchorLabel;
import code.util.CustList;

public final class DualAnchoredLabel extends DualLabel {

    private String href;

    public DualAnchoredLabel(DualContainer _container, MetaAnchorLabel _component, RenderedPage _page, CustList<DualAnimatedImage> _anims) {
        super(_container, _component, new JLabel(), _page);
        JLabel label_ = getGraphic();
        label_.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label_.addMouseListener(new AnchorEvent(_component.getAnchor(), _page, _anims, this));
        WindowPage._texts_.put(getGraphic(), _component.getText());
        if (!_component.getAnchor().getAttribute("command").isEmpty()) {
            href = _component.getAnchor().getAttribute("command");
        } else if (!_component.getAnchor().getAttribute("href").isEmpty()) {
            href = _component.getAnchor().getAttribute("href");
        } else {
            href = "";
        }
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
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
        gr_.setColor(Color.BLUE);
        gr_.drawString(getComponent().getText(), 0, h_ - 1);
        gr_.drawLine(0, h_ - 1, w_, h_ - 1);
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
