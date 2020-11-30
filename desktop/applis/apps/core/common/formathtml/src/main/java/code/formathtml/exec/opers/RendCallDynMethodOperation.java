package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendCallDynMethodOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String fctName;
    public RendCallDynMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _fctName) {
        super(_content, _intermediateDottedOperation);
        fctName = _fctName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasMetaInfo())) {
            Argument res_ = ExecInvokingOperation.getMetaInfo(previous_, _context);
            setSimpleArgument(res_, _conf, _nodes, _context);
            return;
        }
        if (StringUtil.quickEq(fctName, _context.getStandards().getContent().getReflect().getAliasInstance())) {
            Argument res_ = ExecInvokingOperation.getInstanceCall(previous_, _context);
            setSimpleArgument(res_, _conf, _nodes, _context);
            return;
        }
        Argument argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _context), _context).getValue();
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        CustList<Argument> arguments_ = getArguments(_all,this);
        return ExecInvokingOperation.prepareCallDyn(_previous, arguments_, _context);
    }
}
