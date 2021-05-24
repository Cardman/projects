package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendEnumValueOfOperation extends RendAbstractUnaryOperation {

    private final ExecValuesContent valuesContent;

    public RendEnumValueOfOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {

        setRelativeOffsetPossibleLastPage(getIndexInEl()+ valuesContent.getArgOffset(), _rendStack);
        Argument argres_ = RendDynOperationNode.processCall(ExecInvokingOperation.tryGetEnumValue(_context.getExiting(), _context, valuesContent.getRootBlock(), ClassCategory.ENUM, getArgument(_nodes, getFirstChild()), _rendStack.getStackCall()), _context, _rendStack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
