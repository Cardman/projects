package code.mock;

import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.AbsOtherDialog;
import code.gui.Iconifiable;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;

public abstract class MockAbsDialog extends MockWindow implements AbsDialog, AbsOtherDialog {

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
