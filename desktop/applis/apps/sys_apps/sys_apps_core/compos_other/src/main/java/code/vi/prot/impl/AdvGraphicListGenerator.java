package code.vi.prot.impl;

import code.gui.AbsGraphicList;
import code.gui.CustCellRender;
import code.vi.prot.impl.other.CustGrList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractGraphicListGenerator;

import javax.swing.*;

public final class AdvGraphicListGenerator<T> implements AbstractGraphicListGenerator<T> {

    @Override
    public AbsGraphicList<T> createMult(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new CustGrList<>(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,_render);
    }

    @Override
    public AbsGraphicList<T> createSimple(AbstractImageFactory _fact, CustCellRender<T> _render) {
        return new CustGrList<>(ListSelectionModel.SINGLE_SELECTION,_render);
    }
}
