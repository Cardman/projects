package code.expressionlanguage.adv;

import code.gui.AbsPlainLabel;
import code.gui.AbsTextArea;
import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.util.StringMap;

public final class ValidateMessage implements AbsActionListener {
    private final WindowCdmEditor softModel;
    private final AbsTextField key;
    private final AbsTextArea value;
    private final StringMap<String> messages;
    private final AbsPlainLabel curValue;
    private final boolean always;

    public ValidateMessage(WindowCdmEditor _params,AbsTextField _k, AbsTextArea _v, StringMap<String> _m, AbsPlainLabel _current, boolean _direct) {
        softModel = _params;
        key = _k;
        value = _v;
        messages = _m;
        curValue = _current;
        always = _direct;
    }

    @Override
    public void action() {
        if (softModel.getSoftParams().isDirectMatchKeyValue() || always) {
            messages.set(key.getText(), value.getText());
            curValue.setText(value.getText());
        }
    }
}
