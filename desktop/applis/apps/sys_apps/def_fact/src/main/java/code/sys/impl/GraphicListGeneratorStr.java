package code.sys.impl;

import code.gui.*;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class GraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs) {
        return new GraphicListStr(_simple, _abs);
    }
}
