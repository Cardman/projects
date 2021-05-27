package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecImplicitOperation;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecExplicitCommonContent;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendImplicitOperation extends RendAbstractUnaryOperation {

    private final ExecExplicitCommonContent explicitContent;

    public RendImplicitOperation(ExecOperationContent _content, ExecExplicitCommonContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _rendStack);
        ArgumentListCall list_ = ExecMethodOperation.listNamedArguments(buildInfos(_nodes)).getArguments();
        Argument argres_ = RendDynOperationNode.processCall(ExecImplicitOperation.getArgument(explicitContent.getClassName(), _context, _rendStack.getStackCall(), list_), _context, _rendStack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
