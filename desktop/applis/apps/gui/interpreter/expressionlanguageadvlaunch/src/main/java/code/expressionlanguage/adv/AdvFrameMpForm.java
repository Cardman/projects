package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.gui.AbsCustCheckBox;
import code.gui.AbsButton;
import code.util.StringMap;

public abstract class AdvFrameMpForm {
    public abstract AbsFrameMpFormContent form();

    public void refresh(StringMap<String> _v, ResultContext _r, AbsDebuggerGui _d) {
        form().refresh(_v, _r, _d);
    }

    public AbsCustCheckBox getEnterFunction() {
        return form().getEnterFunction();
    }

    public AbsCustCheckBox getExitFunction() {
        return form().getExitFunction();
    }

    public AbsCustCheckBox getEnabledMp() {
        return form().getEnabledMp();
    }

    public AbsButton getOk() {
        return form().getOk();
    }

    public AbsButton getRemove() {
        return form().getRemove();
    }

    public GuiStackForm getGuiEnterStackForm() {
        return form().getGuiEnterStackForm();
    }

    public GuiStackForm getGuiExitStackForm() {
        return form().getGuiExitStackForm();
    }
}
