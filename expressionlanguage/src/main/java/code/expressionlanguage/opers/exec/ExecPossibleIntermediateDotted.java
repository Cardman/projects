package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;

public interface ExecPossibleIntermediateDotted extends PossibleIntermediateDottedOperable {
    Argument getPreviousArgument();

    boolean isIntermediateDottedOperation();
}
