package code.vi.prot.impl.variant;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

public final class GraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr createMult(AbsGraphicListPainter _abs, SpecSelectionCtx _create, AbsCompoFactory _compoFactory) {
        return new GraphicListStr(false, _abs,_create,_compoFactory);
    }

    @Override
    public AbsGraphicListStr createSimple(AbsGraphicListPainter _abs, SpecSelectionCtx _create, AbsCompoFactory _compoFactory) {
        return new GraphicListStr(true, _abs,_create,_compoFactory);
    }

    @Override
    public boolean isCust() {
        return false;
    }
}
