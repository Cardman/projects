package code.gui;
import code.gui.events.AbsMouseListener;
import code.gui.events.MouseListenerCore;
import code.gui.events.WrMouseListener;

import java.awt.Cursor;

import javax.swing.*;

public final class LabelButton extends CustComponent implements AbsLabelButton {

//    private static final int MAX_COLOR = 255;
//    private static final int MID_COLOR = 127;

//    private static final Color DEFAULT_FOREGROUND = new Color(0, 0, MID_COLOR);
//    private static final Color DISABLED = new Color(MID_COLOR, MID_COLOR, MAX_COLOR);

    private boolean enabledLabel = true;

    private final JButton label;
//    private final JLabel label;

    private String text = "";

    public LabelButton(String _text) {
        this();
        text = _text;
        setTextDefaultColors(_text, enabledLabel);
    }
    public LabelButton() {
        label = new JButton();
//        setLineBorder(Color.BLACK, 1);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        label.setOpaque(true);
//        label.setForeground(DEFAULT_FOREGROUND);
//        label.setBackground(Color.WHITE);
    }

    public void setTextDefaultColors(String _text,
                              boolean _enabled) {
//        BufferedImage img_ = LabelButtonUtil.paintButton(this, _text, _enabled);
//        label.setIcon(new ImageIcon(img_));
        label.setText(_text);
        label.setEnabled(_enabled);
    }

    public void addMouseList(AbsMouseListener _l) {
        label.addMouseListener(new WrMouseListener(new MouseListenerCore(_l, this)));
    }

    public boolean isEnabledLabel() {
        return enabledLabel;
    }

    public void setEnabledLabel(boolean _enabled) {
        int value_ = GuiConstants.cursor(_enabled);
        label.setCursor(new Cursor(value_));
        enabledLabel = _enabled;
//        if (_enabled) {
//            label.setForeground(DEFAULT_FOREGROUND);
//        } else {
//            label.setForeground(DISABLED);
//        }
        FrameUtil.trySetText(this);
    }

    public String getText() {
        return text;
    }

    public void setTextAndSize(String _text) {
        text = _text;
        setTextDefaultColors(_text, enabledLabel);
//        initSize(_text);
    }

    public void setVisibleButton(boolean _aFlag) {
        label.setVisible(_aFlag);
    }

    @Override
    protected JComponent getNatComponent() {
        return label;
    }
}
