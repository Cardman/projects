package code.gui;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.sys.impl.DefImage;
import code.sys.impl.DefImageFactory;

import javax.swing.*;

public final class PaintableLabel extends CustComponent implements AbsPaintableLabel {

    private final JLabel label = new JLabel();
    private final AbsMetaLabel metaLabel;
    public PaintableLabel(AbsMetaLabel _meta) {
        metaLabel = _meta;
    }

    public void repaintLabel(AbstractImageFactory _fact) {
        FrameUtil.repaint(_fact, this, metaLabel);
    }

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
    protected JComponent getNatComponent() {
        return label;
    }

}
