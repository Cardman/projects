package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.sml.Element;
import code.util.IdList;
import code.util.IdMap;
import code.util.NatTreeMap;

public final class ContinueBlock extends AbruptBlock implements CallingFinally {

    public ContinueBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        setStoppable(true);
    }

    public ContinueBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        setStoppable(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        boolean childOfLoop_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof Loop) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        super.abrupt(_an, _anEl);
        IdMap<ContinueBlock, Loop> continuables_ = _anEl.getContinuables();
        IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> continuablesAncestors_ = _anEl.getContinuablesAncestors();
        BracedBlock par_ = getParent();
        IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
        BracedBlock a_ = (BracedBlock) _anEl.getParentsContinuables().last();
        while (par_ != a_) {
            pars_.add(par_);
            par_ = par_.getParent();
        }
        IdMap<Loop, IdList<BracedBlock>> id_;
        id_ = new IdMap<Loop, IdList<BracedBlock>>();
        id_.put((Loop) a_, pars_);
        continuablesAncestors_.put(this, id_);
        continuables_.put(this, (Loop) a_);
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_CONTINUE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        Loop loop_ = null;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_ instanceof LoopBlockStack) {
                BracedBlock br_ = bl_.getBlock();
                loop_ = (Loop) br_;
                br_.removeLocalVars(ip_);
                break;
            }
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        loop_.processLastElementLoop(_conf);
    }
    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }

}
