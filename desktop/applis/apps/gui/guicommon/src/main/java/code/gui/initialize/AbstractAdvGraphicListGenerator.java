package code.gui.initialize;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;

public interface AbstractAdvGraphicListGenerator {

    AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs, SpecSelectionCtx _create);
    boolean isCust();
}
