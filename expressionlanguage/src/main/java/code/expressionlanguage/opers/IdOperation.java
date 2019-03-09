package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.IdOperable;
import code.util.CustList;
import code.util.NatTreeMap;

public final class IdOperation extends AbstractUnaryOperation implements IdOperable {

    public IdOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        OperationNode first_ = getFirstChild();
        setResultClass(first_.getResultClass());
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
