package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.util.CollCapacity;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CausingErrorStruct extends WithoutParentIdStruct implements ErroneousStruct {

    private final ArrayStruct stack;
    private final ArrayStruct fullStack;

    private final Struct cause;
    private final String message;

    public CausingErrorStruct(Struct _cause, ContextEl _cont) {
        this("", _cause,_cont);
    }

    public CausingErrorStruct(String _message, ContextEl _cont) {
        this(_message, NullStruct.NULL_VALUE, _cont);
    }

    private CausingErrorStruct(String _message,Struct _cause, ContextEl _cont) {
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
        return _contextEl.getStandards().getContent().getCoreNames().getAliasErrorInitClass();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getStringRep(_an, stack));
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


    public String getStringRep(ContextEl _an, ArrayStruct _arrInst) {
        StringList str_ = new StringList(new CollCapacity(_arrInst.getLength()+2));
        str_.add(getClassName(_an));
        str_.add(message);
        for (Struct s: _arrInst.getInstance()) {
            str_.add(StackTraceElementStruct.getStack(s).getStringRep());
        }
        return StringUtil.join(str_, "\n");
    }
}
