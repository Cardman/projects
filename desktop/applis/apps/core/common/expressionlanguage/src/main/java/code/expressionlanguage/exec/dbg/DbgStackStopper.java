package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractLambdaVariable;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class DbgStackStopper implements AbsStackStopper {
    public static final int READ = 0;
    public static final int WRITE = 1;
    public static final int COMPOUND_READ = 2;
    public static final int COMPOUND_WRITE = 3;
    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.DEBUG;
    }

    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return _page.sizeEl() == 0;
    }
    @Override
    public boolean stopAt(StackCall _stack) {
        return _stack.getStackState().isCheckingBp();
    }

    @Override
    public boolean isCheckingException(StackCall _stack) {
        return _stack.getStopper().hasFoundException(_stack) && _stack.getStackState().isCheckingBp();
    }

    @Override
    public boolean stopAt(ContextEl _context, StackCall _stack, int _size) {
        return stopAt(_stack) || _context.callsOrException(_stack);
    }

    @Override
    public boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        if (_stackCall.getStackState().visitedExp()) {
            return false;
        }
        _stackCall.getStackState().resetVisit(true);
        _stackCall.getStackState().visitExp();
        _el.currentOper(_o);
        return true;
    }

    private static CheckedExecOperationNodeInfos expOper(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompound(_el, (ExecAbstractAffectOperation)_o, _context, _stackCall);
        }
        if (isField(_o)) {
            return field(_el, (ExecSettableFieldOperation) _o,  _context, _stackCall);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variable((ExecStdRefVariableOperation)_o,  _context, _stackCall);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos affectationOrCompound(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, StackCall _stack) {
        _o.setRelOffsetPossibleLastPage(_o.getOperatorContent().getOpOffset(), _stack);
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            return null;
        }
        if (_o instanceof ExecCompoundAffectationOperation) {
            return compound(_el, (ExecCompoundAffectationOperation)_o, _context,_stack);
        }
        if (_o.getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) _o.getSettable()).isDeclare()) {
            return null;
        }
        CustList<ExecOperationNode> childrenNodes_ = _o.getChildrenNodes();
        Struct right_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1)).getArgument());
        return settable(_el, _o, _context,_stack, WRITE, right_);
    }

    private static CheckedExecOperationNodeInfos field(ExpressionLanguage _el, ExecSettableFieldOperation _o, ContextEl _context, StackCall _stackCall) {
        if (!_o.resultCanBeSet()) {
            int anc_ = _o.getSettableFieldContent().getAnc();
            Struct instance_ = instance(anc_,_el, _o, _stackCall);
            if (sub(_o)) {
                return new CheckedExecOperationNodeInfos(_o.getSettableFieldContent().getClassField(), COMPOUND_READ, AbstractLambdaVariable.formatted(_context, _o.getSettableFieldContent().getClassField(), instance_), instance_, null);
            }
            return new CheckedExecOperationNodeInfos(_o.getSettableFieldContent().getClassField(), READ, AbstractLambdaVariable.formatted(_context, _o.getSettableFieldContent().getClassField(), instance_), instance_, null);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos variable(ExecStdRefVariableOperation _o, ContextEl _context, StackCall _stackCall) {
        if (!_o.resultCanBeSet()) {
            if (sub(_o)) {
                return prVar(_o, _context, _stackCall, COMPOUND_READ);
            }
            return prVar(_o, _context, _stackCall, READ);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos prVar(ExecStdRefVariableOperation _o, ContextEl _context, StackCall _stackCall, int _mode) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams());
        if (w_ instanceof FieldWrapper) {
            Struct instance_ = AbstractLambdaVariable.value(w_);
            return new CheckedExecOperationNodeInfos(((FieldWrapper)w_).getId(), _mode,AbstractLambdaVariable.formatted(_context, (FieldWrapper) w_, ((FieldWrapper) w_).getId()), instance_, null);
        }
        return null;
    }
    private static CheckedExecOperationNodeInfos compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, StackCall _stackCall) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settable(_el, _o, _context,_stackCall, COMPOUND_WRITE,ArgumentListCall.toStr(argumentPair_.getArgument()));
            }
            return null;
        }
        if (_o instanceof ExecCompoundAffectationStringOperation) {
            ImplicitMethods implicits_ = _o.getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(), _o);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
                return null;
            }
            return settable(_el, _o, _context,_stackCall, COMPOUND_WRITE, ((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context,_stackCall));
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, StackCall _stackCall, int _mode, Struct _right) {
        ExecOperationNode set_ = _o.getSettable();
        if (isField(set_)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instanceSet(_el, anc_, set_,  ((ExecSettableFieldOperation) set_).resultCanBeSet(),_stackCall);
            return new CheckedExecOperationNodeInfos(((ExecSettableFieldOperation) set_).getSettableFieldContent().getClassField(), _mode, AbstractLambdaVariable.formatted(_ctx, ((ExecSettableFieldOperation) set_).getSettableFieldContent().getClassField(), instance_), instance_, _right);
        }
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            if (w_ instanceof FieldWrapper) {
                Struct instance_ = AbstractLambdaVariable.value(w_);
                return new CheckedExecOperationNodeInfos(((FieldWrapper)w_).getId(),_mode,AbstractLambdaVariable.formatted(_ctx, (FieldWrapper) w_,((FieldWrapper) w_).getId()), instance_, _right);
            }
        }
        return null;
    }

    private static boolean isField(ExecOperationNode _o) {
        return (_o instanceof ExecSettableFieldOperation) && !((ExecSettableFieldOperation) _o).isDeclare();
    }

    private static Struct instance(int _anc,ExpressionLanguage _el, ExecOperationNode _o, StackCall _stackCall) {
        Struct prev_ = ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o).getPreviousArgument()));
        Struct instance_;
        if (prev_ == NullStruct.NULL_VALUE) {
            instance_ = _stackCall.getLastPage().getGlobalStruct();
        } else {
            instance_ = prev_;
        }
        return ExecFieldTemplates.getParent(_anc,instance_,_stackCall);
    }

    private static Struct instanceSet(ExpressionLanguage _el, int _anc, ExecOperationNode _o, boolean _writeOnly, StackCall _stackCall) {
        if (_writeOnly) {
            return instance(_anc,_el,_o,_stackCall);
        }
        return ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o).getArgumentParent()));
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
    public boolean isStopAtRefField(FieldMetaInfo _meta, ContextEl _context, StackCall _stackCall) {
        if (AbstractLambdaVariable.stopMetaField(_meta, _context, _stackCall)) {
            _stackCall.getStackState().resetVisit(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean isStopAtRefVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall) {
        _stackCall.getStackState().resetVisit(true);
        return true;
    }

    @Override
    public boolean isChecking(ExpressionLanguage _o, ContextEl _context, StackCall _stackCall) {
        return _stackCall.getStackState().isCheckingBp() ||_context.callsOrException(_stackCall);
    }

    @Override
    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CheckedExecOperationNodeInfos infos_;
        ExecOperationNode ex_;
        if (p_ instanceof AbstractLambdaVariable) {
            infos_ = ((AbstractLambdaVariable) p_).infosVisited(_context, _stackCall);
            ex_ = null;
        } else if (p_.getReadWrite() == ReadWrite.EXIT && _stackCall.nbPages() > 1) {
            AbstractPageEl previous_ = _stackCall.getCall(_stackCall.nbPages() - 2);
            if (!previous_.isEmptyEl()) {
                ExpressionLanguage el_ = previous_.getLastEl();
                ex_ = el_.getCurrentOper();
                infos_ = end(_context, _stackCall, el_);
            } else {
                infos_ = null;
                ex_ = null;
            }
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            ex_ = el_.getCurrentOper();
            infos_ = expOper(el_, el_.getCurrentOper(), _context, _stackCall);
        } else {
            ex_ = null;
            infos_ = null;
        }
        if (checkBreakPoint(_stackCall,p_,infos_)) {
            return stopAtCheckedBp(_context, _stackCall, p_, ex_, infos_);
        }
        return false;
    }

    private static CheckedExecOperationNodeInfos end(ContextEl _context, StackCall _stackCall, ExpressionLanguage _el) {
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof ExecCompoundAffectationOperation) {
            ImplicitMethods implicits_ = ((ExecCompoundAffectationOperation) ex_).getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(),ex_);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (!ImplicitMethods.isValidIndex(implicits_,indexImplicit_)&&!pairBefore_.isEndCalculate()) {
                return settable(_el,(ExecCompoundAffectationOperation) ex_,_context,_stackCall, COMPOUND_WRITE,ArgumentListCall.toStr(_stackCall.getLastPage().getReturnedArgument()));
            }
        }
        if (ex_ instanceof ExecSettableCallFctOperation) {
            if (sub(ex_)) {
                AbstractWrapper w_ = _stackCall.getLastPage().getWrapper();
                return wrapp(w_, _context, COMPOUND_READ);
            } else if (!((ExecSettableCallFctOperation) ex_).resultCanBeSet()) {
                AbstractWrapper w_ = _stackCall.getLastPage().getWrapper();
                return wrapp(w_, _context, READ);
            }
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos wrapp(AbstractWrapper _w, ContextEl _context, int _m) {
        if (_w instanceof FieldWrapper) {
            Struct instance_ = AbstractLambdaVariable.value(_w);
            String formatted_ = ExecInherits.getQuickFullTypeByBases(instance_.getClassName(_context), ((FieldWrapper) _w).getId().getClassName(), _context);
            ExecRootBlock ex_ = _context.getClasses().getClassBody(((FieldWrapper) _w).getId().getClassName());
            return new CheckedExecOperationNodeInfos(((FieldWrapper) _w).getId(),_m,new ExecFormattedRootBlock(ex_,formatted_),instance_,null);
        }
        return null;
    }

    @Override
    public boolean hasFoundException(StackCall _stackCall) {
        return _stackCall.trueException() != null;
    }

    @Override
    public ExpressionLanguageBp checkBpWithoutClear(StackCall _stack, int _index, AbstractPageEl _ip, CustList<ExecOperationNode> _list, ExecBlock _bl) {
        return ExecHelperBlocks.checkBpWithoutClear(_stack, _index, _ip, _list, _bl);
    }

    private static boolean stopAtCheckedBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ExecOperationNode _ex, CheckedExecOperationNodeInfos _infos) {
        if (_stackCall.getStackState().visitedInst()) {
            return false;
        }
        _stackCall.getStackState().visitInst();
        _stackCall.setOperElt(_infos);
        if (stopStep(_context, _stackCall, _p, _ex)) {
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            return true;
        }
        if (_ex == null && _context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().get()) {
            _context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().set(false);
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            return true;
        }
        if (_stackCall.isMute()) {
            return false;
        }
        if (_infos != null) {
            ClassField clField_ = _infos.getIdClass();
            WatchPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullWatch(clField_);
            if (stopCurrentWp(bp_,_context,_p,_stackCall,_infos)) {
                _stackCall.setGlobalOffset(_p.getTraceIndex());
                return true;
            }
            return false;
        }
        if (!beginInstrPart(_p,_ex)) {
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

    private static boolean stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ExecOperationNode _ex) {
        if (_stackCall.getStep() == StepDbgActionEnum.RETURN_METHOD && _p.getReadWrite() == ReadWrite.EXIT && _stackCall.getPreviousNbPages() >= _stackCall.nbPages()) {
            return true;
        }
        return beginInstrPart(_p, _ex) && (_stackCall.getStep() == StepDbgActionEnum.NEXT_IN_METHOD && _stackCall.getPreviousNbPages() >= _stackCall.nbPages() || _stackCall.getStep() == StepDbgActionEnum.NEXT_INSTRUCTION || stopTmp(_context, _stackCall, _p));
    }

    private static boolean beginInstrPart(AbstractPageEl _p, ExecOperationNode _ex) {
        return _ex == null && _p.getReadWrite() != ReadWrite.EXIT;
    }

    private static boolean stopTmp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
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
    private static boolean stopExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        Struct str_ = stopExcValuRetThrowCatch(_stackCall, _p);
        if (str_ == null) {
            return false;
        }
        String clName_ = str_.getClassName(_context);
        ExcPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullExc(clName_,true);
        return checkExc(_context, _stackCall, _p, str_, bp_) || checkExc(_context, _stackCall, _p, str_, _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullExc(StringExpUtil.getIdFromAllTypes(clName_),false));
    }

    private static boolean checkExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, ExcPoint _bp) {
        if (!stopExcValue(_context, _stackCall, _p, _bp)) {
            return false;
        }
        String clName_ = _str.getClassName(_context);
        _stackCall.setOperElt(new CoreCheckedExecOperationNodeInfos(new ExecFormattedRootBlock(_context.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(clName_)), clName_), _str,null));
        return stopCurrent(_context, _stackCall, _p, _bp.getResult());
    }

    private static Struct stopExcValuRetThrowCatch(StackCall _stackCall, AbstractPageEl _p) {
        AbstractStask stLast_ = _p.tryGetLastStack();
        ExecBlock bl_ = _p.getBlock();
        if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            return ((TryBlockStack) stLast_).getException();
        }
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            return exc_.getStruct();
        }
        return null;
    }
    private static boolean stopExcValue(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ExcPoint _ex) {
        AbstractStask stLast_ = _p.tryGetLastStack();
        ExecBlock bl_ = _p.getBlock();
        if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            Struct e_ = ((TryBlockStack) stLast_).getException();
            if ((_ex.getConditionReturn() == ConditionReturn.YES || _ex.getConditionReturn() == ConditionReturn.CALL_EX) && ExecHelperBlocks.firstMatch(_context, _stackCall, ((ExecAbstractCatchEval)bl_).getContent(), e_, ((ExecAbstractCatchEval)bl_).isCatchAll()) && _p.sizeEl() < 2) {
                return true;
            }
        }
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            return _ex.getConditionReturn() == ConditionReturn.NO || _ex.getConditionReturn() == ConditionReturn.CALL_EX;
        }
        return false;
    }
    private static boolean stopCurrentBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPoint _bp) {
        if (!(_bp.isEnabled() && (!_bp.isEnabledChgtType() || _bp.isInstanceType() && _p instanceof AbstractCallingInstancingPageEl || _bp.isStaticType() && _p instanceof StaticInitPageEl))) {
            return false;
        }
        BreakPointCondition condition_ = stopCurrentBpCondition(_bp);
        return stopCurrent(_context, _stackCall, _p, condition_);
    }

    private static boolean stopCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPointCondition _condition) {
        if (okStack(_context, _stackCall, _condition) && condition(_context, _stackCall, _p, _condition.getResult())) {
            if (!_condition.getEnabled().get()) {
                return false;
            }
            if (countMatch(_condition)) {
                if (_condition.getDisableWhenHit().get()) {
                    _condition.getEnabled().set(false);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static boolean stopCurrentWp(WatchPoint _bp, ContextEl _context, AbstractPageEl _p, StackCall _stackCall, CheckedExecOperationNodeInfos _check) {
        if (!_bp.isEnabled()) {
            return false;
        }
        if (!(_bp.isRead() && _check.getModeField() == READ || _bp.isWrite() && _check.getModeField() == WRITE || _bp.isCompoundRead() && _check.getModeField() == COMPOUND_READ || _bp.isCompoundWrite() && _check.getModeField() == COMPOUND_WRITE)) {
            return false;
        }
        BreakPointCondition condition_ = stopCurrentWpCondition(_bp, _check);
        return stopCurrent(_context, _stackCall, _p, condition_);
    }

    private static BreakPointCondition stopCurrentWpCondition(WatchPoint _bp, CheckedExecOperationNodeInfos _check) {
        if (_check.getModeField() == READ) {
            return _bp.getResultRead();
        }
        if (_check.getModeField() == WRITE) {
            return _bp.getResultWrite();
        }
        if (_check.getModeField() == COMPOUND_READ) {
            return _bp.getResultCompoundRead();
        }
        return _bp.getResultCompoundWrite();
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
    private static boolean okStack(ContextEl _context, StackCall _stackCall, BreakPointCondition _bp) {
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

    private static boolean excOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return false;
            }
        }
        return true;
    }
    private static boolean incOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return true;
            }
        }
        return false;
    }
    private static boolean condition(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ResultContextLambda _result) {
        if (_result == null) {
            return true;
        }
        StackCallReturnValue result_ = _result.eval(_context, _stackCall.getOperElt(), _p);
        if (result_.getStack().getCallingState() != null) {
            _stackCall.setCallingStateSub(result_.getStack().getCallingState());
            return true;
        }
        return BooleanStruct.isTrue(ArgumentListCall.toStr(result_.getRetValue().getValue()));
    }
    private static BreakPointCondition stopCurrentBpCondition(BreakPoint _bp) {
        if (!_bp.isEnabledChgtType()) {
            return _bp.getResultStd();
        }
        if (_bp.isInstanceType()) {
            return _bp.getResultInstance();
        }
        return _bp.getResultStatic();
    }

    private static int[] list(StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getStopper().hasFoundException(_stackCall)) {
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

    private static boolean checkBreakPoint(StackCall _stackCall, AbstractPageEl _p, CheckedExecOperationNodeInfos _infos) {
        if (_stackCall.getStopper().hasFoundException(_stackCall) || _infos != null) {
            return true;
        }
        if (_p.getReadWrite() == ReadWrite.EXIT) {
            return true;
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
