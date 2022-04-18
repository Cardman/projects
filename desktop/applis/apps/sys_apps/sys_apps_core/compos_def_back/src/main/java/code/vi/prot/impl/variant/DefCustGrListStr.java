package code.vi.prot.impl.variant;

import code.expressionlanguage.structs.Struct;
import code.gui.AbsGraphicListStr;
import code.gui.AbsPreparedLabel;
import code.gui.SpecSelectionCtx;
import code.gui.SpecSelectionStruct;

import javax.swing.*;

public final class DefCustGrListStr extends DefCustGrList<Struct> implements AbsGraphicListStr {
    public DefCustGrListStr(int _simple, SpecSelectionCtx _create) {
        super(_simple,new DefDefSelListStr(_create,new DefaultListCellRenderer()));
    }

    @Override
    public void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        DefCustSelListStr cell_ = new DefCustSelListStr(_lab, _labStruct, _cell);
        getListView().setCellRenderer(cell_);
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        DefDefSelListStr cell_ = new DefDefSelListStr(_create,new DefaultListCellRenderer());
        getListView().setCellRenderer(cell_);
    }

    @Override
    public void updateGraphics() {
        getListView().repaint();
    }

    @Override
    public boolean isCust() {
        return true;
    }
}
