package code.formathtml.exec.opers;

import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class RendAbstractLambdaOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private final ExecLambdaCommonContent lambdaCommonContent;

    protected RendAbstractLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
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

    public String getFoundClass() {
        return lambdaCommonContent.getFoundClass();
    }

}
