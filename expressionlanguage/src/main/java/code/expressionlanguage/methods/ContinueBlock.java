package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class ContinueBlock extends AbruptBlock implements CallingFinally {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;

    public ContinueBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
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
        checkLoop(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkLoop(_cont);
    }

    private void checkLoop(ContextEl _cont) {
        boolean childOfLoop_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof Loop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringList.quickEq(label, ((BreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    labelOffsetRef = ((BreakableBlock) b_).getRealLabelOffset();
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
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
        boolean childOfLoop_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof Loop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringList.quickEq(label, ((BreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            return;
        }
        IdMap<ContinueBlock, Loop> continuables_ = _anEl.getContinuables();
        IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> continuablesAncestors_ = _anEl.getContinuablesAncestors();
        BracedBlock par_ = getParent();
        IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
        BracedBlock a_ = b_;
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
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        if (getLabel().isEmpty()) {
            return;
        }
        String tag_ = "<a href=\"#"+labelOffsetRef+"\">";
        _parts.add(new PartOffset(tag_,labelOffset));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,labelOffset+getLabel().length()));
    }
    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        Loop loop_;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_ instanceof LoopBlockStack) {
                BracedBlock br_ = bl_.getBlock();
                if (label.isEmpty()) {
                    br_.removeLocalVars(ip_);
                    loop_ = (Loop) br_;
                    break;
                }
                if (StringList.quickEq(label, ((BreakableBlock) br_).getRealLabel())){
                    br_.removeLocalVars(ip_);
                    loop_ = (Loop) br_;
                    break;
                }
            }
            bl_.getCurrentVisitedBlock().removeAllVars(ip_);
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this)) {
                return;
            }
        }
        ip_.getReadWrite().setBlock((Block) loop_);
        loop_.processLastElementLoop(_conf);
    }

}
