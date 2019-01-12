package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AssocationOperation;
import code.util.IdMap;

public final class ExecAssocationOperation extends ExecAbstractUnaryOperation {

    private String fieldName;

    public ExecAssocationOperation(AssocationOperation _a) {
        super(_a);
        fieldName = _a.getFieldName();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = getArgument(_nodes,getFirstChild());
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
