package code.gui.initialize;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;

public interface AbstractAdvGraphicListGenerator {

    AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs);
    boolean isCust();
}
