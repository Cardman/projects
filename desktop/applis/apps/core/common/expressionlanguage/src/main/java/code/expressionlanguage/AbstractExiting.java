package code.expressionlanguage;

import code.expressionlanguage.exec.StackCall;

public interface AbstractExiting {
    boolean hasToExit(StackCall _stack, String _className);
    boolean hasToExit(StackCall _stack, String _className, Argument _arg);
}
