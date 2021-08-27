package code.sys.impl;

import code.expressionlanguage.structs.Struct;
import code.gui.*;

public final class GraphicListStr extends GraphicList<Struct> implements AbsGraphicListStr {
    public GraphicListStr(boolean _simple, AbsGraphicListPainter _graphicListPainter) {
        super(_simple, _graphicListPainter);
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        FrameUtil.commonSet(_grComp, this);
    }

    @Override
    public void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        FrameUtil.commonSet(_grComp, this);
    }

}
