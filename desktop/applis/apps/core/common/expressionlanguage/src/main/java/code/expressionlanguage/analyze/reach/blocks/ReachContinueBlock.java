package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ContinueBlock;
import code.util.IdMap;
import code.util.StringList;

public final class ReachContinueBlock extends ReachAbruptBlock {

    private String label;
    protected ReachContinueBlock(ContinueBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
        boolean childOfLoop_ = false;
        ReachBracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof ReachLoop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringList.quickEq(label, ((ReachBreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            return;
        }
        IdMap<ReachContinueBlock, ReachLoop> continuables_ = _anEl.getReachContinuables();
//        IdMap<ReachContinueBlock, IdMap<ReachLoop, IdList<ReachBracedBlock>>> continuablesAncestors_ = _anEl.getReachContinuablesAncestors();
//        ReachBracedBlock par_ = getParent();
//        IdList<ReachBracedBlock> pars_ = new IdList<ReachBracedBlock>();
        ReachBracedBlock a_ = b_;
//        while (par_ != a_) {
////            pars_.add(par_);
//            par_ = par_.getParent();
//        }
//        IdMap<ReachLoop, IdList<ReachBracedBlock>> id_;
//        id_ = new IdMap<ReachLoop, IdList<ReachBracedBlock>>();
//        id_.put((ReachLoop) a_, pars_);
//        continuablesAncestors_.put(this, id_);
        continuables_.put(this, (ReachLoop) a_);
    }
}
