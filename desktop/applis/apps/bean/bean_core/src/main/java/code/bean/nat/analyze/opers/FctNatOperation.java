package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.bean.nat.fwd.opers.NatAnaCallFctContent;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.stds.StandardMethod;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FctNatOperation extends InvokingNatOperation {

    private final NatAnaCallFctContent callFctContent;

    private StandardMethod standardMethod;

    public FctNatOperation(int _index,
                           int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new NatAnaCallFctContent(getOperations().getFctName());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        NatAnaClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            clCur_ = new NatAnaClassArgumentMatching(_page.getGlobalClass());
            setStaticAccess(_page.getStaticContext());
        }

        String trimMeth_ = callFctContent.getMethodName().trim();
        StringList l_ = clCur_.getNames();
        ClassMethodIdReturn clMeth_;
        clMeth_ = tryGetDeclaredCustMethod(isStaticAccess(), l_, trimMeth_, _page);
        callFctContent.update(clMeth_);
        standardMethod = clMeth_.getStandardMethod();
        setResultClass(voidToObject(new NatAnaClassArgumentMatching(clMeth_.getReturnType()), _page));
    }

    public NatAnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

}
