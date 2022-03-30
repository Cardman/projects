package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecAnnotationMethodOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecCallFctAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendAnnotationMethodOperation extends RendInvokingOperation  implements RendCalculableOperation {

    private final ExecCallFctAnnotContent callFctAnnotContent;

    public RendAnnotationMethodOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecCallFctAnnotContent _callFctAnnotContent) {
        super(_content, _intermediateDottedOperation);
        callFctAnnotContent = _callFctAnnotContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        Argument res_ = getArgument(previous_, _context, _rendStack);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

    Argument getArgument(Argument _previous, ContextEl _context, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _rendStackCall);
        return ExecAnnotationMethodOperation.getAnnotation(_previous, callFctAnnotContent.getClassMethodId(), _context, _rendStackCall.getStackCall());
    }
}
