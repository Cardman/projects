package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractInstancingOperation extends
        ExecInvokingOperation {

    private final boolean initBefore;
    private final ExecTypeFunction pair;
    private final ExecInstancingCommonContent instancingCommonContent;

    protected ExecAbstractInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation,
                                              boolean _initBefore, ExecTypeFunction _pair, ExecInstancingCommonContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation);
        initBefore = _initBefore;
        pair = _pair;
        instancingCommonContent = _instancingCommonContent;
    }

    public boolean isInitBefore() {
        return initBefore;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecInstancingCommonContent getInstancingCommonContent() {
        return instancingCommonContent;
    }
}
