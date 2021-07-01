package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecInstancingCustContent extends ExecInstancingCommonContent {

    private final ExecFormattedRootBlock formattedType;
    private final ExecTypeFunction pair;

    public ExecInstancingCustContent(AnaInstancingCommonContent _cont,ExecTypeFunction _pair, Forwards _fwd) {
        this(_cont,_pair, FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd));
    }
    public ExecInstancingCustContent(AnaInstancingCommonContent _cont,ExecTypeFunction _pair, ExecFormattedRootBlock _formattedType) {
        super(_cont);
        pair = _pair;
        formattedType = _formattedType;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

}
