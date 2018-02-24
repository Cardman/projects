package code.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class LabelButtonUtil {

    public static final Color DEFAULT_FOREGROUND = new Color(0, 0, 127);
    public static final Color DISABLED = new Color(127, 127, 255);

    private LabelButtonUtil(){
    }

    public static void setTextDefaultColors(JLabel _label, String _text,
            boolean _enabled) {
        BufferedImage img_ = paintButton(_label, _text, _enabled);
        _label.setIcon(new ImageIcon(img_));
    }

    public static BufferedImage paintButton(JLabel _label, String _text,
            boolean _enabled) {
        return paintButton(_label, _text, _enabled, DEFAULT_FOREGROUND, DISABLED, Color.WHITE);
    }

    public static void setTextDefaultLabel(JLabel _label, String _text,
            Color _front, Color _back) {
        BufferedImage img_ = paintDefaultLabel(_label, _text, _front, _back);
        _label.setIcon(new ImageIcon(img_));
    }

    public static BufferedImage paintDefaultLabel(JLabel _label, String _text,
            Color _front, Color _back) {
        Font font_ = _label.getFont();
        FontMetrics fontMetrics_ = _label.getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(_text);
        BufferedImage img_ = new BufferedImage(w_ + 2, h_ + 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(font_);
        gr_.setColor(_back);
        gr_.fillRect(0, 0, w_ + 2, h_ + 2);
        gr_.setColor(_front);
        gr_.drawString(_text, 1, h_);
        gr_.dispose();
        return img_;
    }
    public static BufferedImage paintButton(JLabel _label, String _text,
            boolean _enabled, Color _enable, Color _disable, Color _back) {
        Font font_ = _label.getFont();
        FontMetrics fontMetrics_ = _label.getFontMetrics(font_);
        int h_ = fontMetrics_.getHeight();
        int w_ = fontMetrics_.stringWidth(_text);
        BufferedImage img_ = new BufferedImage(w_ + 2, h_ + 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr_ = img_.createGraphics();
        gr_.setFont(font_);
        gr_.setColor(_back);
        gr_.fillRect(0, 0, w_ + 2, h_ + 2);
        if (_enabled) {
            gr_.setColor(_enable);
        } else {
            gr_.setColor(_disable);
        }
        gr_.drawString(_text, 1, h_);
        gr_.dispose();
        return img_;
    }
}
