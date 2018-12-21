package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.opers.AbstractUnaryOperation;
import code.util.CustList;

public abstract class ExecAbstractUnaryOperation extends ExecReflectableOpering {

    public ExecAbstractUnaryOperation(AbstractUnaryOperation _a) {
        super(_a);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        CustList<ExecDynOperationNode> children_ = getChildrenNodes();
        if (children_.isEmpty()) {
            return;
        }
        for (ExecDynOperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        quickCalculate(_conf);
    }
}
