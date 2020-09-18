package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.functionid.MethodAccessKind;

public interface PossibleIntermediateDotted {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _static);

    void setPreviousArgument(Argument _argument);
}
