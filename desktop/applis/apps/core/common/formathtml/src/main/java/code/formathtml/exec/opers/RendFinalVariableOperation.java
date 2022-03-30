package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendFinalVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private final ExecVariableContent variableContent;

    public RendFinalVariableOperation(ExecOperationContent _content, ExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _rendStack);
        Argument arg_ = ExecVariableTemplates.getIndexLoop(_context, variableContent, _rendStack.getPageEl().getCache(), _rendStack.getPageEl().getVars(), _rendStack.getStackCall());
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
