package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendWhileCondition extends RendCondition {

    private final String label;

    public RendWhileCondition(CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_op,_offset);
        label = _label;
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processVisitedLoop(_cont,_stds,c_,this,_ctx,_rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ConditionReturn res_ = keepLoop(_cont, _stds, _ctx, _rendStack);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.getContent().setFinished(res_ == ConditionReturn.NO);
        ip_.addBlock(l_);
        if (l_.getContent().isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        rw_.setRead(getFirstChild());
    }

    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
//        ImportingPage ip_ = _rendStack.getLastPage();
//        RendReadWrite rw_ = ip_.getRendReadWrite();
//        RendBlock forLoopLoc_ = _loopBlock.getCurrentVisitedBlock();
        ConditionReturn keep_ = keepLoop(_conf, _advStandards, _ctx, _rendStack);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        ExecHelperBlocks.afterConditLoop(keep_, _loopBlock.getContent());
//        if (keep_ == ConditionReturn.NO) {
//            _loopBlock.getContent().setFinished(true);
//        } else {
//            rw_.setRead(forLoopLoc_.getFirstChild());
//        }
    }

    public ConditionReturn keepLoop(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        return evaluateCondition(_conf, _stds, _ctx, _rendStackCall);
    }
}
