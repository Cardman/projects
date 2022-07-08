package code.gui.events;

import code.gui.*;

public final class ClosingDialogEvent extends AbstractClosingDialogListEvent implements AbsActionListener {

    public ClosingDialogEvent(AbsDialog _current) {
        this(_current, null);
    }

    public ClosingDialogEvent(AbsDialog _current, AbsCloseableDialog _dia) {
        super(_current, _dia);
    }

    @Override
    public void action() {
        close();
    }
}
