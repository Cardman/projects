package code.gui.initialize;

import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;

public interface AbstractAdvGraphicListGenerator {

    AbsGraphicListStr createSimple(SpecSelectionCtx _create, AbsCompoFactory _compoFactory);
    AbsGraphicListStr createMult(SpecSelectionCtx _create, AbsCompoFactory _compoFactory);
    boolean isCust();
}
