package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.stacks.RemovableVars;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecContinueBlock extends ExecLeaf implements CallingFinally {

    private String label;
    private int labelOffset;
    private int labelOffsetRef;
    public ExecContinueBlock(OffsetsBlock _offset, String _label, int _labelOffset, int _labelOffsetRef) {
        super(_offset);
        label = _label;
        labelOffset = _labelOffset;
        labelOffsetRef = _labelOffsetRef;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ExecLoop loop_;
        while (true) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_ instanceof LoopBlockStack) {
                ExecBracedBlock br_ = bl_.getBlock();
                if (label.isEmpty()) {
                    br_.removeLocalVars(ip_);
                    loop_ = (ExecLoop) br_;
                    break;
                }
                if (StringList.quickEq(label, bl_.getLabel())){
                    br_.removeLocalVars(ip_);
                    loop_ = (ExecLoop) br_;
                    break;
                }
            }
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.getReadWrite().setBlock((ExecBlock) loop_);
        loop_.processLastElementLoop(_conf);
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
