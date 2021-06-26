package code.gui;

import code.gui.images.AbstractImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public final class LabelButtonUtil {

//    public static final Color DEFAULT_FOREGROUND = new Color(0, 0, 127);
//    public static final Color DISABLED = new Color(127, 127, 255);

    private LabelButtonUtil(){
    }

//    public static BufferedImage paintButton(PreparedLabel _label, String _text,
//                                            boolean _enabled) {
//        return paintButton(_label, _text, _enabled, DEFAULT_FOREGROUND, DISABLED, Color.WHITE);
//    }

//    public static BufferedImage paintButton(LabelButton _label, String _text,
//            boolean _enabled) {
//        return paintButton(_label, _text, _enabled, DEFAULT_FOREGROUND, DISABLED, Color.WHITE);
//    }

    public static void paintDefaultLabel(AbstractImage _label, String _text, int _w, int _fw, int _h,
                                         Color _front, Color _back) {
        int w_ = _fw;
        w_ = Math.max(_w, w_);
        AbstractImage gr_ = _label;
        gr_.setColor(_back);
        gr_.fillRect(0, 0, w_ + 2, _h + 2);
        gr_.setColor(_front);
        gr_.drawString(_text, 1, _h);
    }
//    static BufferedImage paintDefaultLabel(CustComponent _label, String _text, int _w,
//                                           Color _front, Color _back) {
//        Font font_ = _label.getFont();
//        int h_ = _label.heightFont();
//        int w_ = _label.stringWidth(_text);
//        w_ = Math.max(_w, w_);
//        BufferedImage img_ = new BufferedImage(w_ + 2, h_ + 2, BufferedImage.TYPE_INT_RGB);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
//        gr_.setFont(font_);
//        gr_.setColor(_back);
//        gr_.fillRect(0, 0, w_ + 2, h_ + 2);
//        gr_.setColor(_front);
//        gr_.drawString(_text, 1, h_);
//        gr_.dispose();
//        return img_;
//    }
//    public static BufferedImage paintButton(CustComponent _label, String _text,
//                                             boolean _enabled, Color _enable, Color _disable, Color _back) {
//        Font font_ = _label.getFont();
//        int h_ = _label.heightFont();
//        int w_ = _label.stringWidth(_text);
//        BufferedImage img_ = new BufferedImage(w_ + 2, h_ + 2, BufferedImage.TYPE_INT_RGB);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
//        gr_.setFont(font_);
//        gr_.setColor(_back);
//        gr_.fillRect(0, 0, w_ + 2, h_ + 2);
//        gr_.setColor(color(_enabled, _enable, _disable));
//        gr_.drawString(_text, 1, h_);
//        gr_.dispose();
//        return img_;
//    }
//    public static Color color(boolean _enabled, Color _enable, Color _disable) {
//        if (_enabled) {
//            return _enable;
//        } else {
//            return _disable;
//        }
//    }
}
