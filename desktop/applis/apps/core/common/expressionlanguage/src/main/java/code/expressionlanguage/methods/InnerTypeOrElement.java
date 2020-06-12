package code.expressionlanguage.methods;

import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.util.CustList;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();

    CustList<ExecOperationNode> getOpValue();
}
