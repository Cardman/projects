package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelperBlocks;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.LocalThrowing;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class ExecBlock {

    protected static final String EMPTY_STRING = "";

    private ExecBracedBlock parent;

    private ExecBlock nextSibling;

    private ExecBlock previousSibling;

    private ExecFileBlock file;

    private final int offsetTrim;


    ExecBlock(int _offsetTrim) {
        offsetTrim = _offsetTrim;
    }
    protected final void setParent(ExecBracedBlock _b) {
        parent = _b;
    }

    public final int getOffsetTrim() {
        return offsetTrim;
    }

    public final void processBlockAndRemove(ContextEl _conf, StackCall _stackCall) {
        ExecHelperBlocks.processBlockAndRemove(_conf,this, _stackCall);
    }

    public final void processMemberBlock(AbstractInitPageEl _lastPage) {
        int cur_ = _lastPage.getMember();
        int next_ = cur_ + 1;
        CustList<ExecBlock> visited_ = _lastPage.getVisited();
        if (visited_.isValidIndex(next_)) {
            _lastPage.setMember(next_);
            _lastPage.setBlock(visited_.get(next_));
            return;
        }
//        ExecBlock n_ = getNextSibling();
//        if (n_ != null) {
//            _lastPage.setBlock(n_);
//            return;
//        }
        _lastPage.setNullReadWrite();
    }
    public final void processBlock(ContextEl _conf, StackCall _stackCall) {
        ExecBlock n_ = getNextSibling();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (n_ != null) {
            ip_.setBlock(n_);
            return;
        }
        ExecBracedBlock par_ = getParent();
        if (ip_.hasBlock()) {
            ip_.setBlock(par_);
            AbstractStask lastStack_ = ip_.getLastStack();
            if (lastStack_ instanceof LoopBlockStack) {
                if (par_ instanceof ExecDoBlock) {
                    par_.removeLocalVars(ip_);
                    ((ExecDoBlock)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForEachArray) {
                    par_.removeLocalVars(ip_);
                    ((ExecForEachArray)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForEachRefArray) {
                    par_.removeLocalVars(ip_);
                    ((ExecForEachRefArray)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForEachIterable) {
                    par_.removeLocalVars(ip_);
                    ((ExecForEachIterable)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    ((ExecForIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForMutableIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    ((ExecForMutableIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecForEachTable) {
                    par_.removeLocalVars(ip_);
                    ((ExecForEachTable)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
                if (par_ instanceof ExecWhileCondition) {
                    par_.removeLocalVars(ip_);
                    ((ExecWhileCondition)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_, _stackCall);
                }
            }
            if (lastStack_ instanceof IfBlockStack) {
                if (par_ instanceof ExecIfCondition) {
                    par_.removeAllVars(ip_);
                    if (((IfBlockStack)lastStack_).getLastBlock() != par_) {
                        ip_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseIfCondition) {
                    par_.removeAllVars(ip_);
                    if (((IfBlockStack)lastStack_).getLastBlock() != par_) {
                        ip_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseCondition) {
                    par_.removeAllVars(ip_);
                }
                if (par_ instanceof ExecUnclassedBracedBlock) {
                    par_.removeAllVars(ip_);
                }
            }
            if (lastStack_ instanceof TryBlockStack) {
                if (par_ instanceof ExecTryEval) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_.getNextSibling());
                    ip_.setLastTry((TryBlockStack)lastStack_);
                }
                if (par_ instanceof ExecAbstractCatchEval) {
                    par_.removeAllVars(ip_);
                    ip_.setLastTry((TryBlockStack)lastStack_);
                }
                if (par_ instanceof ExecFinallyEval) {
                    par_.removeAllVars(ip_);
                    AbruptCallingFinally call_ = ((TryBlockStack)lastStack_).getCalling();
                    if (call_ != null) {
                        MethodCallingFinally callingFinally_ = call_.getCallingFinally();
                        if (callingFinally_ != null) {
                            callingFinally_.removeBlockFinally(_conf, _stackCall);
                        } else {
                            Struct exception_ = ((TryBlockStack)lastStack_).getException();
                            _stackCall.setCallingState(new CustomFoundExc(exception_));
                            LocalThrowing.removeBlockFinally(_conf, exception_, _stackCall);
                        }
                    }
                }
            }
            if (lastStack_ instanceof SwitchBlockStack) {
                if (par_ instanceof ExecDefaultCondition) {
                    par_.removeAllVars(ip_);
                    SwitchBlockStack if_ = (SwitchBlockStack) lastStack_;
                    if (if_.getExecLastVisitedBlock() == par_) {
                        ip_.setBlock(if_.getBlock());
                    } else {
                        ip_.setBlock(par_.getNextSibling());
                    }
                }
                if (par_ instanceof ExecAbstractCaseCondition) {
                    par_.removeAllVars(ip_);
                    SwitchBlockStack if_ = (SwitchBlockStack) lastStack_;
                    if (if_.getExecLastVisitedBlock() == par_) {
                        ip_.setBlock(if_.getBlock());
                    } else {
                        ip_.setBlock(par_.getNextSibling());
                    }
                }
            }
            return;
        }
        ip_.setNullReadWrite();
    }

    public final ExecFileBlock getFile() {
        return file;
    }

    public void setFile(ExecFileBlock _file) {
        file = _file;
    }


    public final ExecBlock getPreviousSibling() {
        return previousSibling;
    }

    public final ExecBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(ExecBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(ExecBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final ExecBracedBlock getParent() {
        return parent;
    }

}
