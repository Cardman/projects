package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.util.CollCapacity;
import code.util.StringList;

public final class CausingErrorStruct implements ErroneousStruct {

    private final ArrayStruct stack;

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
        stack = StackTraceElementStruct.newStackTraceElementArray(cont_);
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
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
        return new StringStruct(getStringRep(_an.getStandards().getAliasErrorInitClass()));
    }

    @Override
    public ArrayStruct getStack() {
        return stack;
    }

    @Override
    public Struct getMessage() {
        return new StringStruct(message);
    }


    private String getStringRep(String _cl) {
        Struct[] calls_ = stack.getInstance();
        StringList str_ = new StringList(new CollCapacity(calls_.length+2));
        str_.add(_cl);
        str_.add(message);
        for (Struct s: calls_) {
            str_.add(((StackTraceElementStruct)s).getStringRep());
        }
        return StringList.join(str_, "\n");
    }
}
