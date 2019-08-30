package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.util.CollCapacity;
import code.util.StringList;

public final class CausingErrorStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final Struct cause;
    private final String message;

    public CausingErrorStruct(Struct _cause, ExecutableCode _cont) {
        this("", _cause,_cont);
    }

    public CausingErrorStruct(String _message, ExecutableCode _cont) {
        this(_message, NullStruct.NULL_VALUE, _cont);
    }

    private CausingErrorStruct(String _message,Struct _cause, ExecutableCode _cont) {
        message = _message;
        cause = _cause;
        ContextEl cont_ = _cont.getContextEl();
        stack = cont_.newStackTraceElementArray();
        fullStack = _cont.getExecutingInstance().newStackTraceElementArray();
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    @Override
    public Struct getCause() {
        return cause;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasErrorInitClass();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        Struct[] calls_ = stack.getInstance();
        return new StringStruct(getStringRep(_an,calls_));
    }

    @Override
    public ArrayStruct getStack() {
        return stack;
    }

    @Override
    public ArrayStruct getFullStack() {
        return fullStack;
    }

    @Override
    public Struct getMessage() {
        return new StringStruct(message);
    }


    public String getStringRep(Analyzable _an, Struct[] _array) {
        StringList str_ = new StringList(new CollCapacity(_array.length+2));
        str_.add(getClassName(_an.getContextEl()));
        str_.add(message);
        for (Struct s: _array) {
            str_.add(((StackTraceElementStruct)s).getStringRep());
        }
        return StringList.join(str_, "\n");
    }
}
