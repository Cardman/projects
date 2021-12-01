package code.vi.prot.impl.gui.events;

import code.gui.events.AbsAutoCompleteListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class WrAutoCompleteListener implements DocumentListener {
    private final AbsAutoCompleteListener autoCompleteListener;

    public WrAutoCompleteListener(AbsAutoCompleteListener _autoCompleteListener) {
        this.autoCompleteListener = _autoCompleteListener;
    }

    @Override
    public void insertUpdate(DocumentEvent _e) {
        autoCompleteListener.insertUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent _e) {
        autoCompleteListener.removeUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent _e) {
        autoCompleteListener.changedUpdate();
    }
}
