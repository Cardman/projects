package code.vi.prot.impl.variant;

import code.expressionlanguage.guicompos.AdvGraphicListPainter;
import code.expressionlanguage.guicompos.DefSpecSelectionCtx;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.vi.prot.impl.DefImageFactory;

public final class GraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr createMult(SpecSelectionCtx _create, AbsCompoFactory _compoFactory) {
        return new GraphicListStr(false, new AdvGraphicListPainter(new DefImageFactory(), ((DefSpecSelectionCtx)_create).getInterrupt(), ((DefSpecSelectionCtx)_create).getExecutionInfos(),((DefSpecSelectionCtx)_create).getArgs()),_create,_compoFactory);
    }

    @Override
    public AbsGraphicListStr createSimple(SpecSelectionCtx _create, AbsCompoFactory _compoFactory) {
        return new GraphicListStr(true, new AdvGraphicListPainter(new DefImageFactory(), ((DefSpecSelectionCtx)_create).getInterrupt(), ((DefSpecSelectionCtx)_create).getExecutionInfos(),((DefSpecSelectionCtx)_create).getArgs()),_create,_compoFactory);
    }

    @Override
    public boolean isCust() {
        return false;
    }
}
