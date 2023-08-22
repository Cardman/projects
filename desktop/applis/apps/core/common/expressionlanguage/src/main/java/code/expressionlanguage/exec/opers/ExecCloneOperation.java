package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecCloneOperation extends ExecSettableCallFctOperation {

    private final String methodName;

    public ExecCloneOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _methodName) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        methodName = _methodName;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = cloneArray(previous_, _conf, _stack);
        setResult(res_, _conf, _nodes, _stack);
    }

}
