package code.formathtml;

import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;

public final class RendWhileCondition extends RendCondition implements RendLoop {

    private String label;
    private int labelOffset;
    RendWhileCondition(OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }


    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont);
            return;
        }
        ConditionReturn res_ = evaluateCondition(_cont);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setFinished(res_ == ConditionReturn.NO);
        ip_.addBlock(l_);
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _conf) {
        processLastElementLoop(_conf);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        ConditionReturn keep_ = keepLoop(_conf);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        } else {
            rw_.setRead(forLoopLoc_.getFirstChild());
        }
    }

    public ConditionReturn keepLoop(Configuration _conf) {
        return evaluateCondition(_conf);
    }
}
