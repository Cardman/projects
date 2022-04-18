package code.vi.prot.impl.gui;
import code.gui.AbsPaintableLabel;
import code.gui.FrameUtil;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.DefImageFactory;

import javax.swing.*;

public abstract class PaintableLabelAbs extends CustComponent implements AbsPaintableLabel {

    private final JLabel label = new JLabel();

    public void requestFocusInWindow() {
        label.requestFocusInWindow();
    }

    public void setEmptyIcon() {
        label.setIcon(new ImageIcon());
    }
    public void setIcon(AbstractImageFactory _fact,AbstractImage _icon) {
        label.setIcon(DefImageFactory.icon(((DefImage)_icon)));
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
        int prHeight_ = getPreferredSize().height;
        return FrameUtil.pref(h_, prHeight_);
    }

    @Override
    public int getWidth() {
        int w_ = super.getWidth();
        int prWidth_ = getPreferredSize().width;
        return FrameUtil.pref(w_, prWidth_);
    }

    @Override
    public JComponent getNatComponent() {
        return label;
    }

}
