package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.util.CollCapacity;
import code.util.StringList;

public final class InvokeTargetErrorStruct extends WithoutParentIdStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final Struct cause;
    private final String message;

    public InvokeTargetErrorStruct(Struct _cause, ContextEl _cont) {
        this("", _cause, _cont);
    }

    private InvokeTargetErrorStruct(String _message, Struct _cause, ContextEl _cont) {
        message = _message;
        cause = _cause;
        stack = ExecutingUtil.newStackTraceElementArray(_cont);
        fullStack = ExecutingUtil.newStackTraceElementArrayFull(_cont);
    }

    @Override
    public Struct getCause() {
        return cause;
    }


    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasInvokeTarget();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
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

    public String getStringRep(ContextEl _an,Struct[] _arr) {
        StringList str_ = new StringList(new CollCapacity(_arr.length+2));
        str_.add(getClassName(_an));
        str_.add(message);
        for (Struct s: _arr) {
            str_.add(StackTraceElementStruct.getStack(s).getStringRep());
        }
        return StringList.join(str_, "\n");
    }

}
