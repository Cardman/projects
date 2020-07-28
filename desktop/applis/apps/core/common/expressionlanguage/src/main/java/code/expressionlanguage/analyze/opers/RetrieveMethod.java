package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;

public interface RetrieveMethod {
    OperationNode getFirstChild();
    MethodAccessKind isStaticAccess();
    String getMethodFound();
    CustList<CustList<MethodInfo>> getMethodInfos();
}
