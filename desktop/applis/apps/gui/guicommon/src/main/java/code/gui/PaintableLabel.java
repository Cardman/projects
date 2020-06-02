package code.gui;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public abstract class PaintableLabel extends CustComponent {

    private JLabel label = new JLabel();

    public void repaintLabel() {
        int w_ = getWidth();
        int h_ = getHeight();
        BufferedImage img_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        CustGraphics gr_ = new CustGraphics(img_.getGraphics());
        gr_.setFont(getFont());
        paintComponent(gr_);
        setIcon(img_);
    }
    public abstract void paintComponent(CustGraphics _g);

    public void validate() {
        label.validate();
    }

    public void removeMouseListener(MouseListener _l) {
        label.removeMouseListener(_l);
    }

    public MouseListener[] getMouseListeners() {
        return label.getMouseListeners();
    }

    public boolean requestFocusInWindow() {
        return label.requestFocusInWindow();
    }

    public void setIcon(BufferedImage _icon) {
        label.setIcon(new ImageIcon(_icon));
    }

    public FontMetrics getFontMetrics(Font _font) {
        return label.getFontMetrics(_font);
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
    protected JComponent getComponent() {
        return label;
    }

    JLabel getLabel() {
        return label;
    }
}
