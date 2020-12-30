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

public final class RendEnumValueOfOperation extends RendAbstractUnaryOperation {

    private final ExecValuesContent valuesContent;

    public RendEnumValueOfOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context, _stack, _rendStack), _context, _stack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    Argument getCommonArgument(Argument _argument, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ valuesContent.getArgOffset(), _rendStackCall);
        return ExecInvokingOperation.tryGetEnumValue(_context.getExiting(), _context, valuesContent.getRootBlock(), ClassCategory.ENUM,_argument, _stackCall);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {

        return getCommonArgument(getArgument(_all,getFirstChild()), _context, _stackCall, _rendStackCall);
    }
}
