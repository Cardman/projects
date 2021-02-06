package code.gui;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

import javax.swing.*;

public final class CustGrListStr extends CustGrList<Struct> implements AbsCustGraphicListStr {
    public CustGrListStr(boolean _simple) {
        super(_simple);
    }

    @Override
    public void setCell(ContextEl _ctx, PreparedLabel _lab, Struct _labStruct, SpecSelectionCtx _create, SpecSelectionStruct _cell, SpecSelectionStruct _width, SpecSelectionStruct _height) {
        if (_cell != null) {
            CustSelListStr cell_ = new CustSelListStr(_lab, _labStruct, _cell, _width, _height);
            getListView().setCellRenderer(cell_);
        } else {
            DefSelListStr cell_ = new DefSelListStr(_create,new DefaultListCellRenderer());
            getListView().setCellRenderer(cell_);
        }
    }
}
