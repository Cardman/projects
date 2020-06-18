package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    void buildExpressionLanguageReadOnly(ContextEl _cont, ExecInnerTypeOrElement _exec);
}
