package code.gui;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class TextLabel extends PaintableLabel {

    private String text;

    public TextLabel(String _text) {
        text = _text;
        int w_ = stringWidth(text)+2;
        JLabel lab_ = getLabel();
        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(this, text, w_, getForeground(), getBackground());
        lab_.setIcon(new ImageIcon(img_));
    }

    public TextLabel(String _titre, int _align) {
        this(_titre);
        setHorizontalAlignment(_align);
    }

    @Override
    public void paintComponent(CustGraphics _g2) {
        int h_ = heightFont();
        int w_ = stringWidth(text);
        LabelButtonUtil.paintDefaultLabel(_g2, text, w_, getWidth(), h_, getForeground(), getBackground());
    }

    @Override
    public int getHeight() {
        return heightFont();
    }

    @Override
    public int getWidth() {
        return stringWidth(text)+2;
    }

    public void setText(String _simpleNumberFormat) {
        text = _simpleNumberFormat;
        int w_ = stringWidth(text)+2;
        JLabel lab_ = getLabel();
        BufferedImage img_ = LabelButtonUtil.paintDefaultLabel(this, text, w_, getForeground(), getBackground());
        lab_.setIcon(new ImageIcon(img_));
    }

    public String getText() {
        return text;
    }
}
