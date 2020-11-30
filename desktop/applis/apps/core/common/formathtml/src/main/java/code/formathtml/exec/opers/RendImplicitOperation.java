package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendImplicitOperation extends RendAbstractUnaryOperation {

    private ExecExplicitContent explicitContent;
    private ExecTypeFunction pair;
    public RendImplicitOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecExplicitContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _conf);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context), _context).getValue();
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        CustList<Argument> first_ = getArguments(_all,this);
        return RendExplicitOperation.prepare(_context.getExiting(),pair,false,first_, explicitContent.getClassName(), explicitContent.getClassNameOwner(),_context);
    }
}
