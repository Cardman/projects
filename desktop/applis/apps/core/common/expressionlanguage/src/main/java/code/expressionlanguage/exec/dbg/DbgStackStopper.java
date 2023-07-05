package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.FieldWrapper;
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
    public boolean stopAt(AbstractPageEl _page, StackCall _stack, int _size) {
        return _size < _page.sizeEl() || !_stack.getCheckingExp().getClassName().isEmpty();
    }

    @Override
    public boolean stopAt(ContextEl _context, StackCall _stack, int _size) {
        return stopAt(_stack.getLastPage(),_stack,_size) || _context.callsOrException(_stack);
    }

    @Override
    public boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        if (_stackCall.isVisitedExp()) {
            _stackCall.setCheckingExp(new ClassField("",""));
            return false;
        }
        _stackCall.setVisitedExp(true);
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompound(_el, (ExecAbstractAffectOperation)_o, _stackCall);
        }
        if (_o instanceof ExecSettableFieldInstOperation || (_o instanceof ExecSettableFieldStatOperation && _context.getExiting().state(_stackCall, ((ExecSettableFieldStatOperation) _o).getRootBlock(), null) == null)) {
            return field(_el, (ExecSettableFieldOperation) _o, _stackCall);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variable(_el, (ExecStdRefVariableOperation)_o, _stackCall);
        }
        _stackCall.setCheckingExp(new ClassField("",""));
        return false;
    }

    private boolean affectationOrCompound(ExpressionLanguage _el, ExecAbstractAffectOperation _o, StackCall _stack) {
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            _stack.setCheckingExp(new ClassField("",""));
            return false;
        }
        if (_o instanceof ExecCompoundAffectationOperation) {
            return compound(_el, (ExecCompoundAffectationOperation)_o, _stack);
        }
        _stack.setCheckingExp(new ClassField("",""));
        return false;
    }

    private boolean field(ExpressionLanguage _el, ExecSettableFieldOperation _o, StackCall _stackCall) {
        if (sub(_o)) {
            _stackCall.setCheckingExp(_o.getSettableFieldContent().getClassField());
            _el.currentOper(_o);
            _stackCall.setVisited(false);
            return true;
        }
        _stackCall.setCheckingExp(new ClassField("",""));
        return false;
    }

    private boolean variable(ExpressionLanguage _el, ExecStdRefVariableOperation _o, StackCall _stackCall) {
        if (sub(_o)) {
            String v_ = _o.getVariableContent().getVariableName();
            AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(),_stackCall.getLastPage().getCache(),_stackCall.getLastPage().getRefParams());
            if (w_ instanceof FieldWrapper) {
                _stackCall.setCheckingExp(((FieldWrapper)w_).getId());
                _el.currentOper(_o);
                _stackCall.setVisited(false);
                return true;
            }
        }
        _stackCall.setCheckingExp(new ClassField("",""));
        return false;
    }
    private boolean compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, StackCall _stackCall) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                ExecOperationNode set_ = _o.getSettable();
                return settable(_el, _o, _stackCall, set_);
            }
            _stackCall.setCheckingExp(new ClassField("",""));
            return false;
        }
        if (_o instanceof ExecCompoundAffectationStringOperation) {
            ImplicitMethods implicits_ = _o.getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(), _o);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
                _stackCall.setCheckingExp(new ClassField("",""));
                return false;
            }
            ExecOperationNode set_ = _o.getSettable();
            return settable(_el, _o, _stackCall, set_);
        }
        _stackCall.setCheckingExp(new ClassField("",""));
        return false;
    }

    private boolean settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, StackCall _stackCall, ExecOperationNode _set) {
        if (_set instanceof ExecSettableFieldOperation) {
            _o.setRelOffsetPossibleLastPage(_o.getOperatorContent().getOpOffset(), _stackCall);
            _stackCall.setCheckingExp(((ExecSettableFieldOperation) _set).getSettableFieldContent().getClassField());
            _el.currentOper(_o);
            _stackCall.setVisited(false);
            return true;
        }
        if (_set instanceof ExecStdRefVariableOperation || _set instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _set);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            if (w_ instanceof FieldWrapper) {
                _o.setRelOffsetPossibleLastPage(_o.getOperatorContent().getOpOffset(), _stackCall);
                _stackCall.setCheckingExp(((FieldWrapper)w_).getId());
                _el.currentOper(_o);
                _stackCall.setVisited(false);
                return true;
            }
        }
        _stackCall.setCheckingExp(new ClassField("",""));
        return false;
    }

    private static boolean sub(ExecOperationNode _o) {
        ExecOperationNode curr_ = _o;
        while (curr_ != null) {
            if (curr_ instanceof ExecCompoundAffectationOperation && ((ExecCompoundAffectationOperation)curr_).getSettable() == _o) {
                return true;
            }
            curr_ = curr_.getParent();
        }
        return false;
    }

    @Override
    public boolean isChecking(ExpressionLanguage _o, ContextEl _context, StackCall _stackCall) {
        return !_stackCall.getCheckingExp().getClassName().isEmpty() ||_context.callsOrException(_stackCall);
    }

    @Override
    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (p_.getReadWrite() == null && _stackCall.nbPages() > 1) {
            AbstractPageEl previous_ = _stackCall.getCall(_stackCall.nbPages() - 2);
            if (!previous_.isEmptyEl()) {
                ExpressionLanguage el_ = previous_.getLastEl();
                endCall(_stackCall, el_);
            }
        }
        if (checkBreakPoint(_stackCall,p_)) {
            return stopAtCheckedBp(_context, _stackCall, p_);
        }
        return false;
    }

    private void endCall(StackCall _stackCall, ExpressionLanguage _el) {
        if (_stackCall.isVisited()) {
            return;
        }
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof ExecCompoundAffectationOperation) {
            ImplicitMethods implicits_ = ((ExecCompoundAffectationOperation) ex_).getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(),ex_);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (!ImplicitMethods.isValidIndex(implicits_,indexImplicit_)&&!pairBefore_.isEndCalculate()) {
                ExecOperationNode set_ = ((ExecCompoundAffectationOperation) ex_).getSettable();
                if (set_ instanceof ExecSettableFieldOperation) {
                    ClassField clField_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getClassField();
                    _stackCall.setCheckingExp(clField_);
                }
                if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
                    ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
                    AbstractWrapper w_ = argumentPair_.getWrapper();
                    wrapp(w_, _stackCall);
                }
            }
        }
        if (ex_ instanceof ExecSettableCallFctOperation && sub(ex_)) {
            AbstractWrapper w_ = _stackCall.getLastPage().getWrapper();
            wrapp(w_, _stackCall);
        }
    }

    private void wrapp(AbstractWrapper _w, StackCall _stackCall) {
        if (_w instanceof FieldWrapper) {
            _stackCall.setCheckingExp(((FieldWrapper) _w).getId());
        }
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
            _stackCall.setCheckingExp(new ClassField("",""));
            return false;
        }
        _stackCall.setVisited(true);
        if (stopStep(_context, _stackCall, _p)) {
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            _stackCall.setCheckingExp(new ClassField("",""));
            return true;
        }
        if (_context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().get()) {
            _context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().set(false);
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            _stackCall.setCheckingExp(new ClassField("",""));
            return true;
        }
        if (_stackCall.isMute()) {
            _stackCall.setCheckingExp(new ClassField("",""));
            return false;
        }
        if (!_stackCall.getCheckingExp().getClassName().isEmpty()) {
            ClassField clField_ = _stackCall.getCheckingExp();
            WatchPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullWatch(clField_);
            if (stopCurrentWp(bp_)) {
                _stackCall.setGlobalOffset(_p.getTraceIndex());
                _stackCall.setCheckingExp(new ClassField("",""));
                return true;
            }
            _stackCall.setCheckingExp(new ClassField("",""));
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

    private boolean stopCurrentWp(WatchPoint _bp) {
        return _bp.isEnabled();
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
        if (_stackCall.isCheckingException() || !_stackCall.getCheckingExp().getClassName().isEmpty()) {
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
