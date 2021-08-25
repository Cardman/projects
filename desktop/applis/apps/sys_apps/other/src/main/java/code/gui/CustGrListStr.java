package code.gui;

import code.expressionlanguage.structs.Struct;

import javax.swing.*;

public final class CustGrListStr extends CustGrList<Struct> implements AbsCustGraphicListStr {
    public CustGrListStr(boolean _simple) {
        super(_simple);
    }

    @Override
    public void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        CustSelListStr cell_ = new CustSelListStr(_lab, _labStruct, _cell);
        getListView().setCellRenderer(cell_);
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        DefSelListStr cell_ = new DefSelListStr(_create,new DefaultListCellRenderer());
        getListView().setCellRenderer(cell_::getListCellRendererComponent);
    }

    @Override
    public void updateGraphics() {
        getListView().repaint();
    }
}
