package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;

public interface Operable {

    Argument getArgument();
    ParentOperable getParent();
    CustList<Operable> getChildrenOperable();
    void setSimpleArgument(Argument _argument);
    void setSimpleArgumentAna(Argument _argument, Analyzable _conf);
    ClassArgumentMatching getResultClass();
    int getOrder();
    int getIndexChild();
    int getIndexBegin();
    int getIndexInEl();

    PossibleIntermediateDottedOperable getSiblingSet();
    Operable getNextSibling();
}
