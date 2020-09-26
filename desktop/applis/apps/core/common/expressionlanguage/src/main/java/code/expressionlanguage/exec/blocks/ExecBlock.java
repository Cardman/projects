package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

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
    public static CustList<ExecBlock> getDirectChildrenNotType(ExecBlock _element) {
        CustList<ExecBlock> list_ = new CustList<ExecBlock>();
        for (ExecBlock e: getDirectChildren(_element)) {
            if (e instanceof ExecRootBlock) {
                continue;
            }
            list_.add(e);
        }
        return list_;
    }
    public static CustList<ExecBlock> getDirectChildren(ExecBlock _element) {
        CustList<ExecBlock> list_ = new CustList<ExecBlock>();
        if (_element == null) {
            return list_;
        }
        ExecBlock elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }
    public final void processBlock(ContextEl _conf) {
        ExecBlock n_ = getNextSibling();
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (n_ != null) {
            rw_.setBlock(n_);
            return;
        }
        ExecBracedBlock par_ = getParent();
        if (ip_.hasBlock()) {
            rw_.setBlock(par_);
            AbstractStask lastStack_ = ip_.getLastStack();
            if (lastStack_ instanceof LoopBlockStack) {
                if (par_ instanceof ExecDoBlock) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecDoBlock)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForEachLoop) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecForEachLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecForIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForMutableIterativeLoop) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecForMutableIterativeLoop)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecForEachTable) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecForEachTable)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
                if (par_ instanceof ExecWhileCondition) {
                    par_.removeLocalVars(ip_);
                    rw_.setBlock(par_);
                    ((ExecWhileCondition)par_).processLastElementLoop(_conf, (LoopBlockStack) lastStack_);
                }
            }
            if (lastStack_ instanceof IfBlockStack) {
                if (par_ instanceof ExecIfCondition) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                    if (lastStack_.getLastBlock() != par_) {
                        rw_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseIfCondition) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                    if (lastStack_.getLastBlock() != par_) {
                        rw_.setBlock(par_.getNextSibling());
                        ip_.setLastIf((IfBlockStack) lastStack_);
                    }
                }
                if (par_ instanceof ExecElseCondition) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                }
                if (par_ instanceof ExecUnclassedBracedBlock) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                }
            }
            if (lastStack_ instanceof TryBlockStack) {
                if (par_ instanceof ExecTryEval) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_.getNextSibling());
                    ip_.setLastTry((TryBlockStack)lastStack_);
                }
                if (par_ instanceof ExecAbstractCatchEval) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                    ip_.setLastTry((TryBlockStack)lastStack_);
                }
                if (par_ instanceof ExecFinallyEval) {
                    par_.removeAllVars(ip_);
                    rw_.setBlock(par_);
                    AbruptCallingFinally call_ = ((TryBlockStack)lastStack_).getCalling();
                    if (call_ != null) {
                        Object callingFinally_ = call_.getCallingFinally();
                        if (callingFinally_ instanceof MethodCallingFinally) {
                            ((MethodCallingFinally)callingFinally_).removeBlockFinally(_conf);
                        } else {
                            Struct exception_ = ((TryBlockStack)lastStack_).getException();
                            _conf.setException(exception_);
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
                        rw_.setBlock(if_.getBlock());
                    } else {
                        rw_.setBlock(par_.getNextSibling());
                    }
                }
                if (par_ instanceof ExecAbstractCaseCondition) {
                    par_.removeAllVars(ip_);
                    SwitchBlockStack if_ = (SwitchBlockStack) lastStack_;
                    if (if_.getExecLastVisitedBlock() == par_) {
                        rw_.setBlock(if_.getBlock());
                    } else {
                        rw_.setBlock(par_.getNextSibling());
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

    public static CustList<ExecNamedFunctionBlock> getMethodBodiesById(ExecRootBlock _genericClassName, MethodId _id) {
        return filter(getMethodBodies(_genericClassName),_id);
    }

    private static CustList<ExecNamedFunctionBlock> getMethodBodies(ExecRootBlock _genericClassName) {
        return getMethodExecBlocks(_genericClassName);
    }


    private static CustList<ExecNamedFunctionBlock> getMethodExecBlocks(ExecRootBlock _element) {
        CustList<ExecNamedFunctionBlock> methods_ = new CustList<ExecNamedFunctionBlock>();
        for (ExecBlock b: getDirectChildren(_element)) {
            if (b instanceof ExecOverridableBlock) {
                methods_.add((ExecNamedFunctionBlock) b);
            }
        }
        return methods_;
    }

    private static CustList<ExecNamedFunctionBlock> filter(CustList<ExecNamedFunctionBlock> _methods,MethodId _id) {
        CustList<ExecNamedFunctionBlock> methods_ = new CustList<ExecNamedFunctionBlock>();
        for (ExecNamedFunctionBlock m: _methods) {
            if (((GeneMethod)m).getId().eq(_id)) {
                methods_.add(m);
            }
        }
        return methods_;
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
