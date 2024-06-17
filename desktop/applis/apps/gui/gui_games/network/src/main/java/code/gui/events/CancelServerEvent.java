package code.gui.events;

import code.network.DialogServerContent;

public final class CancelServerEvent implements AbsActionListener {

    private final DialogServerContent dialog;

    public CancelServerEvent(DialogServerContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.restore();
    }
}
