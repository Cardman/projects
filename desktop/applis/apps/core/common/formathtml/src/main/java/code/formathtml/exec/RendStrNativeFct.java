package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class RendStrNativeFct extends RendNativeFct {

    private final StackCall stackCall;

    public RendStrNativeFct(StackCall _stackCall) {
        stackCall = _stackCall;
    }

    @Override
    public Struct compute(Argument _arg, ContextEl _conf) {
        return ExecCatOperation.getDisplayable(_arg, _conf);
    }

    @Override
    public StackCall stack() {
        return stackCall;
    }
}
