package code.mock;

import code.gui.AbsOtherDialog;
import code.gui.PlacableWindow;

public class MockAbsDialog extends MockWindow implements AbsOtherDialog, PlacableWindow {

    private boolean modal;

    @Override
    public void setModal(boolean _b) {
        modal = _b;
    }

    @Override
    public boolean isModal() {
        return modal;
    }

}
