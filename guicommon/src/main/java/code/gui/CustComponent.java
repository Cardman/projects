package code.gui;

import java.awt.*;
import java.awt.image.BufferedImage;

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
            p_.paintComponent(new CustGraphics(img_.getGraphics()));
            p_.setIcon(new ImageIcon(img_));
            return;
        }
        _cust.getComponent().repaint();
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

    protected Dimension getPreferredSize() {
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
