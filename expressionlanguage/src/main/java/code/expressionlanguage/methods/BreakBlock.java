package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.stacks.*;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class BreakBlock extends AbruptBlock implements CallingFinally {

    private String label;
    private int labelOffset;

    public BreakBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        boolean childOfBreakable_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof BreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof Loop || b_ instanceof SwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringList.quickEq(label, ((BreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        }
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        super.abrupt(_an, _anEl);
        boolean childOfBreakable_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof BreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof Loop || b_ instanceof SwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringList.quickEq(label, ((BreakableBlock)b_).getRealLabel())){
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
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getBreakablesAncestors();
        BracedBlock par_ = getParent();
        IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
        BracedBlock a_ = b_;
        while (par_ != a_) {
            pars_.add(par_);
            par_ = par_.getParent();
        }
        IdMap<BreakableBlock, IdList<BracedBlock>> id_;
        id_ = new IdMap<BreakableBlock, IdList<BracedBlock>>();
        id_.put((BreakableBlock) a_, pars_);
        breakablesAncestors_.put(this, id_);
        breakables_.put(this, (BreakableBlock) a_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        RemovableVars stack_;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            stack_ = bl_;
            if (label.isEmpty()) {
                if (bl_ instanceof LoopBlockStack || bl_ instanceof SwitchBlockStack) {
                    bl_.getBlock().removeLocalVars(ip_);
                    break;
                }
            } else {
                BreakableBlock br_ = (BreakableBlock) bl_.getBlock();
                if (StringList.quickEq(label, br_.getRealLabel())){
                    bl_.getCurrentVisitedBlock().removeLocalVars(ip_);
                    break;
                }
            }
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        Block forLoopLoc_ = stack_.getLastBlock();
        rw_.setBlock(forLoopLoc_);
        if (stack_ instanceof LoopStack) {
            ((LoopStack)stack_).setFinished(true);
        }
    }

}
