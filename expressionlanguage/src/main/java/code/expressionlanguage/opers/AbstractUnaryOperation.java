package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.util.CustList;
import code.util.EqList;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.isEmpty()) {
            return;
        }
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<OperationNode> children_ = getChildrenNodes();
        if (children_.isEmpty()) {
            return;
        }
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
