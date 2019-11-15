package code.expressionlanguage.opers;

import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.MethodAccessKind;

public interface PossibleIntermediateDotted extends PossibleIntermediateDottedOperable {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(ClassArgumentMatching _previousResultClass,MethodAccessKind _static);
}
