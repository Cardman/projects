package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class DbgStackStopper implements AbsStackStopper {
    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.DEBUG;
    }

    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return _page.sizeEl() == 0;
    }
    @Override
    public boolean stopAt(AbstractPageEl _page, int _size) {
        return _size < _page.sizeEl();
    }

    @Override
    public boolean stopAt(ContextEl _context, StackCall _stack, int _size) {
        return _size < _stack.getLastPage().sizeEl() || _context.callsOrException(_stack);
    }

    @Override
    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (checkBreakPoint(_stackCall,p_)) {
            return stopAtCheckedBp(_context, _stackCall, p_);
        }
        return false;
    }

    @Override
    public boolean isCheckingException(StackCall _stackCall) {
        return _stackCall.isCheckingException();
    }

    @Override
    public boolean hasFoundException(StackCall _stackCall) {
        return _stackCall.trueException() != null;
    }

    @Override
    public ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl) {
        return ExecHelperBlocks.checkBpWithoutClear(_stack, _index, _ip, _list, _bl);
    }

    private boolean stopAtCheckedBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.isVisited()) {
            return false;
        }
        _stackCall.setVisited(true);
        if (stopStep(_context, _stackCall, _p)) {
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            return true;
        }
        if (_context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().get()) {
            _context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().set(false);
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            return true;
        }
        if (_stackCall.isMute()) {
            return false;
        }
        if (stopExc(_context, _stackCall, _p)) {
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            return true;
        }
        for (int i : list(_stackCall, _p)) {
            BreakPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNull(_p.getFile(), i);
            if (stopCurrentBp(_context, _stackCall,_p,bp_)) {
                _stackCall.setGlobalOffset(i);
                return true;
            }
        }
        return false;
    }

    private boolean stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        return _stackCall.getStep() == StepDbgActionEnum.RETURN_METHOD && _p.getReadWrite() == null || _stackCall.getStep() == StepDbgActionEnum.NEXT_IN_METHOD && _stackCall.getPreviousNbPages() >= _stackCall.nbPages() || _stackCall.getStep() == StepDbgActionEnum.NEXT_INSTRUCTION || stopTmp(_context, _stackCall, _p);
    }

    private boolean stopTmp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getStep() == StepDbgActionEnum.CURSOR) {
            for (int i : list(_stackCall,_p)) {
                if (_context.getClasses().getDebugMapping().getBreakPointsBlock().isTmp(_p.getFile(), i)) {
                    _stackCall.setGlobalOffset(_p.getGlobalOffset());
                    return true;
                }
            }
        }
        return false;
    }
    private boolean stopExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        AbstractStask stLast_ = _p.tryGetLastStack();
        ExecBlock bl_ = _p.getBlock();
        if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            Struct e_ = ((TryBlockStack) stLast_).getException();
            ConditionReturn st_ = _context.getClasses().getDebugMapping().getExceptions().getVal(e_.getClassName(_context));
            if ((st_ == ConditionReturn.YES || st_ == ConditionReturn.CALL_EX) && ExecHelperBlocks.firstMatch(_context, _stackCall, ((ExecAbstractCatchEval)bl_).getContent(), e_, ((ExecAbstractCatchEval)bl_).isCatchAll()) && _p.sizeEl() < 2) {
                return true;
            }
        }
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            Struct e_ = exc_.getStruct();
            ConditionReturn st_ = _context.getClasses().getDebugMapping().getExceptions().getVal(e_.getClassName(_context));
            return st_ == ConditionReturn.NO || st_ == ConditionReturn.CALL_EX;
        }
        return false;
    }
    private boolean stopCurrentBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPoint _bp) {
        if (!(_bp.isEnabled() && (!_bp.isEnabledChgtType() || _bp.isInstanceType() && _p instanceof AbstractCallingInstancingPageEl || _bp.isStaticType() && _p instanceof StaticInitPageEl))) {
            return false;
        }
        BreakPointCondition condition_ = stopCurrentBpCondition(_bp);
        if (okStack(_context,_stackCall,condition_) && condition(_context,_stackCall,_p,condition_)) {
            if (!condition_.getEnabled().get()) {
                return false;
            }
            if (countMatch(condition_)) {
                if (condition_.getDisableWhenHit().get()) {
                    condition_.getEnabled().set(false);
                }
                return true;
            }
            return false;
        }
        return false;
    }
    private static boolean countMatch(BreakPointCondition _cond) {
        int c_ = _cond.getCountModulo();
        if (c_ <= 0) {
            return true;
        }
        int p_ = _cond.getCount();
        _cond.setCount(p_ + 1);
        return NumberUtil.mod(_cond.getCount(),c_) == 0;
    }
    private boolean okStack(ContextEl _context, StackCall _stackCall, BreakPointCondition _bp) {
        for (AbsCallContraints e: _bp.getExclude().elts()) {
            if (!excOk(_context,_stackCall,e)) {
                return false;
            }
        }
        for (AbsCallContraints e: _bp.getInclude().elts()) {
            if (!incOk(_context,_stackCall,e)) {
                return false;
            }
        }
        return true;
    }

    private boolean excOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return false;
            }
        }
        return true;
    }
    private boolean incOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return true;
            }
        }
        return false;
    }
    private boolean condition(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPointCondition _bp) {
        if (_bp.getResult() == null) {
            return true;
        }
        StackCallReturnValue result_ = _bp.getResult().eval(_context, _p);
        if (result_.getStack().getCallingState() != null) {
            _stackCall.setCallingStateSub(result_.getStack().getCallingState());
            return true;
        }
        return BooleanStruct.isTrue(ArgumentListCall.toStr(result_.getRetValue().getValue()));
    }
    private BreakPointCondition stopCurrentBpCondition(BreakPoint _bp) {
        if (!_bp.isEnabledChgtType()) {
            return _bp.getResultStd();
        }
        if (_bp.isInstanceType()) {
            return _bp.getResultInstance();
        }
        return _bp.getResultStatic();
    }

    private int[] list(StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.isCheckingException()) {
            return NumberUtil.wrapIntArray();
        }
        ExecBlock bl_ = _p.getBlock();
        AbstractStask st_ = _p.tryGetLastStack();
        if (st_ instanceof LoopBlockStack && bl_ instanceof ExecAbstractForEachLoop && !(bl_ instanceof ExecForEachIterable) && ((ExecAbstractForEachLoop) bl_).getVariable().getOffset() == _p.getGlobalOffset()) {
            if (!((LoopBlockStack) st_).getContent().hasNext()) {
                return NumberUtil.wrapIntArray(((ExecAbstractForEachLoop) bl_).getSeparator());
            }
            return NumberUtil.wrapIntArray(_p.getGlobalOffset(), ((ExecAbstractForEachLoop) bl_).getSeparator());
        }
        if (_p.getLastLoopIfPossible(bl_) == null){
            if (bl_ instanceof ExecForEachIterable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachIterable) bl_).getIteratorOffset());
            }
            if (bl_ instanceof ExecForEachTable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachTable) bl_).getOffsets().getIteratorOffset());
            }
        }
        return NumberUtil.wrapIntArray(_p.getGlobalOffset());
    }

    private boolean checkBreakPoint(StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.isCheckingException()) {
            return true;
        }
        if (_p.getReadWrite() == null) {
            return _stackCall.getStep() == StepDbgActionEnum.RETURN_METHOD && _stackCall.getPreviousNbPages() >= _stackCall.nbPages();
        }
        ExecBlock bl_ = _p.getBlock();
        if (bl_ instanceof ExecDeclareVariable || _p.isEmptyEl()) {
            return false;
        }
        if (bl_ instanceof ExecLine || bl_ instanceof ExecAbstractReturnMethod || bl_ instanceof MethodCallingFinally || bl_ instanceof ExecThrowing || bl_ instanceof ExecDoWhileCondition) {
            return true;
        }
        AbstractStask st_ = _p.tryGetLastStack();
        if (st_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            return true;
        }
        if (st_ instanceof EnteredStack) {
            return !((EnteredStack) st_).isEntered();
        }
        if (st_ instanceof LoopBlockStack) {
            return !((LoopBlockStack) st_).getContent().isFinished();
        }
        if (st_ instanceof SwitchBlockStack) {
            return !((SwitchBlockStack) st_).isEntered();
        }
        return true;
    }
}
