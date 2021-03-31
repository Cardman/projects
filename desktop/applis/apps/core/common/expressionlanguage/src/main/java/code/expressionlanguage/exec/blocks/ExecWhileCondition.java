package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.util.CustList;

public final class ExecWhileCondition extends ExecCondition implements ExecLoop {

    private final String label;
    public ExecWhileCondition(int _conditionOffset, String _label, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_conditionOffset, _opCondition, _offsetTrim);
        label = _label;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        _l.setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = evaluateCondition(_conf, _stack);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            _l.setFinished(true);
        }
        _l.setEvaluatingKeepLoop(false);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont, _stack);
            return;
        }
        ConditionReturn res_ = evaluateCondition(_cont, _stack);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        boolean finished_ = res_ == ConditionReturn.NO;
        l_.setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont, _stack);
            return;
        }
        ip_.setBlock(getFirstChild());
    }


}
