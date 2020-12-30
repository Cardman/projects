package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendDirectStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecInstancingCommonContent instancingCommonContent;

    public RendDirectStandardInstancingOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument argres_ = getArgument(_nodes, _context, _stack, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes,
                         ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        return ExecInvokingOperation.instancePrepareStd(_context, instancingCommonContent.getClassName(), instancingCommonContent.getConstId(), fectchArgs(_nodes,instancingCommonContent.getLastType(),instancingCommonContent.getNaturalVararg(), _rendStackCall).getArguments(), _stackCall);
    }
}
