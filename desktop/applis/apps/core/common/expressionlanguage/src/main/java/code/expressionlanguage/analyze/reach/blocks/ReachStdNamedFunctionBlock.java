package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.core.StringUtil;

public final class ReachStdNamedFunctionBlock extends ReachNamedFunctionBlock {
    private final NamedFunctionBlock meta;
    private boolean abstractMethod;
    protected ReachStdNamedFunctionBlock(NamedFunctionBlock _info) {
        super(_info);
        meta = _info;
        if (AbsBk.isOverBlock(_info)) {
            abstractMethod = ((NamedCalledFunctionBlock) _info).isAbstractMethod();
        }
    }
    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        checkReturnFct(_anEl, _page);
    }

    private void checkReturnFct(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (!StringUtil.quickEq(meta.getImportedReturnType(), _page.getAliasVoid())) {
            if (!abstractMethod&&_anEl.canCompleteNormally(this)) {
                //error
                FoundErrorInterpret miss_ = new FoundErrorInterpret();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                //return type len
                miss_.buildError(_page.getAnalysisMessages().getMissingAbrupt(),
                        _page.getKeyWords().getKeyWordThrow(),
                        _page.getKeyWords().getKeyWordReturn(),
                        getPseudoSignature(_page));
                _page.addLocError(miss_);
                meta.addNameErrors(miss_);
            }
        }
    }
}
