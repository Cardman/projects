package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCloneOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendCloneOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final String methodName;

    public RendCloneOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, String _methodName) {
        super(_content, _intermediateDottedOperation);
        methodName = _methodName;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStack);
        Argument argres_ = ExecCloneOperation.cloneArray(previous_, _context, _rendStack.getStackCall());
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public String getMethodName() {
        return methodName;
    }
}
