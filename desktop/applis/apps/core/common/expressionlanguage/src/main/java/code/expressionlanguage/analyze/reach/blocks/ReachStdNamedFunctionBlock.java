package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.OverridableBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;

public final class ReachStdNamedFunctionBlock extends ReachNamedFunctionBlock {
    private NamedFunctionBlock meta;
    private boolean abstractMethod;
    protected ReachStdNamedFunctionBlock(NamedFunctionBlock _info) {
        super(_info);
        meta = _info;
        if (_info instanceof OverridableBlock) {
            abstractMethod = ((OverridableBlock) _info).isAbstractMethod();
        }
    }
    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        checkReturnFct(_anEl, _page);
    }

    private void checkReturnFct(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
        if (!StringList.quickEq(meta.getImportedReturnType(), stds_.getAliasVoid())) {
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
