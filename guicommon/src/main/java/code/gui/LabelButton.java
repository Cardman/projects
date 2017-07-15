package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelButton extends JLabel {

    private static final int MAX_COLOR = 255;
    private static final int MID_COLOR = 127;

    private static final Color DEFAULT_FOREGROUND = new Color(0, 0, MID_COLOR);
    private static final Color DISABLED = new Color(MID_COLOR, MID_COLOR, MAX_COLOR);

    private boolean entered;

    public LabelButton(String _text) {
        this(_text, true);
    }

    public LabelButton(String _text, boolean _initSize) {
        this();
        setText(_text);
        if (_initSize) {
            initSize();
        }
    }

    public LabelButton() {
        setOpaque(true);
        setForeground(DEFAULT_FOREGROUND);
        setBackground(Color.WHITE);
    }

    public LabelButton(ImageIcon _imageIcon) {
        super(_imageIcon);
    }

    public void setEnabledLabel(boolean _enabled) {
        if (!_enabled) {
            entered = false;
        }
        setEnabled(_enabled);
        if (_enabled) {
            setForeground(DEFAULT_FOREGROUND);
        } else {
            setForeground(DISABLED);
        }
        repaint();
    }

    public void setTextAndSize(String _text) {
        setText(_text);
        initSize();
    }

    public void initSize() {
        FontMetrics fMetric_ = getFontMetrics(getFont());
        int h_ = fMetric_.getHeight();
        int w_ = fMetric_.stringWidth(getText());
        w_++;
        w_++;
        setPreferredSize(new Dimension(w_, h_));
    }

    @Override
    public void setVisible(boolean _aFlag) {
        if (!_aFlag) {
            entered = false;
        }
        super.setVisible(_aFlag);
    }

    @Override
    protected void processMouseEvent(MouseEvent _e) {
        if (!isEnabled()) {
            return;
        }
        boolean changed_ = false;
        if (_e.getID() == MouseEvent.MOUSE_ENTERED) {
            entered = true;
            changed_ = true;
        } else if (_e.getID() == MouseEvent.MOUSE_EXITED) {
            entered = false;
            changed_ = true;
        }
        super.processMouseEvent(_e);
        if (changed_) {
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics _g) {
        super.paintComponent(_g);
        if (entered) {
            _g.setColor(Color.RED);
        } else {
            _g.setColor(Color.BLACK);
        }
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
