package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;

public interface ExecPossibleIntermediateDotted {
    Argument getPreviousArgument();

    boolean isIntermediateDottedOperation();
}
