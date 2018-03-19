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
import code.formathtml.render.MetaStyle;
import code.formathtml.render.SegmentPart;

public final class DualAnchoredLabel extends DualLabel {

    private String href;

    public DualAnchoredLabel(DualContainer _container, MetaAnchorLabel _component, RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        JLabel label_ = getGraphic();
        label_.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public void paint() {
        JLabel lab_ = getGraphic();
        MetaStyle style_ = getComponent().getStyle();
        Font copy_ = new Font(style_.getFontFamily(), style_.getBold() + style_.getItalic(), style_.getRealSize());
        FontMetrics fontMetrics_ = lab_.getFontMetrics(copy_);
        int h_ = fontMetrics_.getHeight();
        String text_ = getComponent().getText();
        int w_ = fontMetrics_.stringWidth(text_);
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(copy_);
        gr_.setColor(new Color(style_.getBgColor()));
        gr_.fillRect(0, 0, w_, h_);
        gr_.setColor(Color.ORANGE);
        for (SegmentPart s: getSegments()) {
            int beginIndex_ = s.getBegin();
            int b_ = fontMetrics_.stringWidth(text_.substring(0, beginIndex_));
            int d_ = fontMetrics_.stringWidth(text_.substring(beginIndex_, s.getEnd()));
            gr_.fillRect(b_, 0, d_, h_);
        }
        gr_.setColor(Color.BLUE);
        gr_.drawString(text_, 0, h_ - 1);
        gr_.drawLine(0, h_ - 1, w_, h_ - 1);
        lab_.setIcon(new ImageIcon(img_));
        gr_.dispose();
    }
}
