package code.mock;

import code.gui.AbsTextField;
import code.gui.events.AbsAutoCompleteListener;

public final class MockAutoCompleteListener implements AbsAutoCompleteListener {

    private final AbsTextField textField;

    public MockAutoCompleteListener(AbsTextField _t) {
        this.textField = _t;
    }

    @Override
    public void insertUpdate() {
        textField.setCaretPosition(textField.getText().length());
    }

    @Override
    public void removeUpdate() {
        textField.setCaretPosition(textField.getText().length());
    }

    @Override
    public void changedUpdate() {
        textField.setCaretPosition(textField.getText().length());
    }
}
