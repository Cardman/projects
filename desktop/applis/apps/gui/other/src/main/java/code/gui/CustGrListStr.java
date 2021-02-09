package code.gui;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

import javax.swing.*;

public final class CustGrListStr extends CustGrList<Struct> implements AbsCustGraphicListStr {
    public CustGrListStr(boolean _simple) {
        super(_simple);
    }

    @Override
    public void setCell(Struct _grComp, ContextEl _ctx, PreparedLabel _lab, Struct _labStruct, SpecSelectionCtx _create, SpecSelectionStruct _cell) {
        if (_cell != null) {
            CustSelListStr cell_ = new CustSelListStr(_lab, _labStruct, _cell);
            getListView().setCellRenderer(cell_);
        } else {
            DefSelListStr cell_ = new DefSelListStr(_create,new DefaultListCellRenderer());
            getListView().setCellRenderer(cell_::getListCellRendererComponent);
        }
    }

    @Override
    public void updateGraphics() {
        getListView().repaint();
    }
}
