package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class InvokeTargetErrorStruct extends AbstractCauseErrorStruct {


    public InvokeTargetErrorStruct(Struct _cause, ContextEl _cont, StackCall _stackCall) {
        super("", _cause, _cont, _stackCall);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasInvokeTarget();
    }

}
