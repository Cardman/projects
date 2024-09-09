package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.Struct;

public interface AbstractExiting {
    boolean hasToExit(StackCall _stack, GeneType _className);
    boolean hasToExit(StackCall _stack, GeneType _className, Struct _arg);
    CallingState state(StackCall _stack, GeneType _className, Struct _arg);
}
