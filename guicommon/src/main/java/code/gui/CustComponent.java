package code.gui;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import code.util.CustList;

public abstract class CustComponent {

    private CustComponent parent;
    private CustList<CustComponent> children = new CustList<CustComponent>();
    protected abstract JComponent getComponent();
    public static void invokeLater(Runnable _r) {
        SwingUtilities.invokeLater(_r);
    }

    public boolean isAutoscrolls(){
        return getComponent().getAutoscrolls();
    }
    public void setAutoscrolls(boolean _autoscrolls) {
        getComponent().setAutoscrolls(_autoscrolls);
    }
    public static Thread newThread(Runnable _r) {
        return new Thread(_r);
    }

    public void addMouseListener(MouseListener _mouseListener) {
        getComponent().addMouseListener(_mouseListener);
    }

    public void addMouseMotionListener(MouseMotionListener _mouseListener) {
        getComponent().addMouseMotionListener(_mouseListener);
    }

    public void addMouseWheelListener(MouseWheelListener _l) {
        getComponent().addMouseWheelListener(_l);
    }
    public void addKeyListener(KeyListener _l) {
        getComponent().addKeyListener(_l);
    }

    public void requestFocus() {
        getComponent().requestFocus();
    }
    public boolean isVisible() {
        return getComponent().isVisible();
    }
    public void setVisible(boolean _b) {
        getComponent().setVisible(_b);
    }
    public int getWidth() {
        return getComponent().getWidth();
    }
    public int getHeight() {
        return getComponent().getHeight();
    }

    public Font getFont() {
        return getComponent().getFont();
    }
    public void setFont(Font _font) {
        getComponent().setFont(_font);
    }
    public CustComponent getParent() {
        return parent;
    }
    public void setParent(CustComponent _parent) {
        parent = _parent;
    }
    public CustList<CustComponent> getChildren() {
        return children;
    }

    public void setLineBorder(Color _color) {
        getComponent().setBorder(BorderFactory.createLineBorder(_color,1));
    }

    public void setLineBorder(Color _color, int _thick) {
        getComponent().setBorder(BorderFactory.createLineBorder(_color,_thick));
    }

    public void setTitledBorder(String _title) {
        getComponent().setBorder(BorderFactory.createTitledBorder(_title));
    }

    public void setLoweredBorder() {
        getComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    public void setRaisedBorder() {
        getComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    public void setToolTipText(String _title) {
        getComponent().setToolTipText(_title);
    }

    public void setCursor(Cursor cursor) {
        getComponent().setCursor(cursor);
    }

    public Dimension getSize() {
        return getComponent().getSize();
    }

    public void setSize(Dimension dimension) {
        getComponent().setSize(dimension);
    }

    public Dimension getPreferredSize() {
        return getComponent().getPreferredSize();
    }

    public void setPreferredSize(Dimension dimension) {
        getComponent().setPreferredSize(dimension);
    }

    public boolean isFocusable() {
        return getComponent().isFocusable();
    }
    public void setFocusable(boolean _focusable) {
        getComponent().setFocusable(_focusable);
    }
    public boolean isOpaque() {
        return getComponent().isOpaque();
    }

    public void setOpaque(boolean _b) {
        getComponent().setOpaque(_b);
    }

    public int getXcoords() {
        return getComponent().getX();
    }

    public int getYcoords() {
        return getComponent().getY();
    }
    public void setLocation(int _x, int _y) {
        getComponent().setLocation(_x,_y);
    }

    public void setBackground(Color _color) {
        getComponent().setBackground(_color);
    }
    public Color getBackground() {
        return getComponent().getBackground();
    }

    public void setForeground(Color _color) {
        getComponent().setForeground(_color);
    }
    public Color getForeground() {
        return getComponent().getForeground();
    }

    public String getToolTipText() {
        return getComponent().getToolTipText();
    }
}
