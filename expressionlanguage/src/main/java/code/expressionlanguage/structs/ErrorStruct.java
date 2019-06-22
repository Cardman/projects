package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.util.CollCapacity;
import code.util.StringList;

public final class ErrorStruct implements ErroneousStruct {

    private final ArrayStruct stack;

    private final String className;

    private final String message;

    public ErrorStruct(ExecutableCode _context, String _className) {
        this(_context, "", _className);
    }

    public ErrorStruct(ExecutableCode _context, String _message, String _className) {
        ContextEl cont_ = _context.getContextEl();
        stack = StackTraceElementStruct.newStackTraceElementArray(cont_);
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
        return new StringStruct(getStringRep());
    }

    private String getStringRep() {
        Struct[] calls_ = stack.getInstance();
        StringList str_ = new StringList(new CollCapacity(calls_.length+2));
        str_.add(className);
        str_.add(message);
        for (Struct s: calls_) {
            str_.add(((StackTraceElementStruct)s).getStringRep());
        }
        return StringList.join(str_, "\n");
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
