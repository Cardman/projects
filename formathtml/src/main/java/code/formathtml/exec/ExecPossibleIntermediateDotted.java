package code.formathtml.exec;

import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface ExecPossibleIntermediateDotted extends PossibleIntermediateDottedOperable {

    ClassArgumentMatching getPreviousResultClass();
    int getOrder();
    boolean isIntermediateDottedOperation();
}
