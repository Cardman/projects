package code.gui.initialize;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;

public interface AbstractAdvGraphicListGenerator {

    AbsGraphicListStr createSimple(AbsGraphicListPainter _abs, SpecSelectionCtx _create, AbsCompoFactory _compoFactory);
    AbsGraphicListStr createMult(AbsGraphicListPainter _abs, SpecSelectionCtx _create, AbsCompoFactory _compoFactory);
    boolean isCust();
}
