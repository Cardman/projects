package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendImplicitOperation extends RendAbstractUnaryOperation {

    private final ExecExplicitContent explicitContent;
    private final ExecTypeFunction pair;
    public RendImplicitOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecExplicitContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _rendStack);
        CustList<Argument> first_ = getArguments(_nodes, this);
        Argument argres_ = RendDynOperationNode.processCall(RendExplicitOperation.prepare(_context.getExiting(), pair, false, first_, explicitContent.getClassName(), explicitContent.getClassNameOwner(), _context, _stack), _context, _stack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
