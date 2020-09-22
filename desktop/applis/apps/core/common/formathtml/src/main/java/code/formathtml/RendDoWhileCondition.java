package code.formathtml;

import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;

public final class RendDoWhileCondition extends RendCondition {
    RendDoWhileCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        ConditionReturn keep_ = evaluateCondition(_cont);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        RendBlock previousSibling_ = getPreviousSibling();
        if (previousSibling_ instanceof RendPossibleEmpty) {
            previousSibling_ = previousSibling_.getPreviousSibling();
        }
        rw_.setRead(previousSibling_);
    }
}
