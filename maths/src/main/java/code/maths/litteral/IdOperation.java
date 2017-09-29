package code.maths.litteral;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class IdOperation extends MethodOperation {

    public IdOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        setResultClass(chidren_.first().getResultClass());
    }

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        setArgument(chidren_.first().getArgument());
        setNextSiblingsArg(chidren_.first().getArgument());
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
