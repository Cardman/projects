package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecLoop;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.*;
import code.util.core.StringUtil;

public final class ExecHelperBlocks {
    private ExecHelperBlocks() {
    }

    public static boolean hasBlockBreak(AbstractPageEl _ip, String _label) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return false;
        }
        AbstractStask bl_ = _ip.getLastStack();
        if (_label.isEmpty()) {
            if (bl_ instanceof LoopBlockStack) {
                ExecBlock forLoopLoc_ = bl_.getCurrentVisitedBlock();
                _ip.setBlock(forLoopLoc_);
                _ip.setLastLoop((LoopBlockStack) bl_);
                ((LoopBlockStack)bl_).setFinished(true);
                return false;
            }
            if (bl_ instanceof SwitchBlockStack) {
                ExecBlock forLoopLoc_ = ((SwitchBlockStack)bl_).getBlock();
                _ip.setBlock(forLoopLoc_);
                return false;
            }
        } else {
            if (StringUtil.quickEq(_label, bl_.getLabel())){
                if (bl_ instanceof LoopBlockStack) {
                    ExecBlock forLoopLoc_ = bl_.getCurrentVisitedBlock();
                    _ip.setBlock(forLoopLoc_);
                    _ip.setLastLoop((LoopBlockStack) bl_);
                    ((LoopBlockStack)bl_).setFinished(true);
                }
                if (bl_ instanceof IfBlockStack) {
                    ExecBlock forLoopLoc_ = ((IfBlockStack)bl_).getLastBlock();
                    _ip.setBlock(forLoopLoc_);
                    _ip.setLastIf((IfBlockStack) bl_);
                }
                if (bl_ instanceof TryBlockStack) {
                    ExecBlock forLoopLoc_ = ((TryBlockStack)bl_).getLastBlock();
                    _ip.setBlock(forLoopLoc_);
                    _ip.setLastTry((TryBlockStack) bl_);
                }
                if (bl_ instanceof SwitchBlockStack) {
                    ExecBlock forLoopLoc_ = ((SwitchBlockStack)bl_).getBlock();
                    _ip.setBlock(forLoopLoc_);
                }
                return false;
            }
        }
        return true;
    }

    public static boolean hasBlockContinue(ContextEl _conf, AbstractPageEl _ip, String _label, StackCall _stackCall) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return false;
        }
        AbstractStask bl_ = _ip.getLastStack();
        if (bl_ instanceof LoopBlockStack) {
            ExecBracedBlock br_ = bl_.getCurrentVisitedBlock();
            if (_label.isEmpty()) {
                LoopBlockStack lSt_;
                lSt_ = (LoopBlockStack) bl_;
                br_.removeLocalVars(_ip);
                ExecLoop loop_;
                loop_ = ((LoopBlockStack) bl_).getExecLoop();
                _ip.setBlock(br_);
                loop_.processLastElementLoop(_conf,lSt_, _stackCall);
                return false;
            }
            if (StringUtil.quickEq(_label, bl_.getLabel())){
                LoopBlockStack lSt_;
                lSt_ = (LoopBlockStack) bl_;
                br_.removeLocalVars(_ip);
                ExecLoop loop_;
                loop_ = ((LoopBlockStack) bl_).getExecLoop();
                _ip.setBlock(br_);
                loop_.processLastElementLoop(_conf,lSt_, _stackCall);
                return false;
            }
        }
        return true;
    }

    public static void setVisited(AbstractPageEl _ip, ExecBracedBlock _block) {
        if (!_ip.hasBlock()) {
            _ip.setNullReadWrite();
            return;
        }
        _ip.getLastStack().setCurrentVisitedBlock(_block);
    }

    public static void processFinally(ContextEl _cont, ExecBracedBlock _block, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        TryBlockStack ts_ = ip_.getLastTry();
        if (ts_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        ts_.setCurrentVisitedBlock(_block);
        if (ts_.isVisitedFinally()) {
            _block.processBlockAndRemove(_cont, _stackCall);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.setBlock(_block.getFirstChild());
    }

    public static void processElseIf(ContextEl _cont, ExecCondition _cond, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        IfBlockStack if_ = ip_.getLastIf();
        if (if_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!if_.isEntered()) {
            ConditionReturn assert_ = _cond.evaluateCondition(_cont, _stackCall);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                if_.setEntered(true);
                ip_.setBlock(_cond.getFirstChild());
                return;
            }
        }
        if (ExecBracedBlock.isNextIfParts(_cond.getNextSibling())) {
            ip_.setBlock(_cond.getNextSibling());
            return;
        }
        _cond.processBlockAndRemove(_cont, _stackCall);
    }

    public static void processElse(ContextEl _cont, ExecBracedBlock _cond, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        IfBlockStack if_ = ip_.getLastIf();
        if (if_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.setBlock(_cond.getFirstChild());
            return;
        }
        _cond.processBlockAndRemove(_cont, _stackCall);
    }

    public static void processDo(ContextEl _cont, ExecCondition _cond, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        LoopBlockStack l_ = ip_.getLastLoop();
        if (l_ == null) {
            ip_.setNullReadWrite();
            return;
        }
        l_.setEvaluatingKeepLoop(true);
        ConditionReturn keep_ = _cond.evaluateCondition(_cont, _stackCall);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
        ip_.setBlock(_cond.getPreviousSibling());
    }

    public static void processBlockAndRemove(ContextEl _context, ExecBlock _bl, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (!ip_.hasBlock()) {
            ip_.setNullReadWrite();
            return;
        }
        ip_.removeLastBlock();
        _bl.processBlock(_context, _stackCall);
    }
}
