package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.util.CustList;

public final class ExecIfCondition extends ExecCondition implements StackableBlock {

    private String label;

    public ExecIfCondition(int _conditionOffset, String _label, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_conditionOffset, _opCondition, _offsetTrim);
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
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
        while (isNextIfParts(n_)) {
            if_.setExecLastBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(this);
        if_.setCurrentVisitedBlock(this);
        if (assert_ == ConditionReturn.YES) {
            ip_.addBlock(if_);
            if_.setEntered(true);
            ip_.setBlock(getFirstChild());
        } else {
            ip_.addBlock(if_);
            if (if_.getLastBlock() != this) {
                ip_.setBlock(getNextSibling());
                ip_.setLastIf(if_);
            }
        }
    }

}
