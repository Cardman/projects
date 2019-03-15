package code.expressionlanguage.opers;

import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface PossibleIntermediateDotted extends PossibleIntermediateDottedOperable {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(ClassArgumentMatching _previousResultClass,boolean _static);
}
