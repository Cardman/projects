package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.StackCall;

public interface AbstractExiting {
    boolean hasToExit(StackCall _stack, GeneType _className);
    boolean hasToExit(StackCall _stack, GeneType _className, Argument _arg);
}
