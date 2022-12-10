package code.vi.prot.impl;

import code.gui.AbsOtherDialog;
import code.gui.ChangeableTitle;
import code.vi.prot.impl.gui.Panel;

public final class LigDialog extends LigWindow implements AbsOtherDialog, ChangeableTitle {

    private boolean modal;

    public LigDialog() {
        super(Panel.newGrid(0,1));
    }

    public boolean isModal() {
        return modal;
    }
    public void setModal(boolean _modal) {
        this.modal = _modal;
    }


}

