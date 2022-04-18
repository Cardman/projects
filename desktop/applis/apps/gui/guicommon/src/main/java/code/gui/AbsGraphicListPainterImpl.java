package code.gui;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public abstract class AbsGraphicListPainterImpl implements AbsGraphicListPainter {
    private Struct value = NullStruct.NULL_VALUE;

    public Struct getValue() {
        return value;
    }

    @Override
    public void setValue(Struct _v) {
        this.value = _v;
    }
}
