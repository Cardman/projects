package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CallDynMethodOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendCallDynMethodOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    public RendCallDynMethodOperation(CallDynMethodOperation _call) {
        super(_call);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return ExecInvokingOperation.prepareCallDyn(_previous, _arguments, _conf.getContext());
    }
}
