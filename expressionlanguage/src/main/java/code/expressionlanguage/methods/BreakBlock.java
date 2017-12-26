package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadTagBreakException;
import code.expressionlanguage.stacks.BreakableBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.sml.Element;
import code.util.NatTreeMap;

public final class BreakBlock extends Leaf implements CallingFinally {

    public BreakBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        setStoppable(true);
    }

    public BreakBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_importingPage, _indexChild, _m);
        setStoppable(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        boolean childOfBreakable_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof CaseCondition) {
                ((CaseCondition)b_).setPossibleSkipNexts(true);
            }
            if (b_ instanceof BreakableBlock) {
                childOfBreakable_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadTagBreakException(_cont.joinPages());
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_BREAK;
    }

    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        BreakableBlockStack stack_ = null;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_ instanceof BreakableBlockStack) {
                stack_ = (BreakableBlockStack) bl_;
                bl_.getBlock().removeLocalVars(ip_);
                break;
            }
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        Block forLoopLoc_ = stack_.getBlock();
        rw_.setBlock(forLoopLoc_);
        stack_.setFinished(true);
    }

}
