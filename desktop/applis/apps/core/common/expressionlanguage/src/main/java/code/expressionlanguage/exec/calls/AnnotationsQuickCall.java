package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;

public final class AnnotationsQuickCall implements AbstractQuickCall {
    @Override
    public Struct calculate(AbstractRefectCommonMethodPageEl _current, ContextEl _context, StackCall _stack) {
        MethodId dir_ = _current.getMetaInfo().getRealId();
        return ExecInvokingOperation.getAnnotation(_current.getInstance(),dir_.getName(),_context, _stack);
    }
}
