package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecBreakBlock extends ExecLeaf implements CallingFinally {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;
    public ExecBreakBlock(OffsetsBlock _offset, String _label, int _labelOffset, int _labelOffsetRef) {
        super(_offset);
        label = _label;
        labelOffset = _labelOffset;
        labelOffsetRef = _labelOffsetRef;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        RemovableVars stack_;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
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
        if (stack_ instanceof LoopStack) {
            ((LoopStack)stack_).setFinished(true);
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
