package code.expressionlanguage.adv;

import code.gui.AbsCustCheckBox;
import code.gui.AbsPlainButton;
import code.util.StringMap;

public abstract class AdvFrameMpForm {
    public abstract AbsFrameMpFormContent form();

    public void refresh(StringMap<String> _v) {
        form().refresh(_v);
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

    public AbsPlainButton getOk() {
        return form().getOk();
    }

    public GuiStackForm getGuiEnterStackForm() {
        return form().getGuiEnterStackForm();
    }

    public GuiStackForm getGuiExitStackForm() {
        return form().getGuiExitStackForm();
    }
}
