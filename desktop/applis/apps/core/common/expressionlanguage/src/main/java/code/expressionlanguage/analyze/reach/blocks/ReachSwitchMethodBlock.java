package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.SwitchMethodBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class ReachSwitchMethodBlock extends ReachMemberCallingsBlock implements ReachAnalyzedSwitch {
    private final AnaClassArgumentMatching result;
    private final String instanceTest;
    private final SwitchMethodBlock meta;

    protected ReachSwitchMethodBlock(SwitchMethodBlock _info) {
        super(_info);
        result = _info.getResult();
        instanceTest = _info.getInstanceTest();
        meta = _info;
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        boolean abrupt_ = ReachSwitchBlock.abruptCore(this,this,_anEl);
        if (abrupt_) {
            _anEl.completeAbruptGroup(this);
        }
    }


    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return meta.getSignature(_page);
    }

    @Override
    public void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (_anEl.canCompleteNormally(this)) {
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
            meta.addErrorBlock(miss_.getBuiltError());
        }
    }
}
