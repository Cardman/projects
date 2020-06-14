package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.CallDynMethodOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCallDynMethodOperation extends ExecInvokingOperation {

    public ExecCallDynMethodOperation(CallDynMethodOperation _call) {
        super(_call);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_= getPreviousArg(this, _nodes, _conf);
        Argument res_ = prepareCallDyn(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

}
