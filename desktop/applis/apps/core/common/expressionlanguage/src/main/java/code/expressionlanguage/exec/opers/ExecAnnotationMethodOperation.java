package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecCallFctAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnnotationMethodOperation extends ExecSettableCallFctOperation {

    private final ExecCallFctAnnotContent callFctAnnotContent;

    public ExecAnnotationMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecCallFctAnnotContent _callFctAnnotContent) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        callFctAnnotContent = _callFctAnnotContent;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Struct res_ = getAnnotation(previous_, callFctAnnotContent.getClassMethodId(), _conf, _stack);
        setResult(res_, _conf, _nodes, _stack);
    }

    public String getFieldName() {
        return callFctAnnotContent.getClassMethodId();
    }
}
