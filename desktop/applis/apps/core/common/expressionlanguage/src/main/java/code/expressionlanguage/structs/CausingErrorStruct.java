package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class CausingErrorStruct extends AbstractCauseErrorStruct {

    public CausingErrorStruct(Struct _cause, ContextEl _cont, StackCall _stackCall) {
        super("", _cause,_cont, _stackCall);
    }

    public CausingErrorStruct(String _message, ContextEl _cont, StackCall _stackCall) {
        super(_message, NullStruct.NULL_VALUE, _cont, _stackCall);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getCoreNames().getAliasErrorInitClass();
    }
}
