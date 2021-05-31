package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    OperationNode getRoot();
    void buildExpressionLanguageReadOnly(AnalyzedPageEl _page);
    int getFieldNameOffset();

    CustList<OperationNode> getRoots();
}
