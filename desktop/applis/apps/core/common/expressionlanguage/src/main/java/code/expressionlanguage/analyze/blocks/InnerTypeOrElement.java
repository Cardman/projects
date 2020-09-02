package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    OperationNode getRoot();
    void buildExpressionLanguageReadOnly(ContextEl _cont, ExecInnerTypeOrElement _exec);
}
