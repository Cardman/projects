package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractInstancingOperation extends
        ExecInvokingOperation {

    private final boolean initBefore;
    private final ExecInstancingCustContent instancingCommonContent;
    private final ExecFormattedRootBlock formattedType;

    protected ExecAbstractInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation,
                                              boolean _initBefore, ExecInstancingCustContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation);
        initBefore = _initBefore;
        instancingCommonContent = _instancingCommonContent;
        formattedType = _instancingCommonContent.getFormattedType();
    }

    public boolean isInitBefore() {
        return initBefore;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public ExecInstancingCustContent getInstancingCommonContent() {
        return instancingCommonContent;
    }
}
