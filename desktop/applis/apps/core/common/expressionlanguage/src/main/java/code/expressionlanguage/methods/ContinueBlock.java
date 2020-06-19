package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecContinueBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.stacks.RemovableVars;
import code.expressionlanguage.structs.Struct;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;

public final class ContinueBlock extends AbruptBlock {

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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkLoop(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecContinueBlock exec_ = new ExecContinueBlock(getOffset(),label,labelOffset,labelOffsetRef);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
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
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            if (label.isEmpty()) {
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedAbrupt(),
                        _cont.getKeyWords().getKeyWordContinue(),
                        StringList.join(
                                new StringList(
                                        _cont.getKeyWords().getKeyWordFor(),
                                        _cont.getKeyWords().getKeyWordForeach(),
                                        _cont.getKeyWords().getKeyWordDo(),
                                        _cont.getKeyWords().getKeyWordWhile()
                                ),
                                "|"));
            } else {
                //key word len
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _cont.getKeyWords().getKeyWordContinue(),
                        label,
                        StringList.join(
                                new StringList(
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

    public int getLabelOffsetRef() {
        return labelOffsetRef;
    }
}
