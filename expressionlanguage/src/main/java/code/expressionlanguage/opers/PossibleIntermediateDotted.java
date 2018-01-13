package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface PossibleIntermediateDotted {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();
    ClassArgumentMatching getPreviousResultClass();
    void setPreviousResultClass(ClassArgumentMatching _previousResultClass);
    void setPreviousResultClass(ClassArgumentMatching _previousResultClass,boolean _static);
    Argument getPreviousArgument();
    void setPreviousArgument(Argument _argument);
}
