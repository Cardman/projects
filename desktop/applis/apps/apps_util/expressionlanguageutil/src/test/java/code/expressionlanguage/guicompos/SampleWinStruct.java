package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.mock.MockWindow;

public final class SampleWinStruct extends WindowStruct {
    public SampleWinStruct() {
        super(new MockWindow());
    }

    @Override
    public void setMenuBar(Struct _arg) {
        getClassName(null);
    }

    @Override
    public Struct getMenuBar() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return "";
    }
}
