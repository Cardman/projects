package code.gui;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;

public abstract class PaintableLabel extends CustComponent {

    private final JLabel label = new JLabel();

    public void repaintLabel(AbstractImageFactory _fact) {
        int w_ = getWidth();
        int h_ = getHeight();
        if (w_ <= 0 || h_ <= 0) {
            setEmptyIcon();
            return;
        }
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
//        CustGraphics gr_ = img_.getGraphics();
        img_.setFont(getFont());
        paintComponent(img_);
        setIcon(_fact,img_);
    }

    public abstract void paintComponent(AbstractImage _g);

    public boolean requestFocusInWindow() {
        return label.requestFocusInWindow();
    }

    public void setEmptyIcon() {
        label.setIcon(new ImageIcon());
    }
    public void setIcon(AbstractImageFactory _fact,AbstractImage _icon) {
        label.setIcon(PreparedLabel.buildIcon(_fact,_icon));
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
    protected JComponent getNatComponent() {
        return label;
    }

}
