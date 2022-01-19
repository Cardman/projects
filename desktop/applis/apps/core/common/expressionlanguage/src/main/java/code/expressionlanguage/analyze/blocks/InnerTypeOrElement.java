package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.CustList;
import code.util.Ints;

public interface InnerTypeOrElement extends InfoBlock {
    String getUniqueFieldName();
    OperationNode getRoot();
    void buildExpressionLanguageReadOnly(AnalyzedPageEl _page);
    int getFieldNameOffset();
    CustList<AnaResultPartType> getTypePartOffsets();
    CustList<OperationNode> getRoots();
    boolean koType();
    Ints getLastBadIndexes();
}
