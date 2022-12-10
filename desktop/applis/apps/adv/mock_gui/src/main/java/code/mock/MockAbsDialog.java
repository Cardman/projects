package code.mock;

import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;

public abstract class MockAbsDialog extends MockWindow implements AbsDialog, AbsOtherDialog, PlacableWindow {

    private boolean modal;
    private boolean resizable;
    private AbsCloseableDialog event;

    protected MockAbsDialog(AbstractProgramInfos _fr) {
        super(_fr);
    }

    protected MockAbsDialog(AbsCloseableDialog _cl, AbstractProgramInfos _fr) {
        super(_fr);
        event = _cl;
    }

    @Override
    public void setLocationRelativeTo(AbsOtherDialog _c) {
        setResizable(isResizable());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherFrame _c) {
        setModal(isModal());
    }

    public boolean isResizable() {
        return resizable;
    }

    @Override
    public void setResizable(boolean _b) {
        resizable = _b;
    }

    @Override
    public void setDialogIcon(AbstractImageFactory _i, Iconifiable _group) {
        setImageIconFrame(_group.getImageIconFrame());
    }

    @Override
    public void setModal(boolean _b) {
        modal = _b;
    }

    @Override
    public boolean isModal() {
        return modal;
    }

    @Override
    public void closeWindow() {
        if (event != null) {
            event.closeWindow();
        }
        setVisible(false);
        getContentPane().removeAll();
    }

}
