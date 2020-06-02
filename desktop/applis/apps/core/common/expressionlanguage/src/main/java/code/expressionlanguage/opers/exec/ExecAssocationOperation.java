package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AssocationOperation;
import code.util.IdMap;

public final class ExecAssocationOperation extends ExecAbstractUnaryOperation {

    public ExecAssocationOperation(AssocationOperation _a) {
        super(_a);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = getArgument(_nodes,getFirstChild());
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
