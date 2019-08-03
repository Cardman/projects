package code.gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

public final class TextLabel extends PaintableLabel {

    private String text;

    public TextLabel(String _text) {
        text = _text;
        Font font_ = getFont();
        FontMetrics fontMetrics_ = getFontMetrics(font_);
        int w_ = fontMetrics_.stringWidth(text)+2;
        JLabel lab_ = getLabel();
        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(lab_, text, w_, getForeground(), getBackground());
        lab_.setIcon(new ImageIcon(img_));
    }

    public TextLabel(String _titre, int _align) {
        this(_titre);
        setHorizontalAlignment(_align);
    }

    @Override
    public void paintComponent(CustGraphics _g2) {
        Font font_ = getFont();
        FontMetrics fontMetrics_ = getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(text);
        LabelButtonUtil.paintDefaultLabel(_g2, text, w_, getWidth(), h_, getForeground(), getBackground());
    }

    @Override
    public int getHeight() {
        Font font_ = getFont();
        FontMetrics fontMetrics_ = getFontMetrics(font_);
        return fontMetrics_.getHeight();
    }

    @Override
    public int getWidth() {
        Font font_ = getFont();
        FontMetrics fontMetrics_ = getFontMetrics(font_);
        return fontMetrics_.stringWidth(text)+2;
    }

    public void setText(String _simpleNumberFormat) {
        text = _simpleNumberFormat;
        Font font_ = getFont();
        FontMetrics fontMetrics_ = getFontMetrics(font_);
        int w_ = fontMetrics_.stringWidth(text)+2;
        JLabel lab_ = getLabel();
        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(lab_, text, w_, getForeground(), getBackground());
        lab_.setIcon(new ImageIcon(img_));
    }

    public String getText() {
        return text;
    }
}
