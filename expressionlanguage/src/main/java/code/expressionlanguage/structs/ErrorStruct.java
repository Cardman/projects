package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.util.CollCapacity;
import code.util.StringList;

public final class ErrorStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final String className;

    private final String message;

    public ErrorStruct(ExecutableCode _context, String _className) {
        this(_context, "", _className);
    }

    public ErrorStruct(ExecutableCode _context, String _message, String _className) {
        ContextEl cont_ = _context.getContextEl();
        stack = cont_.newStackTraceElementArray();
        fullStack = _context.getExecutingInstance().newStackTraceElementArray();
        className = _className;
        message = _message;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
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
    public StringStruct getDisplayedString(Analyzable _an) {
        Struct[] calls_ = stack.getInstance();
        return new StringStruct(getStringRep(_an,calls_));
    }

    public String getStringRep(Analyzable _an,Struct[] _array) {
        StringList str_ = new StringList(new CollCapacity(_array.length+2));
        str_.add(className);
        str_.add(message);
        for (Struct s: _array) {
            str_.add(((StackTraceElementStruct)s).getStringRep());
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
