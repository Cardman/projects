package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public interface AbstractReducingOperations {
    CustList<OperationNode> reduced(CustList<OperationNode> _list, AnalyzedPageEl _page);
}
