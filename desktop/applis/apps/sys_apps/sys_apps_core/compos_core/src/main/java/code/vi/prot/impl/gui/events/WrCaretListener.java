package code.vi.prot.impl.gui.events;

import code.gui.events.AbsCaretListener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public final class WrCaretListener implements CaretListener {
    private final AbsCaretListener listener;

    public WrCaretListener(AbsCaretListener _l) {
        this.listener = _l;
    }

    @Override
    public void caretUpdate(CaretEvent _e) {
        listener.caretUpdate(_e.getDot(), _e.getMark());
    }
}
