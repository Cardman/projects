package code.gui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class LabelButton extends CustComponent {

    private static final int MAX_COLOR = 255;
    private static final int MID_COLOR = 127;

    private static final Color DEFAULT_FOREGROUND = new Color(0, 0, MID_COLOR);
    private static final Color DISABLED = new Color(MID_COLOR, MID_COLOR, MAX_COLOR);

    private boolean enabledLabel = true;

    private JLabel label;

    private String text = "";

    private MouseAdapaterCore adapterCore;

    public LabelButton(String _text) {
        this(_text, true);
    }

    public LabelButton(String _text, boolean _initSize) {
        this();
        text = _text;
        LabelButtonUtil.setTextDefaultColors(label, _text, enabledLabel);
        if (_initSize) {
            initSize(_text);
        }
    }

    public LabelButton() {
        label = new JLabel();
        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setOpaque(true);
        label.setForeground(DEFAULT_FOREGROUND);
        label.setBackground(Color.WHITE);
    }

    public LabelButton(BufferedImage _imageIcon) {
        label = new JLabel(new ImageIcon(_imageIcon));
        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void addMouseListener(MouseAdapter _l) {
        adapterCore = new MouseAdapaterCore(_l, this);
        label.addMouseListener(adapterCore);
    }

    public boolean isEnabledLabel() {
        return enabledLabel;
    }

    public void setEnabledLabel(boolean _enabled) {
        if (!_enabled) {
            label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        enabledLabel = _enabled;
        if (_enabled) {
            label.setForeground(DEFAULT_FOREGROUND);
        } else {
            label.setForeground(DISABLED);
        }
        if (!text.isEmpty()) {
            LabelButtonUtil.setTextDefaultColors(label, text, enabledLabel);
        }
    }

    public void setTextAndSize(String _text) {
        text = _text;
        LabelButtonUtil.setTextDefaultColors(label, _text, enabledLabel);
        initSize(_text);
    }

    public void initSize(String _text) {
        FontMetrics fMetric_ = label.getFontMetrics(label.getFont());
        int h_ = fMetric_.getHeight();
        int w_ = fMetric_.stringWidth(_text);
        w_++;
        w_++;
        label.setPreferredSize(new Dimension(w_, h_));
    }

    public void setVisibleButton(boolean _aFlag) {
        label.setVisible(_aFlag);
    }

    @Override
    protected JComponent getComponent() {
        return label;
    }

    public void setBackground(Color _background) {
        label.setBackground(_background);
    }

    public void setForeground(Color _foreground) {
        label.setForeground(_foreground);
    }

    public MouseListener[] getMouseListeners() {
        return label.getMouseListeners();
    }
}
