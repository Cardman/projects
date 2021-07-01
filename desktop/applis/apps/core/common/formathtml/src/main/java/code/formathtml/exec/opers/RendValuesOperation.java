package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(valuesContent.getArgOffset(), _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(ExecInvokingOperation.tryGetEnumValues(_context.getExiting(), _context, valuesContent.getRootBlock(), ClassCategory.ENUM, _rendStack.getStackCall()), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
