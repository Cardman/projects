package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;


public abstract class PrimitiveBoolOperation extends MethodOperation {

    public PrimitiveBoolOperation(int _index,
            int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }

    @Override
    public final ConstructorId getConstId() {
        return null;
    }

}
