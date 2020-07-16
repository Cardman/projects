package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecBreakBlock extends ExecLeaf implements MethodCallingFinally {

    private String label;
    public ExecBreakBlock(OffsetsBlock _offset, String _label) {
        super(_offset);
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        AbstractStask stack_;
        while (true) {
            AbstractStask bl_ = ip_.getLastStack();
            stack_ = bl_;
            if (label.isEmpty()) {
                if (bl_ instanceof LoopBlockStack || bl_ instanceof SwitchBlockStack) {
                    break;
                }
            } else {
                if (StringList.quickEq(label, bl_.getLabel())){
                    break;
                }
            }
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ExecBlock forLoopLoc_ = stack_.getLastBlock();
        rw_.setBlock(forLoopLoc_);
        if (stack_ instanceof LoopBlockStack) {
            ip_.setLastLoop((LoopBlockStack) stack_);
        }
        if (stack_ instanceof IfBlockStack) {
            ip_.setLastIf((IfBlockStack) stack_);
        }
        if (stack_ instanceof TryBlockStack) {
            ip_.setLastTry((TryBlockStack) stack_);
        }
        if (stack_ instanceof LoopBlockStack) {
            ((LoopBlockStack)stack_).setFinished(true);
        }
    }

    @Override
    public AbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new AbruptCallingFinally(this);
    }

    @Override
    public void processEl(ContextEl _cont) {
        removeBlockFinally(_cont);
    }

    public String getLabel() {
        return label;
    }
}
