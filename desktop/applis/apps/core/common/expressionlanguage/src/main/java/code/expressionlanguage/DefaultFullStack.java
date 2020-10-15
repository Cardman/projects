package code.expressionlanguage;

import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.structs.ArrayStruct;

public final class DefaultFullStack implements AbstractFullStack {
    private final ContextEl context;

    public DefaultFullStack(ContextEl _context) {
        this.context = _context;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        return ExecutingUtil.newStackTraceElementArray(context);
    }
}
