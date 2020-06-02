package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;

public interface ExecPossibleIntermediateDotted extends Operable {
    Argument getPreviousArgument();

    boolean isIntermediateDottedOperation();
}
