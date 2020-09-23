package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.BreakBlock;
import code.expressionlanguage.exec.blocks.ExecBreakBlock;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class ReachBreakBlock extends ReachAbruptBlock {
    private String label;
    protected ReachBreakBlock(BreakBlock _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        ExecBreakBlock exec_ = new ExecBreakBlock(getOffset(),label);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        super.abrupt(_anEl);
        boolean childOfBreakable_ = false;
        ReachBracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof ReachBreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof ReachLoop || b_ instanceof ReachSwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringList.quickEq(label, ((ReachBreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            return;
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_ = _anEl.getReachBreakables();
//        IdMap<ReachBreakBlock, IdMap<ReachBreakableBlock, IdList<ReachBracedBlock>>> breakablesAncestors_ = _anEl.getReachBreakablesAncestors();
//        ReachBracedBlock par_ = getParent();
//        IdList<ReachBracedBlock> pars_ = new IdList<ReachBracedBlock>();
        ReachBracedBlock a_ = b_;
//        while (par_ != a_) {
////            pars_.add(par_);
//            par_ = par_.getParent();
//        }
//        IdMap<ReachBreakableBlock, IdList<ReachBracedBlock>> id_;
//        id_ = new IdMap<ReachBreakableBlock, IdList<ReachBracedBlock>>();
//        id_.put((ReachBreakableBlock) a_, pars_);
//        breakablesAncestors_.put(this, id_);
        breakables_.put(this, (ReachBreakableBlock) a_);
    }

}
