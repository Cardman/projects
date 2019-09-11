package code.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

public abstract class PaintableLabel extends CustComponent {

    private JLabel label = new JLabel();

    public abstract void paintComponent(CustGraphics _g);

    public void validate() {
        label.validate();
    }

    public void addMouseMotionListener(MouseMotionListener _l) {
        label.addMouseMotionListener(_l);
    }

    public void addMouseWheelListener(MouseWheelListener _l) {
        label.addMouseWheelListener(_l);
    }

    public void setSize(Dimension _d) {
        label.setSize(_d);
    }

    public Dimension getSize() {
        return label.getSize();
    }

    public void setSize(int _width, int _height) {
        label.setSize(_width, _height);
    }

    public void removeMouseListener(MouseListener _l) {
        label.removeMouseListener(_l);
    }

    public MouseListener[] getMouseListeners() {
        return label.getMouseListeners();
    }

    public void setOpaque(boolean _b) {
        label.setOpaque(_b);
    }

    public boolean requestFocus(boolean _temporary) {
        return label.requestFocus(_temporary);
    }

    public boolean requestFocusInWindow() {
        return label.requestFocusInWindow();
    }

    public void setFocusable(boolean _focusable) {
        label.setFocusable(_focusable);
    }

    public String getToolTipText() {
        return label.getToolTipText();
    }

    public void setLocation(int _x, int _y) {
        label.setLocation(_x, _y);
    }

    public void setIcon(Icon _icon) {
        label.setIcon(_icon);
    }

    public void setLocation(Point _p) {
        label.setLocation(_p);
    }

    public FontMetrics getFontMetrics(Font _font) {
        return label.getFontMetrics(_font);
    }

    public void setForeground(Color _fg) {
        label.setForeground(_fg);
    }

    public void setBackground(Color _bg) {
        label.setBackground(_bg);
    }

    public void setVerticalAlignment(int _alignment) {
        label.setVerticalAlignment(_alignment);
    }

    public void setHorizontalAlignment(int _alignment) {
        label.setHorizontalAlignment(_alignment);
    }

    @Override
    public int getHeight() {
        int h_ = super.getHeight();
        if (h_ > 0) {
            return h_;
        }
        return getPreferredSize().height;
    }

    @Override
    public int getWidth() {
        int w_ = super.getWidth();
        if (w_ > 0) {
            return w_;
        }
        return getPreferredSize().width;
    }

    @Override
    public JComponent getComponent() {
        return label;
    }

    JLabel getLabel() {
        return label;
    }
}
