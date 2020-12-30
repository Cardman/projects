package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;

public final class NoExiting implements AbstractExiting {

    @Override
    public boolean hasToExit(StackCall _stack, String _className) {
        return false;
    }

    @Override
    public boolean hasToExit(StackCall _stack, String _className, Argument _arg) {
        return false;
    }
}
