package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkCompleteEnterEvent implements AbsActionListener, ListSelection {
    private final GeneComponentModelText input;

    public PkCompleteEnterEvent(GeneComponentModelText _i) {
        this.input = _i;
    }

    @Override
    public void action() {
        tryInsert(input);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        tryInsert(input);
    }

    static void tryInsert(GeneComponentModelText _input) {
        AbsTextPane textPane_ = _input.getTextPane();
        int caretPosition_ = textPane_.getCaretPosition();
        int sel_;
        if (!_input.getPopupMenu().isVisible()) {
            sel_ = -1;
        } else {
            sel_ = _input.getElement().getSelectedIndex();
        }
        if (sel_ == -1) {
            _input.getTextPane().enter();
            _input.getPopupMenu().setVisible(false);
            return;
        }
        int previous_ = PkCompleteEvent.previousChar(textPane_.getText(), caretPosition_);
        _input.getPopupMenu().setVisible(false);
        textPane_.insert(_input.getElement().get(sel_).substring(caretPosition_-previous_), caretPosition_);
    }
}
