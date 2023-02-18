package code.expressionlanguage.adv;

import code.gui.AbsSpinner;
import code.gui.events.AbsActionListener;
import code.threads.AbstractAtomicBoolean;

public final class ValidateTabulations implements AbsActionListener {
    private final AbstractAtomicBoolean val;
    private final AbsSpinner value;
    private final WindowCdmEditor output;

    public ValidateTabulations(AbstractAtomicBoolean _valid, AbsSpinner _value, WindowCdmEditor _w) {
        val = _valid;
        value = _value;
        this.output = _w;
    }

    @Override
    public void action() {
        output.setTabWidth(value.getValue());
        val.set(true);
        output.getDialogTabulations().setVisible(false);
    }
}
