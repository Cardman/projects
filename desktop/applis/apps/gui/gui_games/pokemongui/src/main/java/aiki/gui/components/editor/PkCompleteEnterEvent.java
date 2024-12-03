package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkCompleteEnterEvent implements AbsActionListener {
    private final GeneComponentModelText input;

    public PkCompleteEnterEvent(GeneComponentModelText _i) {
        this.input = _i;
    }

    @Override
    public void action() {
        AbsTextPane textPane_ = input.getTextPane();
        int caretPosition_ = textPane_.getCaretPosition();
        int sel_ = input.getElement().getSelectedIndex();
        if (sel_ == -1) {
            input.getTextPane().insert("\n",caretPosition_);
            input.getPopupMenu().setVisible(false);
            return;
        }
        int previous_ = PkCompleteEvent.previousChar(textPane_.getText(), caretPosition_);
        input.getPopupMenu().setVisible(false);
        textPane_.insert(input.getElement().get(sel_).substring(caretPosition_-previous_), caretPosition_);
    }
}
