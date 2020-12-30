package code.expressionlanguage;

import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;

public final class DefaultFullStack implements AbstractFullStack {
    private final ContextEl context;

    public DefaultFullStack(ContextEl _context) {
        this.context = _context;
    }

    @Override
    public ArrayStruct newStackTraceElementArray(StackCall _stack) {
        return ExecutingUtil.newStackTraceElementArray(context, _stack);
    }
}
