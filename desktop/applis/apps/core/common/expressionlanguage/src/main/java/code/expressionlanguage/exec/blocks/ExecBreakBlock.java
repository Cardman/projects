package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.files.OffsetsBlock;

public final class ExecBreakBlock extends ExecLeaf implements MethodCallingFinally {

    private String label;
    public ExecBreakBlock(OffsetsBlock _offset, String _label) {
        super(_offset);
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        while (ExecTemplates.hasBlockBreak(ip_,label)) {
            AbstractStask bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                break;
            }
        }

    }

    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

}
