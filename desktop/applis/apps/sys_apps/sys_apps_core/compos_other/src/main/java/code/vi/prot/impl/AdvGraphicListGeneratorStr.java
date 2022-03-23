package code.vi.prot.impl;

import code.gui.AbsGraphicListPainter;
import code.gui.AbsGraphicListStr;
import code.gui.SpecSelectionCtx;
import code.vi.prot.impl.other.CustGrListStr;
import code.gui.initialize.AbstractAdvGraphicListGenerator;

import javax.swing.*;

public final class AdvGraphicListGeneratorStr  implements AbstractAdvGraphicListGenerator {

    @Override
    public AbsGraphicListStr createMult(AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new CustGrListStr(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,_create);
    }

    @Override
    public AbsGraphicListStr createSimple(AbsGraphicListPainter _abs, SpecSelectionCtx _create) {
        return new CustGrListStr(ListSelectionModel.SINGLE_SELECTION,_create);
    }

    @Override
    public boolean isCust() {
        return true;
    }
}
