package code.expressionlanguage.adv;

import code.gui.AbsSpinner;
import code.gui.events.AbsActionListener;

public final class ValidateTabulations implements AbsActionListener {
    private final AbsSpinner value;
    private final WindowCdmEditor output;

    public ValidateTabulations(AbsSpinner _value, WindowCdmEditor _w) {
        value = _value;
        this.output = _w;
    }

    @Override
    public void action() {
        output.getDialogTabulations().setVisible(false);
        output.setTabWidth(value.getValue());
        output.afterChangingSyntaxPreferences();
    }
}
