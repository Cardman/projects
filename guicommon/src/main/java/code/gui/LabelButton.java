package code.gui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelButton extends JLabel {

    private static final int MAX_COLOR = 255;
    private static final int MID_COLOR = 127;

    private static final Color DEFAULT_FOREGROUND = new Color(0, 0, MID_COLOR);
    private static final Color DISABLED = new Color(MID_COLOR, MID_COLOR, MAX_COLOR);

    private boolean enabledLabel = true;

    private String text = "";

    private MouseAdapaterCore adapterCore;

    public LabelButton(String _text) {
        this(_text, true);
    }

    public LabelButton(String _text, boolean _initSize) {
        this();
        text = _text;
        LabelButtonUtil.setTextDefaultColors(this, _text, enabledLabel);
        if (_initSize) {
            initSize(_text);
        }
    }

    public LabelButton() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setForeground(DEFAULT_FOREGROUND);
        setBackground(Color.WHITE);
    }

    public LabelButton(ImageIcon _imageIcon) {
        super(_imageIcon);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void addMouseListener(MouseAdapter _l) {
        adapterCore = new MouseAdapaterCore(_l, this);
        super.addMouseListener(adapterCore);
    }

    public boolean isEnabledLabel() {
        return enabledLabel;
    }

    public void setEnabledLabel(boolean _enabled) {
        if (!_enabled) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        enabledLabel = _enabled;
        if (_enabled) {
            setForeground(DEFAULT_FOREGROUND);
        } else {
            setForeground(DISABLED);
        }
        if (!text.isEmpty()) {
            LabelButtonUtil.setTextDefaultColors(this, text, enabledLabel);
        }
    }

    public void setTextAndSize(String _text) {
        text = _text;
        LabelButtonUtil.setTextDefaultColors(this, _text, enabledLabel);
        initSize(_text);
    }

    public void initSize(String _text) {
        FontMetrics fMetric_ = getFontMetrics(getFont());
        int h_ = fMetric_.getHeight();
        int w_ = fMetric_.stringWidth(_text);
        w_++;
        w_++;
        setPreferredSize(new Dimension(w_, h_));
    }

    public void setVisibleButton(boolean _aFlag) {
        setVisible(_aFlag);
    }
}
