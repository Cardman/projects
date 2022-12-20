package code.gui;

import code.expressionlanguage.structs.Struct;

public interface AbsGraphicListStr extends AbsGraphicList<Struct>,AbsGraphicListDef {
    void setCustCell(Struct _grComp, AbsPreparedLabel _lab, Struct _labStruct, SpecSelectionStruct _cell);
    void setDefCell(Struct _grComp, SpecSelectionCtx _create);

    void updateGraphics();
    boolean isCust();
}
