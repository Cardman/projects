package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.stacks.*;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class BreakBlock extends AbruptBlock implements CallingFinally {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;

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
        checkBreakable(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkBreakable(_cont);
    }

    private void checkBreakable(ContextEl _cont) {
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
                        labelOffsetRef = ((BreakableBlock) b_).getRealLabelOffset();
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
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedAbrupt(),
                        _cont.getKeyWords().getKeyWordBreak(),
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordSwitch(),
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            } else {
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _cont.getKeyWords().getKeyWordBreak(),
                        label,
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordSwitch(),
                                        _cont.getKeyWords().getKeyWordTry(),
                                        _cont.getKeyWords().getKeyWordCatch(),
                                        _cont.getKeyWords().getKeyWordFinally(),
                                        _cont.getKeyWords().getKeyWordIf(),
                                        _cont.getKeyWords().getKeyWordElseif(),
                                        _cont.getKeyWords().getKeyWordElse(),
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            }
            _cont.addError(un_);
        }
    }

    @Override
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
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
        ReadWrite rw_ = ip_.getReadWrite();
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        RemovableVars stack_;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            stack_ = bl_;
            if (label.isEmpty()) {
                if (bl_ instanceof LoopBlockStack || bl_ instanceof SwitchBlockStack) {
                    break;
                }
            } else {
                BreakableBlock br_ = (BreakableBlock) bl_.getBlock();
                if (StringList.quickEq(label, br_.getRealLabel())){
                    break;
                }
            }
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        Block forLoopLoc_ = stack_.getLastBlock();
        rw_.setBlock(forLoopLoc_);
        if (stack_ instanceof LoopStack) {
            ((LoopStack)stack_).setFinished(true);
        }
    }

    @Override
    public AbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new AbruptCallingFinally(this);
    }
}
