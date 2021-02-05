package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class GuiFactroy {
    private final AbstractGraphicListGenerator<Struct> graphicListGenerator;

    public GuiFactroy(AbstractGraphicListGenerator<Struct> _graphicListGenerator) {
        graphicListGenerator = _graphicListGenerator;
    }

    public AbstractGraphicListGenerator<Struct> getGraphicListGenerator() {
        return graphicListGenerator;
    }
}
