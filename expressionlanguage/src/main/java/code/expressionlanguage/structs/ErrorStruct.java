package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.util.CollCapacity;
import code.util.StringList;

public final class ErrorStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final String className;

    private final String message;

    public ErrorStruct(ContextEl _context, String _className) {
        this(_context, "", _className);
    }

    public ErrorStruct(ContextEl _context, String _message, String _className) {
        stack = _context.newStackTraceElementArray();
        fullStack = _context.newStackTraceElementArrayFull();
        className = _className;
        message = _message;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        Struct[] calls_ = stack.getInstance();
        return new StringStruct(getStringRep(_an,calls_));
    }

    public String getStringRep(ContextEl _an,Struct[] _array) {
        StringList str_ = new StringList(new CollCapacity(_array.length+2));
        str_.add(className);
        str_.add(message);
        for (Struct s: _array) {
            str_.add(StackTraceElementStruct.getStack(s).getStringRep());
        }
        return StringList.join(str_, "\n");
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
