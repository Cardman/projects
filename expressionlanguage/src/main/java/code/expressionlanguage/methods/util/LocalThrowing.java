package code.expressionlanguage.methods.util;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AbstractCatchEval;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.CatchEval;
import code.expressionlanguage.methods.FinallyEval;
import code.expressionlanguage.methods.NullCatchEval;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;

public final class LocalThrowing implements CallingFinally {

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        LgNames lgNames_ = _conf.getStandards();
        AbstractCatchEval catchElt_ = null;
        while (!_conf.isEmptyPages()) {
            Struct custCause_ = _conf.getException();
            AbstractPageEl bkIp_ = _conf.getLastPage();
            while (!bkIp_.noBlock()) {
                RemovableVars bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryBlockStack)) {
                    bl_.removeVarAndLoop(bkIp_);
                    continue;
                }
                TryBlockStack try_ = (TryBlockStack)bl_;
                boolean addFinallyClause_ = true;
                if (!(try_.getCatchBlocks().last() instanceof FinallyEval)) {
                    addFinallyClause_ = false;
                }
                if (try_.getVisitedCatch() >= CustList.FIRST_INDEX) {
                    if (!(try_.getCurrentCatchBlock() instanceof FinallyEval)) {
                        if (addFinallyClause_) {
                            try_.setException(custCause_);
                            try_.setCalling(this);
                            _conf.setException(null);
                            bkIp_.clearCurrentEls();
                            bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                            return;
                        }
                    }
                    bkIp_.removeLastBlock();
                    continue;
                }
                //process try block
                int i_ = 0;
                for (Block e: try_.getCatchBlocks()) {
                    if (e instanceof FinallyEval) {
                        break;
                    }
                    if (e instanceof CatchEval) {
                        CatchEval ca_ = (CatchEval) e;
                        String name_ = ca_.getClassName();
                        Mapping mapping_ = new Mapping();
                        if (custCause_.isNull()) {
                            i_++;
                            continue;
                        }
                        String excepClass_ = lgNames_.getStructClassName(custCause_, _conf);
                        mapping_.setArg(excepClass_);
                        name_ = bkIp_.formatVarType(name_, _conf);
                        mapping_.setParam(name_);
                        if (Templates.isCorrect(mapping_, _conf)) {
                            catchElt_ = ca_;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    } else {
                        NullCatchEval ca_ = (NullCatchEval) e;
                        if (custCause_.isNull()) {
                            catchElt_ = ca_;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    Block catchElement_ = catchElt_;
                    try_.setCalling(null);
                    _conf.setException(null);
                    bkIp_.clearCurrentEls();
                    Block childCatch_ = catchElement_.getFirstChild();
                    if (childCatch_ != null) {
                        if (catchElement_ instanceof CatchEval) {
                            CatchEval c_ = (CatchEval) catchElement_;
                            String var_ = c_.getVariableName();
                            LocalVariable lv_ = new LocalVariable();
                            lv_.setStruct(custCause_);
                            lv_.setClassName(c_.getClassName());
                            bkIp_.getCatchVars().put(var_, lv_);
                        }
                        bkIp_.getReadWrite().setBlock(childCatch_);
                        return;
                    }
                    bkIp_.getReadWrite().setBlock(catchElement_);
                    return;
                }
                if (addFinallyClause_) {
                    try_.setCalling(this);
                    _conf.setException(null);
                    try_.setException(custCause_);
                    bkIp_.clearCurrentEls();
                    bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                    return;
                }
                bkIp_.removeLastBlock();
            }
            _conf.getClasses().getLocks().processErrorClass(_conf, custCause_);
            _conf.removeLastPage();
        }
    }

}
