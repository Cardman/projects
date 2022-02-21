package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.CallersInfo;

public abstract class ExecAbstractLambdaOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private final ExecLambdaCommonContent lambdaCommonContent;
    private final String result;

    protected ExecAbstractLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont);
        lambdaCommonContent = _lamCont;
        result = _lamCont.getResult();
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return getLambdaCommonContent().isIntermediate();
    }

    public ExecLambdaCommonContent getLambdaCommonContent() {
        return lambdaCommonContent;
    }

    protected CallersInfo format(ExecLambdaMethodContent _caller,StackCall _stack) {
        return build(_caller, formatVarType(_stack));
    }

    public static CallersInfo build(ExecLambdaMethodContent _caller, ExecFormattedRootBlock _formatted) {
        return new CallersInfo(_caller.getModifier(), _caller.getPair().getFct(), _caller.getPair(), _formatted,null);
    }

    public String formatVarTypeRes(StackCall _stack) {
        return _stack.formatVarType(result);
    }

    public ExecFormattedRootBlock formatVarType(StackCall _stack) {
        return StackCall.formatVarType(_stack,getFoundClass());
    }
    public ExecFormattedRootBlock getFoundClass() {
        return lambdaCommonContent.getFormattedType();
    }
}
