package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CallDynMethodOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCallDynMethodOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String fctName;
    public RendCallDynMethodOperation(CallDynMethodOperation _call) {
        super(_call);
        fctName = _call.getFctName();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        if (StringList.quickEq(fctName,_conf.getStandards().getAliasMetaInfo())) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, _conf.getContext());
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        if (StringList.quickEq(fctName,_conf.getStandards().getAliasInstance())) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, _conf.getContext());
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return ExecInvokingOperation.prepareCallDyn(_previous, _arguments, _conf.getContext());
    }
}
