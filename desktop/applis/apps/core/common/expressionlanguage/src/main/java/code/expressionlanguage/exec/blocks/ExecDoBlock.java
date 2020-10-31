package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.LoopBlockStack;

public final class ExecDoBlock extends ExecBracedBlock implements ExecLoop {

    private String label;
    public ExecDoBlock(String _label, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setBlock(getNextSibling());
        ip_.setLastLoop(_l);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,getNextSibling(),_cont);
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        ip_.setBlock(getFirstChild());
    }
}
