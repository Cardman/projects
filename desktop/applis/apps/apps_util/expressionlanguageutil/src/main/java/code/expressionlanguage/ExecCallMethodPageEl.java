package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractBasicReflectPageEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsDbgFutureStruct;

public final class ExecCallMethodPageEl extends AbstractBasicReflectPageEl {
    private final AbsDbgFutureStruct future;
    private final Struct instance;
    private AbsPrepareCustomEvents callEvts;

    public ExecCallMethodPageEl(AbsDbgFutureStruct _f,Struct _g) {
        super(true);
        future = _f;
        instance = _g;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (callEvts == null) {
            callEvts = future.build();
        }
        if (callEvts.call(_context, _stack, instance, 0)) {
            return false;
        }
        future.setFound(getReturnedArgument().getStruct());
        return true;
    }

}
