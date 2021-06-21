package code.formathtml.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendExplicitOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecExplicitContent explicitContent;
    private final ExecTypeFunction pair;
    public RendExplicitOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecExplicitContent _explicitContent) {
        super(_content);
        explicitContent = _explicitContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ explicitContent.getOffset(), _rendStack);
        ArgumentListCall list_ = ExecMethodOperation.listNamedArguments(buildInfos(_nodes)).getArguments();
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(prepare(_context.getExiting(), pair, explicitContent.getFormattedType(), _context, _rendStack, list_), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public static Argument prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock,
                                   ExecFormattedRootBlock _classNameOwner, ContextEl _conf, RendStackCall _stackCall, ArgumentListCall _list) {
        ExecExplicitOperation.checkCustomOper(_exit, _rootBlock, _classNameOwner, _conf,null, _stackCall.getStackCall(), _list);
        return Argument.createVoid();
    }

}
