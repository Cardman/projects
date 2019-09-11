package code.gui;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

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
        try {
            SwingUtilities.invokeAndWait(_r);
            return true;
        } catch (Exception _0) {
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
    public void repaint() {
        if (children.isEmpty()) {
            paint(this);
            return;
        }
        CustComponent current_ = this;
        while (true) {
            paint(current_);
            CustList<CustComponent> ch_ = current_.getChildren();
            if (!ch_.isEmpty()) {
                current_ = ch_.first();
                continue;
            }
            while (true) {
                CustComponent next_ = getNextSibling(current_);
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                CustComponent parent_ = current_.getParent();
                if (parent_ == this) {
                    return;
                }
                current_ = parent_;
            }
        }
    }
    private static void paint(CustComponent _cust) {
        if (_cust instanceof PaintableLabel) {
            PaintableLabel p_ = (PaintableLabel) _cust;
            int w_ = _cust.getWidth();
            int h_ = _cust.getHeight();
            BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
            Graphics gr_ = img_.getGraphics();
            gr_.setFont(p_.getFont());
            p_.paintComponent(new CustGraphics(gr_));
            p_.setIcon(new ImageIcon(img_));
            return;
        }
        _cust.getComponent().repaint();
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
    private static CustComponent getNextSibling(CustComponent _current) {
        CustComponent parent_ = _current.getParent();
        CustList<CustComponent> children_ = parent_.getChildren();
        int count_ = children_.size();
        int i_ = 0;
        while (true) {
            if (children_.get(i_) == _current) {
                if (i_ + 1 >= count_) {
                    return null;
                }
                return children_.get(i_ + 1);
            }
            i_++;
        }
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
