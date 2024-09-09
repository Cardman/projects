package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.Struct;

public final class NoExiting implements AbstractExiting {

    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className) {
        return false;
    }

    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className, Struct _arg) {
        return false;
    }

    @Override
    public CallingState state(StackCall _stack, GeneType _className, Struct _arg) {
        return null;
    }
}
