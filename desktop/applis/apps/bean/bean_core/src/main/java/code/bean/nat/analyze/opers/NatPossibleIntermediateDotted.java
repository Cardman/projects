package code.bean.nat.analyze.opers;

import code.expressionlanguage.functionid.MethodAccessKind;

public interface NatPossibleIntermediateDotted {

    void setIntermediateDotted();
    boolean isIntermediateDottedOperation();

    void setPreviousResultClass(String _previousResultClass, MethodAccessKind _static);

}
