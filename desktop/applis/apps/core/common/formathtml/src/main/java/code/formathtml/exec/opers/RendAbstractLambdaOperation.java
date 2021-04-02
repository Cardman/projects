package code.formathtml.exec.opers;

import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class RendAbstractLambdaOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private final ExecLambdaCommonContent lambdaCommonContent;

    public RendAbstractLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont);
        lambdaCommonContent = _lamCont;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return lambdaCommonContent.isIntermediate();
    }

    public boolean isSafeInstance() {
        return lambdaCommonContent.isSafeInstance();
    }

    public String getReturnFieldType() {
        return lambdaCommonContent.getReturnFieldType();
    }

    public boolean isShiftArgument() {
        return lambdaCommonContent.isShiftArgument();
    }

    public String getFileName() {
        return lambdaCommonContent.getFileName();
    }

    public int getAncestor() {
        return lambdaCommonContent.getAncestor();
    }

    public String getFoundClass() {
        return lambdaCommonContent.getFoundClass();
    }

}
