package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private String label;
    public ExecContinueBlock(OffsetsBlock _offset, String _label) {
        super(_offset);
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ExecBlock loopBlock_;
        ExecLoop loop_;
        LoopBlockStack lSt_;
        while (true) {
            AbstractStask bl_ = ip_.getLastStack();
            if (bl_ instanceof LoopBlockStack) {
                ExecBracedBlock br_ = bl_.getBlock();
                if (label.isEmpty()) {
                    lSt_ = (LoopBlockStack) bl_;
                    br_.removeLocalVars(ip_);
                    loop_ = ((LoopBlockStack) bl_).getExecLoop();
                    loopBlock_ = br_;
                    break;
                }
                if (StringList.quickEq(label, bl_.getLabel())){
                    lSt_ = (LoopBlockStack) bl_;
                    br_.removeLocalVars(ip_);
                    loop_ = ((LoopBlockStack) bl_).getExecLoop();
                    loopBlock_ = br_;
                    break;
                }
            }
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.getReadWrite().setBlock(loopBlock_);
        loop_.processLastElementLoop(_conf,lSt_);
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
