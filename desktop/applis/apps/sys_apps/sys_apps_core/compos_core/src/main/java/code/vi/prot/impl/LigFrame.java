package code.vi.prot.impl;

import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.vi.prot.impl.gui.Panel;


public final class LigFrame extends LigWindow implements AbsOtherFrame, ChangeableTitle {

    private boolean mainFrame;

    private AbsPanel pane = Panel.newLineBox();

    public void setMainFrame(boolean _mainFrame) {
        mainFrame = _mainFrame;
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        return new MetaPoint(0,0);
    }

    public void dispose() {
        setVisible(false);
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return null;
    }

    @Override
    public void setContentPane(AbsPanel _p) {
        pane = _p;
    }

    @Override
    public void pack() {
        GuiBaseUtil.recalculate(pane);
    }

}
