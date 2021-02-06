package code.gui;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public final class GraphicListStr extends GraphicList<Struct> implements AbsGraphicListStr {
    public GraphicListStr(boolean _simple, AbsGraphicListPainter _graphicListPainter) {
        super(_simple, _graphicListPainter);
    }

    @Override
    public void setCell(ContextEl _ctx, PreparedLabel _lab, Struct _labStruct, SpecSelectionCtx _create, SpecSelectionStruct _cell, SpecSelectionStruct _width, SpecSelectionStruct _height) {
        //
    }
}
