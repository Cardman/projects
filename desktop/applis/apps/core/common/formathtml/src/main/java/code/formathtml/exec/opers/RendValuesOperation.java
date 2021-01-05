package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendValuesOperation extends RendLeafOperation implements RendCalculableOperation {

    private final ExecValuesContent valuesContent;

    public RendValuesOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ valuesContent.getArgOffset(), _rendStack);
        Argument argres_ = RendDynOperationNode.processCall(ExecInvokingOperation.tryGetEnumValues(_context.getExiting(), _context, valuesContent.getRootBlock(), ClassCategory.ENUM, _stack), _context, _stack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

}
