package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;

public interface PossibleIntermediateDotted {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(ClassArgumentMatching _previousResultClass,MethodAccessKind _static);

    void setPreviousArgument(Argument _argument);
}
