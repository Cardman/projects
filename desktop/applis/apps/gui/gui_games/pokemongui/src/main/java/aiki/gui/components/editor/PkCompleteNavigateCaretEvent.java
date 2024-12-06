package aiki.gui.components.editor;

import code.gui.events.*;

public final class PkCompleteNavigateCaretEvent implements AbsActionListener {
    private final GeneComponentModelText textPane;
    private final int diff;

    public PkCompleteNavigateCaretEvent(GeneComponentModelText _s, int _d) {
        this.textPane = _s;
        this.diff = _d;
    }

    @Override
    public void action() {
        if (diff == 1) {
            textPane.getTextPane().rightAction();
        } else {
            textPane.getTextPane().leftAction();
        }
        if (!textPane.getPopupMenu().isVisible()) {
            return;
        }
        if (textPane.getTextPane().getCaretPosition() < textPane.getCompleteOffset()) {
            textPane.getPopupMenu().setVisible(false);
            return;
        }
        PkCompleteEvent.computeWords(textPane, 0);

    }
}
