package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface Operable {

    Argument getArgument();
    ParentOperable getParentOperable();
    void setSimpleArgument(Argument _argument);
    void setSimpleArgumentAna(Argument _argument, ContextEl _conf);
    ClassArgumentMatching getResultClass();
    int getOrder();
    int getIndexChild();
    int getIndexBegin();
    int getIndexInEl();

    PossibleIntermediateDottedOperable getSiblingSettable();

}
