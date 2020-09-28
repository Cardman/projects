package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCallDynMethodOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String fctName;
    public RendCallDynMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _fctName) {
        super(_content, _intermediateDottedOperation);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        ContextEl context_ = _conf.getContext();
        if (StringList.quickEq(fctName,context_.getStandards().getAliasMetaInfo())) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, context_);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        if (StringList.quickEq(fctName,context_.getStandards().getAliasInstance())) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, context_);
            setSimpleArgument(res_, _conf, _nodes);
            return;
        }
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<Argument> arguments_ = getArguments(_all,this);
        return ExecInvokingOperation.prepareCallDyn(_previous, arguments_, _conf.getContext());
    }
}
