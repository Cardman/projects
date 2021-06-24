package code.gui;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class LabelButton extends CustComponent {

    private static final int MAX_COLOR = 255;
    private static final int MID_COLOR = 127;

    private static final Color DEFAULT_FOREGROUND = new Color(0, 0, MID_COLOR);
    private static final Color DISABLED = new Color(MID_COLOR, MID_COLOR, MAX_COLOR);

    private boolean enabledLabel = true;

    private final JButton label;
//    private final JLabel label;

    private String text = "";

    private MouseAdapaterCore adapterCore;

    public LabelButton(String _text) {
        this(_text, true);
    }

    public LabelButton(String _text, boolean _initSize) {
        this();
        text = _text;
        setTextDefaultColors(_text, enabledLabel);
        if (_initSize) {
            initSize(_text);
        }
    }
    public LabelButton() {
        label = new JButton();
//        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        label.setOpaque(true);
//        label.setForeground(DEFAULT_FOREGROUND);
//        label.setBackground(Color.WHITE);
    }

    public LabelButton(AbstractImageFactory _fact, AbstractImage _imageIcon) {
        label = new JButton(PreparedLabel.buildIcon(_fact,_imageIcon));
        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void setTextDefaultColors(String _text,
                              boolean _enabled) {
//        BufferedImage img_ = LabelButtonUtil.paintButton(this, _text, _enabled);
//        label.setIcon(new ImageIcon(img_));
        label.setText(_text);
        label.setEnabled(_enabled);
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
//        if (_enabled) {
//            label.setForeground(DEFAULT_FOREGROUND);
//        } else {
//            label.setForeground(DISABLED);
//        }
        if (!text.isEmpty()) {
            setTextDefaultColors(text, enabledLabel);
        }
    }

    public void setTextAndSize(String _text) {
        text = _text;
        setTextDefaultColors(_text, enabledLabel);
//        initSize(_text);
    }

    public void initSize(String _text) {
//        int h_ = heightFont();
//        int w_ = stringWidth(_text);
//        w_++;
//        w_++;
//        label.setPreferredSize(new Dimension(w_, h_));
    }

    public void setVisibleButton(boolean _aFlag) {
        label.setVisible(_aFlag);
    }

    @Override
    protected JComponent getComponent() {
        return label;
    }
}
