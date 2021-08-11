package code.bean.nat.analyze.opers;

import code.expressionlanguage.functionid.MethodAccessKind;

public interface NatPossibleIntermediateDotted {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(NatAnaClassArgumentMatching _previousResultClass, MethodAccessKind _static);

}
