package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;

public interface PossibleIntermediateDotted extends Operable {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(ClassArgumentMatching _previousResultClass,MethodAccessKind _static);

    void setPreviousArgument(Argument _argument);
}
