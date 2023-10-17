package code.expressionlanguage;

import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;

public final class ForceCellPrepareCustomEvents extends CommonCellPrepareCustomEvents {

    public ForceCellPrepareCustomEvents(RowGraphicList<Struct> _r, int _i, Struct _f, Struct _o, GraphicListStruct _g) {
        super(_r, _i, _f, _o, _g);
    }

    @Override
    protected boolean excluded() {
        return false;
    }
}
