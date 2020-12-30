package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecParentInstanceContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendParentInstanceOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private final ExecParentInstanceContent parentInstanceContent;
    public RendParentInstanceOperation(ExecOperationContent _content, ExecParentInstanceContent _parentInstanceContent) {
        super(_content);
        parentInstanceContent = _parentInstanceContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument arg_ = getCommonArgument(_nodes, _context, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    Argument getCommonArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ parentInstanceContent.getOff(), _rendStackCall);
        Argument previous_ = getPreviousArg(this, _nodes, _rendStackCall);
        Struct struct_ = previous_.getStruct();
        return new Argument(ExecClassArgumentMatching.convert(struct_.getParent(), _context, getResultClass().getNames()));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }
}
