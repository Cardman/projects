package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendFinalVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private final ExecVariableContent variableContent;

    public RendFinalVariableOperation(ExecOperationContent _content, ExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _rendStack);
        Argument arg_ = ExecTemplates.getIndexLoop(_context, variableContent, _rendStack.getPageEl().getCache(), _rendStack.getPageEl().getVars(), _stack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

}
