package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ContinueBlock;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ReachContinueBlock extends ReachAbruptBlock {

    private final String label;
    public ReachContinueBlock(ContinueBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
        boolean childOfLoop_ = false;
        ReachBracedBlock b_ = getParent();
        while (b_ != null) {
            if (exitLoop(b_)) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            return;
        }
        IdMap<ReachContinueBlock, ReachBreakableBlock> continuables_ = _anEl.getReachContinuables();
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
        continuables_.put(this, (ReachBreakableBlock) a_);
    }
    private boolean exitLoop(ReachBracedBlock _b) {
        if (ReachBreakBlock.isLoop(_b)) {
            if (label.isEmpty()) {
                return true;
            }
            return StringUtil.quickEq(label, ((ReachBreakableBlock) _b).getRealLabel());
        }
        return false;
    }
}
