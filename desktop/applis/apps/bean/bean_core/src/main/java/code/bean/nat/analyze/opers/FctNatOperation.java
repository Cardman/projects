package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.fwd.opers.NatAnaCallFctContent;

public final class FctNatOperation extends InvokingNatOperation {

    private final NatAnaCallFctContent callFctContent;

    public FctNatOperation(int _index,
                           int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new NatAnaCallFctContent(getOperations().getFctName());
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        String clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            clCur_ = _page.getGlobalClass();
        }

        String trimMeth_ = callFctContent.getMethodName().trim();
        String l_ = clCur_;
        NatClassMethodIdReturn clMeth_;
        clMeth_ = tryGetDeclaredCustMethod(l_, trimMeth_, _page);
        callFctContent.update(clMeth_);
        setResultClass(clMeth_.getReturnType());
    }

    public NatAnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

}
