package code.gui;

import code.gui.images.AbstractImage;
import code.util.core.NumberUtil;


public final class LabelButtonUtil {

//    public static final Color DEFAULT_FOREGROUND = new Color(0, 0, 127);
//    public static final Color DISABLED = new Color(127, 127, 255);

    private LabelButtonUtil(){
    }

    public static boolean match(AbstractSelectionListener _wrapper, ListSelection _list) {
        return _wrapper.getListener() == _list;
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
                                         int _front, int _back) {
        int w_ = _fw;
        w_ = NumberUtil.max(_w, w_);
        _label.setColor(GuiConstants.newColor(_back));
        _label.fillRect(0, 0, w_ + 2, _h + 2);
        _label.setColor(GuiConstants.newColor(_front));
        _label.drawString(_text, 1, _h);
    }

    public static AbstractImage repaintSelected(int _index, boolean _sel, AbsGraphicStringList _curr, DefaultCellRender _simpleRender) {
        String elt_ = _curr.getElements().get(_index);
        AbsPanel panel_ = _curr.getPanel();
        _curr.setHeightList(NumberUtil.max(_curr.getHeightList(),panel_.heightFont()));
        _simpleRender.setMaxWidth(NumberUtil.max(_simpleRender.getMaxWidth(),panel_.stringWidth(elt_)));
        AbstractImage buff_ = _curr.getFact().newImageRgb(_simpleRender.getWidth(),panel_.heightFont());
//        CustGraphics gr_ = new CustGraphics(buff_.getGraphics());
        buff_.setFont(panel_);
        int h_ = panel_.heightFont();
        int w_ = panel_.stringWidth(elt_);
        if (_sel) {
            paintDefaultLabel(buff_, elt_, w_, _simpleRender.getMaxWidth(), h_, GuiConstants.WHITE, GuiConstants.BLUE);
        } else {
            paintDefaultLabel(buff_, elt_, w_, _simpleRender.getMaxWidth(), h_, GuiConstants.BLACK, GuiConstants.WHITE);
        }
        return buff_;
    }

    public static void repAll(AbsGraphicStringList _curr) {
        AbsPanel panel_ =  _curr.getPanel();
        int len_ =  _curr.getElements().size();
        for (int i = 0; i < len_; i++) {
            AbstractImage buff_ = repaintSelected(i,  _curr.getSelectedIndexes().containsObj(i), _curr, (DefaultCellRender)_curr.getSimpleRender());
            AbsPreparedLabel lab_ = _curr.getCompoFactory().newPreparedLabel(buff_);
            _curr.getListComponents().add(lab_);
            panel_.add(lab_);
        }
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
