package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.util.CustList;

public interface RetrieveConstructor {
    OperationNode getFirstChild();
    CustList<ConstructorInfo> getCtors();
}
