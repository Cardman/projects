package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;

public final class GuiInterpreterElements {
    private final AbstractLightProgramInfos programInfos;
    private GuiRunnable guiRunnable;

    public GuiInterpreterElements(AbstractProgramInfos _programInfos) {
        this.programInfos = _programInfos;
    }

    public AbstractLightProgramInfos getProgramInfos() {
        return programInfos;
    }

    public GuiRunnable getGuiRunnable() {
        return guiRunnable;
    }

    public void setGuiRunnable(GuiRunnable _guiRunnable) {
        this.guiRunnable = _guiRunnable;
    }
}
