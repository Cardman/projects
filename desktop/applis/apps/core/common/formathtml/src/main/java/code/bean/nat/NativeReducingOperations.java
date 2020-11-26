package code.bean.nat;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.AbstractReducingOperations;
import code.util.CustList;

public final class NativeReducingOperations implements AbstractReducingOperations {
    @Override
    public CustList<OperationNode> reduced(CustList<OperationNode> _list, AnalyzedPageEl _page) {
        return _list;
    }
}
