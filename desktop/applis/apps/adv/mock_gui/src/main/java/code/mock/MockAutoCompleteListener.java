package code.mock;

import code.gui.AbsTxtComponent;
import code.gui.events.AbsAutoCompleteListener;

public final class MockAutoCompleteListener implements AbsAutoCompleteListener {

    private final AbsTxtComponent textField;

    public MockAutoCompleteListener(AbsTxtComponent _t) {
        this.textField = _t;
    }

    @Override
    public void insertUpdate(int _off, int _len) {
        textField.setCaretPosition(textField.getText().length());
    }

    @Override
    public void removeUpdate(int _off, int _len) {
        textField.setCaretPosition(textField.getText().length());
    }

    @Override
    public void changedUpdate(int _off, int _len) {
        textField.setCaretPosition(textField.getText().length());
    }
}
