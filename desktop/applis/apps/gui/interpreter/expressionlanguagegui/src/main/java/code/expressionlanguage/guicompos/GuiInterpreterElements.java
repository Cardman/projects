package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractProgramInfos;

public final class GuiInterpreterElements {
    private final AbstractProgramInfos programInfos;
    private final GuiFactroy guiFactroy;
    private GuiRunnable guiRunnable;

    public GuiInterpreterElements(AbstractProgramInfos _programInfos, GuiFactroy _guiFactroy) {
        this.programInfos = _programInfos;
        this.guiFactroy = _guiFactroy;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public GuiFactroy getGuiFactroy() {
        return guiFactroy;
    }

    public GuiRunnable getGuiRunnable() {
        return guiRunnable;
    }

    public void setGuiRunnable(GuiRunnable _guiRunnable) {
        this.guiRunnable = _guiRunnable;
    }
}
