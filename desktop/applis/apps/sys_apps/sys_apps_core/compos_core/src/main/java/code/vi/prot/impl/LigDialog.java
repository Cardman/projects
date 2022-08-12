package code.vi.prot.impl;

import code.gui.AbsOtherDialog;
import code.gui.AbsPanel;
import code.gui.ChangeableTitle;
import code.gui.GuiBaseUtil;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.vi.prot.impl.gui.Panel;

public final class LigDialog extends LigWindow implements AbsOtherDialog, ChangeableTitle {

    private boolean modal;
    private AbsPanel contentPane = Panel.newGrid(0,1);

    @Override
    public MetaPoint getLocationOnScreen() {
        return new MetaPoint(0,0);
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return null;
    }

    @Override
    public void dispose() {
        setVisible(false);
    }

    public boolean isModal() {
        return modal;
    }
    public void setModal(boolean _modal) {
        this.modal = _modal;
    }
    @Override
    public void pack() {
        GuiBaseUtil.recalculate(contentPane);
    }

    public void setContentPane(AbsPanel _contentPane) {
        contentPane = _contentPane;
    }

    @Override
    public AbsPanel getContentPane() {
        return contentPane;
    }

}

