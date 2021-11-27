package code.vi.sys.impl.gui.events;

import code.gui.events.AbsAutoCompleteListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class WrAutoCompleteListener implements FocusListener, DocumentListener {
    private final AbsAutoCompleteListener autoCompleteListener;

    public WrAutoCompleteListener(AbsAutoCompleteListener _autoCompleteListener) {
        this.autoCompleteListener = _autoCompleteListener;
    }

    @Override
    public void focusGained(FocusEvent _e) {
        autoCompleteListener.focusGained();
    }

    @Override
    public void focusLost(FocusEvent _e) {
        autoCompleteListener.focusLost();
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
