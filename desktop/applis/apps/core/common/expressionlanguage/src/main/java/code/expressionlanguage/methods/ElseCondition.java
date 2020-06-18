package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecElseCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class ElseCondition extends BracedBlock implements BlockCondition, BuildableElMethod {

    public ElseCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public String getRealLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabel();
    }

    @Override
    public int getRealLabelOffset() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabelOffset();
    }


    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecElseCondition exec_ = new ExecElseCondition(getOffset());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_an.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _an.getKeyWords().getKeyWordElse(),
                        StringList.join(
                                new StringList(
                                        _an.getKeyWords().getKeyWordIf(),
                                        _an.getKeyWords().getKeyWordElseif()
                                ),
                                "|"));
                //key word len
                _an.addError(un_);
            }
        }
    }


    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                break;
            }
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        group_.add(p_);
        boolean canCmpNormally_ = false;
        for (Block b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
        }
    }
}
