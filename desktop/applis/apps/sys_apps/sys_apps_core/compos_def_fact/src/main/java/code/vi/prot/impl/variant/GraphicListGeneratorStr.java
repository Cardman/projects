package code.vi.prot.impl.variant;

import code.gui.*;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class GraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr create(boolean _simple, AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new GraphicListStr(_simple, _abs,_create);
    }

    @Override
    public boolean isCust() {
        return false;
    }
}
