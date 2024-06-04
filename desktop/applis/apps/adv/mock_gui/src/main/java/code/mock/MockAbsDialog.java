package code.mock;

import code.gui.AbsOtherDialog;
import code.gui.PlacableWindow;
import code.gui.initialize.AbstractProgramInfos;

public abstract class MockAbsDialog extends MockWindow implements AbsOtherDialog, PlacableWindow {

    private boolean modal;

    protected MockAbsDialog(AbstractProgramInfos _fr) {
        super(_fr);
    }

    @Override
    public void setModal(boolean _b) {
        modal = _b;
    }

    @Override
    public boolean isModal() {
        return modal;
    }

}
