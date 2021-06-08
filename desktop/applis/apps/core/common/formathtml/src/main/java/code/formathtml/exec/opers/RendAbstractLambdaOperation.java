package code.formathtml.exec.opers;

import code.expressionlanguage.exec.opers.ExecAbstractLambdaOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.CallersInfo;

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

    protected CallersInfo format(ExecLambdaMethodContent _caller) {
        return ExecAbstractLambdaOperation.build(_caller,getFoundClass());
    }

    public String formatVarTypeRes() {
        return getResultClass().getSingleNameOrEmpty();
    }
    public ExecFormattedRootBlock getFoundClass() {
        return lambdaCommonContent.getFormattedType();
    }

}
