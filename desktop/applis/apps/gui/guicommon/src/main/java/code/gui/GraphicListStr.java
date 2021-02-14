package code.gui;

import code.expressionlanguage.structs.Struct;

public final class GraphicListStr extends GraphicList<Struct> implements AbsGraphicListStr {
    public GraphicListStr(boolean _simple, AbsGraphicListPainter _graphicListPainter) {
        super(_simple, _graphicListPainter);
    }

    @Override
    public void setDefCell(Struct _grComp, SpecSelectionCtx _create) {
        commonSet(_grComp);
    }

    @Override
    public void setCustCell(Struct _grComp, PreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell) {
        commonSet(_grComp);
    }

    private void commonSet(Struct _grComp) {
        AbsGraphicListPainter graphicListPainter_ = getGraphicListPainter();
        if (graphicListPainter_ != null) {
            graphicListPainter_.setValue(_grComp);
        }
    }
}
