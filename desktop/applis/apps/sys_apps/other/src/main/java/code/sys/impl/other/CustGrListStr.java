package code.sys.impl.other;

import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustGraphicListStr;
import code.gui.AbsPreparedLabel;
import code.gui.SpecSelectionCtx;
import code.gui.SpecSelectionStruct;

import javax.swing.*;

public final class CustGrListStr extends CustGrList<Struct> implements AbsCustGraphicListStr {
    public CustGrListStr(boolean _simple, SpecSelectionCtx _create) {
        super(_simple,new DefSelListStr(_create,new DefaultListCellRenderer()));
    }

    @Override
    public void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        CustSelListStr cell_ = new CustSelListStr(_lab, _labStruct, _cell);
        getListView().setCellRenderer(cell_);
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        DefSelListStr cell_ = new DefSelListStr(_create,new DefaultListCellRenderer());
        getListView().setCellRenderer(cell_);
    }

    @Override
    public void updateGraphics() {
        getListView().repaint();
    }
}
