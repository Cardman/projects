package code.expressionlanguage.fcts;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

public abstract class FctError implements StdCaller {
    protected FctError() {
    }
    protected static ErroneousStruct getError(Struct _err, ContextEl _cont, StackCall _stackCall) {
        if (_err instanceof ErroneousStruct) {
            return (ErroneousStruct) _err;
        }
        String null_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
        return new ErrorStruct(_cont,null_, _stackCall);
    }
}
