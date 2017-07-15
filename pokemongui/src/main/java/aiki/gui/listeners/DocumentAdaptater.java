package aiki.gui.listeners;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class DocumentAdaptater implements DocumentListener {
    @Override
    public void changedUpdate(DocumentEvent _e) {
        updateText();
    }
    @Override
    public void removeUpdate(DocumentEvent _e) {
        updateText();
    }
    @Override
    public void insertUpdate(DocumentEvent _e) {
        updateText();
    }
    public abstract void updateText();
}
