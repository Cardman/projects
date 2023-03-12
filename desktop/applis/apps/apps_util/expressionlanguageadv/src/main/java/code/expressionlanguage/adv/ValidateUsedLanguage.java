package code.expressionlanguage.adv;

import code.gui.GraphicComboGrInt;
import code.gui.events.AbsActionListener;
import code.util.core.StringUtil;

public final class ValidateUsedLanguage implements AbsActionListener {
    private final GraphicComboGrInt value;
    private final WindowWithTreeImpl output;

    public ValidateUsedLanguage(GraphicComboGrInt _value, WindowWithTreeImpl _w) {
        value = _value;
        this.output = _w;
    }

    @Override
    public void action() {
        output.setUsedLg(StringUtil.nullToEmpty(value.getSelectedItem()));
        output.afterChangingSyntaxPreferences();
    }
}
