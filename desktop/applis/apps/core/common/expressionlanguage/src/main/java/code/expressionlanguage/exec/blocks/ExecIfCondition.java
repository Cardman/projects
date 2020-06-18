package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.StackableBlock;
import code.util.CustList;

public final class ExecIfCondition extends ExecCondition implements StackableBlock {

    private String label;
    private int labelOffset;

    public ExecIfCondition(OffsetsBlock _offset, String _condition, int _conditionOffset, String _label, int _labelOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset,_condition,_conditionOffset, _opCondition);
        label = _label;
        labelOffset = _labelOffset;
    }

    public String getRealLabel() {
        return label;
    }

    public int getRealLabelOffset() {
        return labelOffset;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ConditionReturn assert_ = evaluateCondition(_cont);
        if (assert_ == ConditionReturn.CALL_EX) {
            return;
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.setExecLastBlock(this);
        if_.setLabel(label);
        ExecBlock n_ = getNextSibling();
        while (n_ instanceof ExecElseIfCondition || n_ instanceof ExecElseCondition) {
            if_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(this);
        if_.setCurrentVisitedBlock(this);
        if (assert_ == ConditionReturn.YES) {
            ip_.addBlock(if_);
            if_.setEntered(true);
            rw_.setBlock(getFirstChild());
        } else {
            ip_.addBlock(if_);
            exitStack(_cont);
        }
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
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordIf().length()));
        super.processReport(_cont,_parts);
        refLabel(_parts,label,labelOffset);
    }
    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.getLastBlock() != this) {
            rw_.setBlock(getNextSibling());
        }
    }
}
