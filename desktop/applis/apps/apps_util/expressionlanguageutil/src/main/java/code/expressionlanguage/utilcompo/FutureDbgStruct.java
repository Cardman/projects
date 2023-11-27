package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbsPrepareCustomEvents;
import code.expressionlanguage.ExecRunPrepareCustomEvents;
import code.expressionlanguage.structs.Struct;

public final class FutureDbgStruct extends AbsDbgFutureStruct {

    public FutureDbgStruct(Struct _c) {
        super(_c);
    }

    @Override
    public AbsPrepareCustomEvents build() {
        return new ExecRunPrepareCustomEvents();
    }
}
