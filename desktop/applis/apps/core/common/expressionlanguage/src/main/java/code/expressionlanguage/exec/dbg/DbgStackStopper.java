package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
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
    private static final int READ = 0;
    private static final int WRITE = 1;
    private static final int COMPOUND_READ = 2;
    private static final int COMPOUND_WRITE = 3;
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
            reset(_stackCall);
            return false;
        }
        _stackCall.setVisitedExp(true);
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompound(_el, (ExecAbstractAffectOperation)_o, _context, _stackCall);
        }
        if (isField(_o, _context, _stackCall)) {
            return field(_el, (ExecSettableFieldOperation) _o,  _context, _stackCall);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variable(_el, (ExecStdRefVariableOperation)_o,  _context, _stackCall);
        }
        reset(_stackCall);
        return false;
    }

    private static boolean affectationOrCompound(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, StackCall _stack) {
        _o.setRelOffsetPossibleLastPage(_o.getOperatorContent().getOpOffset(), _stack);
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            reset(_stack);
            return false;
        }
        if (_o instanceof ExecCompoundAffectationOperation) {
            return compound(_el, (ExecCompoundAffectationOperation)_o, _context,_stack);
        }
        if (_o.getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) _o.getSettable()).isDeclare()) {
            reset(_stack);
            return false;
        }
        CustList<ExecOperationNode> childrenNodes_ = _o.getChildrenNodes();
        Struct right_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1)).getArgument());
        return settable(_el, _o, _context,_stack, WRITE, right_);
    }

    private static boolean field(ExpressionLanguage _el, ExecSettableFieldOperation _o, ContextEl _context, StackCall _stackCall) {
        if (!_o.resultCanBeSet()) {
            int anc_ = _o.getSettableFieldContent().getAnc();
            Struct instance_ = instance(anc_,_el, _o, _stackCall);
            if (sub(_o)) {
                check(_el, _o, _stackCall, _o.getSettableFieldContent().getClassField(), COMPOUND_READ, new CheckedExecOperationNodeInfos(formatted(_context, _o.getSettableFieldContent().getClassField(), instance_), instance_, null));
                return true;
            }
            check(_el, _o, _stackCall, _o.getSettableFieldContent().getClassField(), READ, new CheckedExecOperationNodeInfos(formatted(_context, _o.getSettableFieldContent().getClassField(), instance_), instance_, null));
            return true;
        }
        reset(_stackCall);
        return false;
    }

    private static boolean variable(ExpressionLanguage _el, ExecStdRefVariableOperation _o, ContextEl _context, StackCall _stackCall) {
        if (!_o.resultCanBeSet()) {
            if (sub(_o)) {
                return prVar(_el, _o, _context, _stackCall, COMPOUND_READ);
            }
            return prVar(_el, _o, _context, _stackCall, READ);
        }
        reset(_stackCall);
        return false;
    }

    private static boolean prVar(ExpressionLanguage _el, ExecStdRefVariableOperation _o, ContextEl _context, StackCall _stackCall, int _mode) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams());
        if (w_ instanceof FieldWrapper) {
            Struct instance_ = value(w_);
            check(_el, _o, _stackCall, ((FieldWrapper)w_).getId(), _mode, new CheckedExecOperationNodeInfos(formatted(_context, (FieldWrapper) w_, ((FieldWrapper) w_).getId()), instance_, null));
            return true;
        }
        reset(_stackCall);
        return false;
    }
    private static boolean compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, StackCall _stackCall) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settable(_el, _o, _context,_stackCall, COMPOUND_WRITE,ArgumentListCall.toStr(argumentPair_.getArgument()));
            }
            reset(_stackCall);
            return false;
        }
        if (_o instanceof ExecCompoundAffectationStringOperation) {
            ImplicitMethods implicits_ = _o.getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(), _o);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
                reset(_stackCall);
                return false;
            }
            return settable(_el, _o, _context,_stackCall, COMPOUND_WRITE, ((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context,_stackCall));
        }
        reset(_stackCall);
        return false;
    }

    private static boolean settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, StackCall _stackCall, int _mode, Struct _right) {
        ExecOperationNode set_ = _o.getSettable();
        if (isField(set_,_ctx,_stackCall)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instanceSet(_el, anc_, set_,  ((ExecSettableFieldOperation) set_).resultCanBeSet(),_stackCall);
            check(_el, _o, _stackCall, ((ExecSettableFieldOperation) set_).getSettableFieldContent().getClassField(), _mode, new CheckedExecOperationNodeInfos(formatted(_ctx, ((ExecSettableFieldOperation) set_).getSettableFieldContent().getClassField(), instance_), instance_, _right));
            return true;
        }
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            if (w_ instanceof FieldWrapper) {
                Struct instance_ = value(w_);
                check(_el, _o, _stackCall, ((FieldWrapper)w_).getId(), _mode, new CheckedExecOperationNodeInfos(formatted(_ctx, (FieldWrapper) w_,((FieldWrapper) w_).getId()), instance_, _right));
                return true;
            }
        }
        reset(_stackCall);
        return false;
    }

    private static boolean isField(ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        return (_o instanceof ExecSettableFieldInstOperation || (_o instanceof ExecSettableFieldStatOperation && _context.getExiting().state(_stackCall, ((ExecSettableFieldStatOperation) _o).getRootBlock(), null) == null)) && !((ExecSettableFieldOperation) _o).isDeclare();
    }

    private static void check(ExpressionLanguage _el, ExecOperationNode _o, StackCall _stackCall, ClassField _id, int _mode, CheckedExecOperationNodeInfos _oper) {
        _stackCall.setCheckingExp(_id);
        _stackCall.setModeField(_mode);
        _el.currentOper(_o);
        _stackCall.setVisited(false);
        _stackCall.setOperElt(_oper);
    }

    private static ExecFormattedRootBlock formatted(ContextEl _ctx, FieldWrapper _w, ClassField _id) {
        if (_w instanceof StaticFieldWrapper) {
            return new ExecFormattedRootBlock(((StaticFieldWrapper)_w).getRoot());
        }
        Struct instance_ = value(_w);
        return formatted(_ctx, _id, instance_);
    }
    private static ExecFormattedRootBlock formatted(ContextEl _ctx, ClassField _id, Struct _inst) {
        String formatted_ = ExecInherits.getQuickFullTypeByBases(_inst.getClassName(_ctx), _id.getClassName(), _ctx);
        ExecRootBlock ex_ = _ctx.getClasses().getClassBody(_id.getClassName());
        return new ExecFormattedRootBlock(ex_, formatted_);
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
    public boolean isStopAtRefGetField(FieldMetaInfo _meta, Struct _instance, ContextEl _context, StackCall _stackCall) {
        if (stopMetaField(_meta, _context, _stackCall)) {
            ClassField cf_ = new ClassField(StringExpUtil.getIdFromAllTypes(_meta.getFormatted().getFormatted()), _meta.getName());
            _stackCall.setCheckingExp(cf_);
            _stackCall.setOperElt(new CheckedExecOperationNodeInfos(formatted(_context,cf_,_instance),_instance,null));
            _stackCall.setModeField(READ);
            _stackCall.setVisited(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean isStopAtRefGetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_meta.getArgumentWrappers());
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            ClassField cf_ = ((FieldWrapper) w_).getId();
            _stackCall.setCheckingExp(cf_);
            _stackCall.setModeField(READ);
            _stackCall.setVisited(false);
            Struct instance_ = value(w_);
            _stackCall.setOperElt(new CheckedExecOperationNodeInfos(formatted(_context, (FieldWrapper) w_,cf_),instance_,null));
            return true;
        }
        return false;
    }

    @Override
    public boolean isStopAtRefSetField(FieldMetaInfo _meta, Struct _instance, Struct _right, ContextEl _context, StackCall _stackCall) {
        if (stopMetaField(_meta, _context, _stackCall)) {
            ClassField cf_ = new ClassField(StringExpUtil.getIdFromAllTypes(_meta.getFormatted().getFormatted()), _meta.getName());
            _stackCall.setCheckingExp(cf_);
            _stackCall.setOperElt(new CheckedExecOperationNodeInfos(formatted(_context,cf_,_instance),_instance,_right));
            _stackCall.setModeField(WRITE);
            _stackCall.setVisited(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean isStopAtRefSetVar(ArgumentListCall _meta, ContextEl _context, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _meta.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            ClassField cf_ = ((FieldWrapper) w_).getId();
            _stackCall.setCheckingExp(cf_);
            _stackCall.setModeField(WRITE);
            _stackCall.setVisited(false);
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            Struct instance_ = value(w_);
            _stackCall.setOperElt(new CheckedExecOperationNodeInfos(formatted(_context, (FieldWrapper) w_,cf_),instance_,right_));
            return true;
        }
        return false;
    }

    private static boolean stopMetaField(FieldMetaInfo _meta, ContextEl _context, StackCall _stackCall) {
        return !_meta.isStaticField() || _context.getExiting().state(_stackCall, _meta.getFormatted().getRootBlock(), null) == null;
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
                endCall(_context,_stackCall, el_);
            }
        }
        if (checkBreakPoint(_stackCall,p_)) {
            return stopAtCheckedBp(_context, _stackCall, p_);
        }
        return false;
    }

    private static void endCall(ContextEl _context, StackCall _stackCall, ExpressionLanguage _el) {
        if (_stackCall.isVisited()) {
            return;
        }
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof ExecCompoundAffectationOperation) {
            ImplicitMethods implicits_ = ((ExecCompoundAffectationOperation) ex_).getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(),ex_);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (!ImplicitMethods.isValidIndex(implicits_,indexImplicit_)&&!pairBefore_.isEndCalculate()) {
                settable(_el,(ExecCompoundAffectationOperation) ex_,_context,_stackCall, COMPOUND_WRITE,ArgumentListCall.toStr(_stackCall.getLastPage().getReturnedArgument()));
            }
        }
        if (ex_ instanceof ExecSettableCallFctOperation) {
            if (sub(ex_)) {
                AbstractWrapper w_ = _stackCall.getLastPage().getWrapper();
                wrapp(w_, _context, _stackCall, COMPOUND_READ);
            } else if (!((ExecSettableCallFctOperation) ex_).resultCanBeSet()) {
                AbstractWrapper w_ = _stackCall.getLastPage().getWrapper();
                wrapp(w_, _context, _stackCall, READ);
            }
        }
    }

    private static void wrapp(AbstractWrapper _w, ContextEl _context, StackCall _stackCall, int _m) {
        if (_w instanceof FieldWrapper) {
            _stackCall.setCheckingExp(((FieldWrapper) _w).getId());
            _stackCall.setModeField(_m);
            Struct instance_ = value(_w);
            String formatted_ = ExecInherits.getQuickFullTypeByBases(instance_.getClassName(_context), ((FieldWrapper) _w).getId().getClassName(), _context);
            ExecRootBlock ex_ = _context.getClasses().getClassBody(((FieldWrapper) _w).getId().getClassName());
            _stackCall.setOperElt(new CheckedExecOperationNodeInfos(new ExecFormattedRootBlock(ex_,formatted_),instance_,null));
        }
    }
    private static Struct value(AbstractWrapper _f) {
        if (_f instanceof InstanceFieldWrapper) {
            return ((InstanceFieldWrapper)_f).getParent();
        }
        return NullStruct.NULL_VALUE;
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

    private static boolean stopAtCheckedBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.isVisited()) {
            reset(_stackCall);
            return false;
        }
        _stackCall.setVisited(true);
        if (stopStep(_context, _stackCall, _p)) {
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            reset(_stackCall);
            return true;
        }
        if (_context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().get()) {
            _context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().set(false);
            _stackCall.setGlobalOffset(_p.getGlobalOffset());
            reset(_stackCall);
            return true;
        }
        if (_stackCall.isMute()) {
            reset(_stackCall);
            return false;
        }
        if (!_stackCall.getCheckingExp().getClassName().isEmpty()) {
            ClassField clField_ = _stackCall.getCheckingExp();
            WatchPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullWatch(clField_);
            if (stopCurrentWp(bp_,_context,_p,_stackCall)) {
                _stackCall.setGlobalOffset(_p.getTraceIndex());
                reset(_stackCall);
                return true;
            }
            reset(_stackCall);
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

    private static void reset(StackCall _st) {
        _st.setCheckingExp(new ClassField("",""));
        _st.setOperElt(null);
    }

    private static boolean stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        return _stackCall.getStep() == StepDbgActionEnum.RETURN_METHOD && _p.getReadWrite() == null || _stackCall.getStep() == StepDbgActionEnum.NEXT_IN_METHOD && _stackCall.getPreviousNbPages() >= _stackCall.nbPages() || _stackCall.getStep() == StepDbgActionEnum.NEXT_INSTRUCTION || stopTmp(_context, _stackCall, _p);
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

    private static boolean stopCurrentWp(WatchPoint _bp, ContextEl _context, AbstractPageEl _p, StackCall _stackCall) {
        if (!_bp.isEnabled()) {
            return false;
        }
        if (!(_bp.isRead() && _stackCall.getModeField() == READ || _bp.isWrite() && _stackCall.getModeField() == WRITE || _bp.isCompoundRead() && _stackCall.getModeField() == COMPOUND_READ || _bp.isCompoundWrite() && _stackCall.getModeField() == COMPOUND_WRITE)) {
            return false;
        }
        BreakPointCondition condition_ = stopCurrentWpCondition(_bp, _stackCall);
        return stopCurrent(_context, _stackCall, _p, condition_);
    }

    private static BreakPointCondition stopCurrentWpCondition(WatchPoint _bp, StackCall _stackCall) {
        if (_stackCall.getModeField() == READ) {
            return _bp.getResultRead();
        }
        if (_stackCall.getModeField() == WRITE) {
            return _bp.getResultWrite();
        }
        if (_stackCall.getModeField() == COMPOUND_READ) {
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

    private static boolean checkBreakPoint(StackCall _stackCall, AbstractPageEl _p) {
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
