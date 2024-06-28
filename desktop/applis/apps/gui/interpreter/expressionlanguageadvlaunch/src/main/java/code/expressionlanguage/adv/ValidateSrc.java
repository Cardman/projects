package code.expressionlanguage.adv;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;

public final class ValidateSrc implements AbsActionListener {
    private final AbsTextField value;
    private final WindowWithTreeImpl output;

    public ValidateSrc(AbsTextField _value, WindowWithTreeImpl _w) {
        value = _value;
        this.output = _w;
    }

    @Override
    public void action() {
        output.getManageOptions().getEx().setSrcFolder(value.getText());
        output.afterChangingSyntaxPreferences();
    }
}
