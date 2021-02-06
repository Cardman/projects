package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class GuiFactroy {
    private final AbstractAdvGraphicListGenerator graphicListGenerator;

    public GuiFactroy(AbstractAdvGraphicListGenerator _graphicListGenerator) {
        graphicListGenerator = _graphicListGenerator;
    }

    public AbstractAdvGraphicListGenerator getGraphicListGenerator() {
        return graphicListGenerator;
    }
}
