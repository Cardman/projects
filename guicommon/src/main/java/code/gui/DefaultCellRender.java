package code.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;

public final class DefaultCellRender extends CustCellRender {

    private int maxWidth;

    private JLabel label;
    private String text;
    private boolean selected;

    @Override
    public JLabel getListCellRendererComponent(GraphicListable _list, Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus) {
        text = (String) _value;
        JLabel label_ = (JLabel) _list.getListComponents().get(_index);
        label = label_;
        Font font_ = label.getFont();
        FontMetrics fontMetrics_ = label.getFontMetrics(font_);
        maxWidth = Math.max(maxWidth,fontMetrics_.stringWidth(text));
        selected = _isSelected;
        return label_;
    }
    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int _maxWidth) {
        maxWidth = _maxWidth;
    }

    @Override
    public int getWidth() {
        return maxWidth;
    }

    @Override
    public void paintComponent(Graphics _g) {
        Font font_ = label.getFont();
        FontMetrics fontMetrics_ = label.getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(text);
        if (selected) {
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getMaxWidth(), h_, Color.WHITE, Color.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getMaxWidth(), h_, Color.BLACK, Color.WHITE);
        }
    }
    @Override
    public int getHeight() {
        Font font_ = label.getFont();
        FontMetrics fontMetrics_ = label.getFontMetrics(font_);
        return fontMetrics_.getHeight();
    }
}
