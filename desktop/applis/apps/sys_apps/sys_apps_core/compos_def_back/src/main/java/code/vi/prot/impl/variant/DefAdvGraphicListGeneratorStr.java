package code.vi.prot.impl.variant;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

import javax.swing.*;

public final class DefAdvGraphicListGeneratorStr implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr createMult(AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new DefCustGrListStr(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,_create);
    }

    @Override
    public AbsGraphicListStr createSimple(AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new DefCustGrListStr( ListSelectionModel.SINGLE_SELECTION,_create);
    }

    @Override
    public boolean isCust() {
        return true;
    }
}
