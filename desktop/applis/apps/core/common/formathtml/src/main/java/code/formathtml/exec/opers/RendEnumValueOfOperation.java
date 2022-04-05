package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendEnumValueOfOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecValuesContent valuesContent;

    public RendEnumValueOfOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {

        setRelOffsetPossibleLastPage(valuesContent.getArgOffset(), _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(IndirectCalledFctUtil.tryGetEnumValue(_context.getExiting(), _context, valuesContent.getRootBlock(), ClassCategory.ENUM, getArgument(_nodes, getFirstChild()), _rendStack.getStackCall()), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
