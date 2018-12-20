package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface ExecPossibleIntermediateDotted extends ExecOperable {

    ClassArgumentMatching getPreviousResultClass();
    Argument getPreviousArgument();
    void setPreviousArgument(Argument _argument);
    int getOrder();
    boolean isIntermediateDottedOperation();
}
