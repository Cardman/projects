package code.gui;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;

import code.util.CustList;

public abstract class CustComponent {

    private CustComponent parent;
    private CustList<CustComponent> children = new CustList<CustComponent>();
    public abstract JComponent getComponent();
    public static void invokeLater(Runnable _r) {
        SwingUtilities.invokeLater(_r);
    }
    public static boolean invokeAndWait(Runnable _r) {
        if (SwingUtilities.isEventDispatchThread()) {
            Thread th_ = new Thread(_r);
            th_.start();
            try {
                th_.join();
            } catch (Exception _0) {
                //ignore
            }
            return true;
        }
        try {
            SwingUtilities.invokeAndWait(_r);
            return true;
        } catch (Throwable _0) {
            return false;
        }
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

    public void setBorder(Border lineBorder) {
        getComponent().setBorder(lineBorder);
    }

    public void setToolTipText(String _title) {
        getComponent().setToolTipText(_title);
    }

    public void setCursor(Cursor cursor) {
        getComponent().setCursor(cursor);
    }

    public Dimension getPreferredSize() {
        return getComponent().getPreferredSize();
    }

    public void setPreferredSize(Dimension dimension) {
        getComponent().setPreferredSize(dimension);
    }

    public Point getLocation() {
        return getComponent().getLocation();
    }

    public Color getBackground() {
        return getComponent().getBackground();
    }

    public Color getForeground() {
        return getComponent().getForeground();
    }
}
