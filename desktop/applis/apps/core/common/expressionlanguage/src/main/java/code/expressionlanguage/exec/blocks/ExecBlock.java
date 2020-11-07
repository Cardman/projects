package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.structs.Struct;

public abstract class ExecBlock {

    protected static final String EMPTY_STRING = "";

    private ExecBracedBlock parent;

    private ExecBlock nextSibling;

    private ExecBlock previousSibling;

    private ExecFileBlock file;

    private int offsetTrim;


    ExecBlock(int _offsetTrim) {
        offsetTrim = _offsetTrim;
    }
    protected final void setParent(ExecBracedBlock _b) {
        parent = _b;
    }

    public final int getOffsetTrim() {
        return offsetTrim;
    }

    public final void processBlockAndRemove(ContextEl _conf) {
        ExecTemplates.processBlockAndRemove(_conf,this);
    }

    public final void processBlock(ContextEl _conf) {
        ExecBlock n_ = getNextSibling();
        AbstractPageEl ip_ = _conf.getLastPage();
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
                    ip_.setBlock(par_);
                    ((ExecDoBlock)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForEachArray) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecForEachArray)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForEachIterable) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecForEachIterable)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecForIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForMutableIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecForMutableIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForEachTable) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecForEachTable)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecWhileCondition) {
                    par_.removeLocalVars(ip_);
                    ip_.setBlock(par_);
                    ((ExecWhileCondition)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
            }
            if (lastStack_ instanceof IfBlockStack) {
                if (par_ instanceof ExecIfCondition) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_);
                    if (lastStack_.getLastBlock() != par_) {
                        ip_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseIfCondition) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_);
                    if (lastStack_.getLastBlock() != par_) {
                        ip_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseCondition) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_);
                }
                if (par_ instanceof ExecUnclassedBracedBlock) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_);
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
                    ip_.setBlock(par_);
                    ip_.setLastTry((TryBlockStack)lastStack_);
                }
                if (par_ instanceof ExecFinallyEval) {
                    par_.removeAllVars(ip_);
                    ip_.setBlock(par_);
                    AbruptCallingFinally call_ = ((TryBlockStack)lastStack_).getCalling();
                    if (call_ != null) {
                        MethodCallingFinally callingFinally_ = call_.getCallingFinally();
                        if (callingFinally_ != null) {
                            callingFinally_.removeBlockFinally(_conf);
                        } else {
                            Struct exception_ = ((TryBlockStack)lastStack_).getException();
                            _conf.setCallingState(new CustomFoundExc(exception_));
                            ExecutingUtil.processGeneException(_conf);
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
    public abstract ExecBlock getFirstChild();

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
