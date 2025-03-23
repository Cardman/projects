package cards.gui.dialogs;

import code.gui.AbsTextPane;

public final class SetTextThreadCards implements Runnable {
    private final AbsTextPane textPane;
    private final String text;

    public SetTextThreadCards(AbsTextPane _p, String _t) {
        this.textPane = _p;
        this.text = _t;
    }

    @Override
    public void run() {
        textPane.setText(text);
    }
}
