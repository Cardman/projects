package code.gui.document;

import code.gui.AbsTextPane;

public final class SetTextThread implements Runnable {
    private final AbsTextPane textPane;
    private final String text;

    public SetTextThread(AbsTextPane _p, String _t) {
        this.textPane = _p;
        this.text = _t;
    }

    @Override
    public void run() {
        textPane.setText(text);
    }
}
