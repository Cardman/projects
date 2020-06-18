package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public final class ExecWhileCondition extends ExecCondition implements ExecLoop {

    private String label;
    private int labelOffset;
    public ExecWhileCondition(OffsetsBlock _offset, String _condition, int _conditionOffset, String _label, int _labelOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset,_condition,_conditionOffset, _opCondition);
        label = _label;
        labelOffset = _labelOffset;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = keepLoop(_conf);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    private ConditionReturn keepLoop(ContextEl _conf) {
        _conf.getLastPage().setGlobalOffset(getOffset().getOffsetTrim());
        _conf.getLastPage().setOffset(0);
        return evaluateCondition(_conf);
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        ConditionReturn res_ = evaluateCondition(_cont);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecBlock(this);
        l_.setCurrentVisitedBlock(this);
        boolean finished_ = res_ == ConditionReturn.NO;
        l_.setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        ExecOperationNode root_ = getOpCondition().last();
        AbstractCoverageResult result_ = _cont.getCoverage().getCovers(this).getVal(root_);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordWhile().length()));
        super.processReport(_cont,_parts);
        refLabel(_parts,label,labelOffset);
    }

}
