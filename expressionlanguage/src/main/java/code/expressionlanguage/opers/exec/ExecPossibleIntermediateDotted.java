package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;

public interface ExecPossibleIntermediateDotted extends ExecOperable {

    Argument getPreviousArgument();
    void setPreviousArgument(Argument _argument);
    int getOrder();
    boolean isIntermediateDottedOperation();
}
