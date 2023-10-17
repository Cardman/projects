package code.expressionlanguage;

import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.RowGraphicList;

public final class CellPrepareCustomEvents extends CommonCellPrepareCustomEvents {


    public CellPrepareCustomEvents(RowGraphicList<Struct> _r, int _i, Struct _f, Struct _o, GraphicListStruct _g) {
        super(_r, _i, _f, _o, _g);
    }

    @Override
    protected boolean excluded() {
        if (getRow() == null) {
            return false;
        }
        return !getRow().isDirty();
    }
}
