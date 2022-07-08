package code.gui.events;

import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;

public final class CrossClosingDialogListEvent extends AbstractClosingDialogListEvent implements AbsWindowListenerClosing {

    public CrossClosingDialogListEvent(AbsDialog _current, AbsCloseableDialog _dia) {
        super(_current, _dia);
    }

    @Override
    public void windowClosing() {
        close();
    }
}
