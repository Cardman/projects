package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ErrorStruct extends AbstractCauseErrorStruct {

    private final String className;

    public ErrorStruct(ContextEl _context, String _className, StackCall _stackCall) {
        this(_context, "", _className, _stackCall);
    }

    public ErrorStruct(ContextEl _context, String _message, String _className, StackCall _stackCall) {
        super(_message,NullStruct.NULL_VALUE,_context,_stackCall);
        className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public String getClassName() {
        return className;
    }

}
