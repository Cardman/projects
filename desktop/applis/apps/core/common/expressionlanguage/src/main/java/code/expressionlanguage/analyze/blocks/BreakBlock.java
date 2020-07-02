package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBreakBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class BreakBlock extends AbruptBlock {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;
    private StringList errorsRefLabels = new StringList();

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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkBreakable(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecBreakBlock exec_ = new ExecBreakBlock(getOffset(),label,labelOffset,labelOffsetRef);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
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
                                        _cont.getKeyWords().getKeyWordIter(),
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
                                        _cont.getKeyWords().getKeyWordIter(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            }
            _cont.addError(un_);
            if (label.isEmpty()) {
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
            } else {
                errorsRefLabels.add(un_.getBuiltError());
            }
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

    public int getLabelOffsetRef() {
        return labelOffsetRef;
    }

    public StringList getErrorsRefLabels() {
        return errorsRefLabels;
    }
}
