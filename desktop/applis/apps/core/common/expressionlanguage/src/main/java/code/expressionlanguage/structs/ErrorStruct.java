package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.util.CollCapacity;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ErrorStruct extends WithoutParentIdStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final String className;

    private final String message;

    public ErrorStruct(ContextEl _context, String _className) {
        this(_context, "", _className);
    }

    public ErrorStruct(ContextEl _context, String _message, String _className) {
        stack = ExecutingUtil.newStackTraceElementArray(_context);
        fullStack = ExecutingUtil.newStackTraceElementArrayFull(_context);
        className = _className;
        message = _message;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getStringRep(_an, stack));
    }

    public String getStringRep(ContextEl _an, ArrayStruct _arrInst) {
        StringList str_ = new StringList(new CollCapacity(_arrInst.getLength()+2));
        str_.add(className);
        str_.add(message);
        for (Struct s: _arrInst.list()) {
            str_.add(StackTraceElementStruct.getStack(s).getStringRep());
        }
        return StringUtil.join(str_, "\n");
    }

    @Override
    public ArrayStruct getFullStack() {
        return fullStack;
    }

    @Override
    public Struct getCause() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public ArrayStruct getStack() {
        return stack;
    }

    @Override
    public Struct getMessage() {
        return new StringStruct(message);
    }

}
