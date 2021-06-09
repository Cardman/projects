package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecParentInstanceContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public final class RendParentInstanceOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private final ExecParentInstanceContent parentInstanceContent;
    private final StringList names;

    public RendParentInstanceOperation(ExecOperationContent _content, ExecParentInstanceContent _parentInstanceContent,StringList _names) {
        super(_content);
        parentInstanceContent = _parentInstanceContent;
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ parentInstanceContent.getOff(), _rendStack);
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        Struct struct_ = previous_.getStruct();
        Argument arg_ = new Argument(ExecClassArgumentMatching.convert(struct_.getParent(), _context, names));
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return parentInstanceContent.isIntermediate();
    }
}
