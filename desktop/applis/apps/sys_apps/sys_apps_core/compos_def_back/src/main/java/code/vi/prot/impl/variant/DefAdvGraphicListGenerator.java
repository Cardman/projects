package code.vi.prot.impl.variant;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

import javax.swing.*;

public final class DefAdvGraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {

    @Override
    public AbsGraphicList<T> createMult(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new DefCustGrList<T>(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,_render);
    }

    @Override
    public AbsGraphicList<T> createSimple(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new DefCustGrList<T>(ListSelectionModel.SINGLE_SELECTION,_render);
    }
}
