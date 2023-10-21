package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.gui.ScrollCustomCombo;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class ValidateUsedLanguage implements AbsActionListener {
    private final ScrollCustomCombo value;
    private final WindowWithTreeImpl output;

    public ValidateUsedLanguage(ScrollCustomCombo _value, WindowWithTreeImpl _w) {
        value = _value;
        this.output = _w;
    }

    @Override
    public void action() {
        output.setUsedLg(StringUtil.nullToEmpty(GuiBaseUtil.getSelectedItem(value)));
        output.afterChangingSyntaxPreferences();
    }
}
