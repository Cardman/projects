package code.expressionlanguage.adv;

import code.gui.AbsTextArea;
import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class ValidateMessage implements AbsActionListener {
    private final AbsTextField key;
    private final AbsTextArea value;
    private final StringMap<String> messages;

    public ValidateMessage(AbsTextField _k, AbsTextArea _v, StringMap<String> _m) {
        key = _k;
        value = _v;
        messages = _m;
    }

    @Override
    public void action() {
        messages.set(key.getText(), value.getText());
    }
}
