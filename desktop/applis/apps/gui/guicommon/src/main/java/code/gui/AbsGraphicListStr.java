package code.gui;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public interface AbsGraphicListStr extends AbsGraphicList<Struct> {
    void setCell(Struct _grComp, ContextEl _ctx, PreparedLabel _lab, Struct _labStruct, SpecSelectionCtx _create, SpecSelectionStruct _cell, SpecSelectionStruct _width, SpecSelectionStruct _height);
}
