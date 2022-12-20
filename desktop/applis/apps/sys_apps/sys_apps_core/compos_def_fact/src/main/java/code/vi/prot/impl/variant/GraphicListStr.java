package code.vi.prot.impl.variant;

import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;

public final class GraphicListStr extends GraphicList<Struct> implements AbsGraphicListStr {
    public GraphicListStr(boolean _simple, AbsGraphicListPainter _graphicListPainter, SpecSelectionCtx _create, AbsCompoFactory _compoFactory) {
        super(_simple, _graphicListPainter,  new DefaultCellRenderStr(_create,_graphicListPainter.getFact(),_compoFactory));
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        SelectionUtil.commonSet(_grComp, getGraphicListPainter());
    }

    @Override
    public void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        SelectionUtil.commonSet(_grComp, getGraphicListPainter());
    }

    @Override
    public boolean isCust() {
        return false;
    }
}
