package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbsPrepareCustomEvents;
import code.expressionlanguage.ExecCallPrepareCustomEvents;
import code.expressionlanguage.structs.Struct;

public final class FutureParamDbgStruct extends AbsDbgFutureStruct {

    public FutureParamDbgStruct(Struct _c) {
        super(_c);
    }

    @Override
    public AbsPrepareCustomEvents build() {
        return new ExecCallPrepareCustomEvents();
    }
}
