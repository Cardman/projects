package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractLambdaOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private final ExecLambdaCommonContent lambdaCommonContent;

    protected ExecAbstractLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont);
        lambdaCommonContent = _lamCont;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return getLambdaCommonContent().isIntermediate();
    }

    public ExecLambdaCommonContent getLambdaCommonContent() {
        return lambdaCommonContent;
    }

    public ExecFormattedRootBlock getFoundClass() {
        return lambdaCommonContent.getFormattedType();
    }
}
