package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class DbgStackStopper extends AbsStackStopperImpl {
    public DbgStackStopper(AbsLogDbg _log) {
        super(_log);
    }
    @Override
    public int checkNext(ContextEl _context, StackCall _stackCall) {
        if (!_stackCall.getBreakPointInfo().getBreakPointOutputInfo().isStoppedBreakPoint()) {
            return 0;
        }
        if (_stackCall.getInitializingTypeInfos().getInitEnums() != InitPhase.NOTHING || stopAtWp(getLogger(),_context, _stackCall)) {
            return 1;
        }
        return 2;
    }

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
        return _stack.getBreakPointInfo().getStackState().isCheckingBp();
    }

    @Override
    public boolean isStopAtRef(ContextEl _context, StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getStackState().visitedExp()) {
            _stackCall.getBreakPointInfo().getStackState().visitedNone();
            return false;
        }
        _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
        _stackCall.getBreakPointInfo().getStackState().visitExp();
        return true;
    }

    @Override
    public StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        if (_context.pausedLoop().getAndSet(false)) {
            return StopDbgEnum.PAUSE;
        }
        return stopAtCheckedBp(getLogger(),_context, _stackCall);
    }

    @Override
    public boolean callsOrException(ContextEl _owner, StackCall _stackCall) {
        return _stackCall.callsOrException() || _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }

    @Override
    public boolean hasValueStd(StackCall _stack) {
        return !_stack.callsOrException();
    }

    @Override
    public boolean isStopAtExcMethod() {
        return true;
    }

    @Override
    public int checkBpWithoutClearCount(StackCall _stack, AbstractPageEl _ip, int _old) {
        if (_old < _ip.sizeEl()) {
            _stack.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
            return 1;
        }
        return 0;
    }


    private static ExecOperationNode getCurrentOper(AbstractPageEl _p) {
        ExecOperationNode ex_;
        if (!_p.isEmptyEl()){
            ExpressionLanguage el_ = _p.getLastEl();
            ex_ = el_.getCurrentOper();
        } else {
            ex_ = null;
        }
        return ex_;
    }

    private static boolean stopAtWp(AbsLogDbg _l,ContextEl _context,StackCall _stackCall) {
        StopDbgEnum current_ = _stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint();
        while (true) {
            SuspendedCandidateInfos first_ = calculateInterm(_context, _stackCall, current_);
            if (first_ == null) {
                return false;
            }
            StopDbgEnum second_ = logs(_l,first_,_context,_stackCall);
            if (second_ != StopDbgEnum.NONE) {
                _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(second_);
                return true;
            }
            current_ = first_.getStep();
        }
    }
    private static SuspendedCandidateInfos calculateInterm(ContextEl _context, StackCall _stackCall, StopDbgEnum _state) {
        GroupCheckedExecOperationNodeInfos g_ = new GroupCheckedExecOperationNodeInfos(_context, _stackCall);
        CoreCheckedExecOperationNodeInfos infos_ = g_.getInfos();
        StdMethodCheckedExecOperationNodeInfos infosStd_ = g_.getInfosStd();
        if (_state == StopDbgEnum.STEP_RETURN_METHOD) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            Struct str_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
            return current(_context, _stackCall, g_, str_);
        }
        if (infosStd_ != null && (_state == StopDbgEnum.METHOD_STD_ENTRY || _state == StopDbgEnum.METHOD_STD_EXIT)) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return nextEntry(_context, g_, _stackCall, p_);
        }
        if (_state == StopDbgEnum.METHOD_ABS_EXIT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedMicro(_context,_stackCall,p_, infos_, g_);
        }
        if (_state == StopDbgEnum.PARENT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedNatAw(_context,_stackCall,p_, infos_, g_);
        }
        if (_state == StopDbgEnum.OPER_NAT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedAw(_context,_stackCall,p_, infos_, g_.getProcessEnds());
        }
        if (_state == StopDbgEnum.METHOD_ABS_REF_ENTRY) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return stdCallee(_context,g_,_stackCall,p_);
        }
        if (_state == StopDbgEnum.METHOD_ABS_ENTRY) {
            return afterAbsEntry(_context, _stackCall, g_);
        }
        return null;
    }

    private static StopDbgEnum stopAtCheckedBp(AbsLogDbg _l,ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        GroupCheckedExecOperationNodeInfos g_ = new GroupCheckedExecOperationNodeInfos(_context, _stackCall);
        if (!checkBreakPoint(_context,_stackCall, g_)) {
            return StopDbgEnum.NONE;
        }
        Struct exc_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
        StopDbgEnum step_ = stopStep(_context, _stackCall, p_, exc_);
        if (step_ != StopDbgEnum.NONE) {
            return step_;
        }
        return logs(_l,current(_context, _stackCall, g_, exc_),_context,_stackCall);
    }
    private static StopDbgEnum logs(AbsLogDbg _l, SuspendedCandidateInfos _s, ContextEl _context, StackCall _stackCall) {
        if (_s == null) {
            return StopDbgEnum.NONE;
        }
        AbstractPageEl p_ = _s.getPageEl();
        BreakPointOutputInfo out_ = _stackCall.getBreakPointInfo().getBreakPointOutputInfo();
        BreakPointCondition bp_ = _s.getBreakPointCondition();
        if (bp_.getStackLog().get()) {
            for (String s: ResultContextLambda.trace(_stackCall,_context)) {
                _l.log(s);
            }
        }
        CheckedMethodInfos o_ = _s.getInfos();
        ResultContextLambda logs_ = bp_.getLogs();
        if (logs_ != null) {
            for (String s: logs_.evalLog(o_, p_)) {
                _l.log(s);
            }
        }
        if (bp_.getSuspend().get()) {
            out_.getWatchResults().setWatchedObject(null);
            out_.getWatchResults().setWatchedTrace(null);
            out_.getWatchResults().setSubContext(null);
            ResultContextLambda w_ = bp_.getWatches();
            if (w_ != null) {
                out_.getWatchResults().setSubContext(w_.getContext());
                StackCallReturnValue st_ = w_.eval(o_, p_);
                if (st_.getStack().trueException() != null) {
                    out_.getWatchResults().setWatchedTrace(st_.getStack().getStackView());
                } else {
                    out_.getWatchResults().setWatchedObject(ArgumentListCall.toStr(st_.getStack().aw().getValue()));
                }
            }
            out_.setBpc(bp_);
            out_.setOperElt(o_);
            return _s.getStep();
        }
        return StopDbgEnum.NONE;
    }

    private static SuspendedCandidateInfos current(ContextEl _context, StackCall _stackCall, GroupCheckedExecOperationNodeInfos _g, Struct _exc) {
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().isMute()) {
            return null;
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.normalCallNoExit(_context)) {
            return enterCase(_context, _stackCall, p_);
        }
        SuspendedCandidateInfos exit_ = exitMethod(_context, _stackCall, p_);
        if (exit_ != null) {
            return exit_;
        }
        if (_exc != null) {
            SuspendedCandidateInfos exc_ = stopExc(_context, _stackCall, p_, _exc);
            if (exc_ != null) {
                return exc_;
            }
            return defBp(_context, _stackCall, _g.getProcessEnds());
        }
        StdMethodCheckedExecOperationNodeInfos std_ = _g.getInfosStd();
        if (std_ != null && !std_.isExiting()) {
            return enterCase(_context, _g, _stackCall,p_);
        }
        CoreCheckedExecOperationNodeInfos infos_ = _g.getInfos();
        if (infos_ instanceof FieldCheckedExecOperationNodeInfos || infos_ instanceof ArrCheckedExecOperationNodeInfos) {
            return checkedMicro(_context, _stackCall, p_, infos_, _g);
        }
        CallCheckedExecOperationNodeInfos call_ = _g.getInfosCall();
        if (call_ != null && !call_.isExit()) {
            return enterCase(_context, _stackCall, p_, call_, StopDbgEnum.METHOD_ABS_ENTRY);
        }
        return afterAbsEntry(_context, _stackCall, _g);
    }

    private static SuspendedCandidateInfos afterAbsEntry(ContextEl _context, StackCall _stackCall, GroupCheckedExecOperationNodeInfos _g) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        StdMethodCheckedExecOperationNodeInfos std_ = _g.getInfosStd();
        CoreCheckedExecOperationNodeInfos infos_ = _g.getInfos();
        CallCheckedExecOperationNodeInfos call_ = _g.getInfosCall();
        if (std_ != null) {
            return enterCase(_context, _g, _stackCall, p_);
        }
        if (call_ != null && call_.isExit()) {
            SuspendedCandidateInfos s_ = exitMethod(_context, _stackCall, p_, call_, StopDbgEnum.METHOD_ABS_EXIT);
            if (s_ != null) {
                return s_;
            }
        }
        return checkedMicro(_context, _stackCall, p_, infos_, _g);
    }

    private static SuspendedCandidateInfos operNat(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, OperNatCheckedExecOperationNodeInfos _nat) {
        if (_nat != null) {
            AbsOperNatPointBlockPair p_ = _context.getPairOperNat(_nat.getKey());
            if (p_ instanceof OperNatPointBlockPair) {
                return checkOperNat(_context, _stackCall, _p, _nat, stopOperNatValue(((OperNatPointBlockPair)p_).getValue(), _nat.getModeField()));
            }
            if (p_ instanceof CompoOperNatPointBlockPair) {
                return checkOperNat(_context, _stackCall, _p, _nat, stopOperNatValue(((CompoOperNatPointBlockPair)p_).getValue(), _nat.getModeField()));
            }
            return checkOperNat(_context, _stackCall, _p, _nat, stopOperNatValue(_context.operNatDisabled().getValue(), _nat.getModeField()));
        }
        return null;
    }

    private static SuspendedCandidateInfos defBp(ContextEl _context, StackCall _stackCall, int _prEnd) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null || _stackCall.trueException() != null || getCurrentOper(p_) != null && _prEnd == ResultContext.NO_END || _stackCall.getReadWrite() != ReadWrite.ENTRY) {
            return null;
        }
        for (int i : list(p_)) {
            BreakPointBlockPair bp_ = _context.getNotNullPair(p_.getFile(), i);
            SuspendedCandidateInfos s_ = stopCurrentBp(_context, _stackCall, p_, bp_.getValue());
            if (s_ != null) {
                return s_;
            }
        }
        for (int i : list(p_)) {
            TypePointBlockPair bp_ = _context.getNotNullTypePair(p_.getFile(), i);
            SuspendedCandidateInfos s_ = stopCurrentTp(_context, _stackCall, p_, bp_.getValue());
            if (s_ != null) {
                return s_;
            }
        }
        return null;
    }

    private static SuspendedCandidateInfos checkedMicro(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos, GroupCheckedExecOperationNodeInfos _g) {
        SuspendedCandidateInfos res_ = checkedParent(_context, _stackCall, _p, _g.getPar());
        if (res_ != null) {
            return res_;
        }
        return checkedNatAw(_context, _stackCall, _p, _infos, _g);
    }

    private static SuspendedCandidateInfos checkedNatAw(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos, GroupCheckedExecOperationNodeInfos _g) {
        SuspendedCandidateInfos nat_ = operNat(_context, _stackCall, _p, _g.getOpNat());
        if (nat_ != null) {
            return nat_;
        }
        return checkedAw(_context, _stackCall, _p, _infos, _g.getProcessEnds());
    }
    private static SuspendedCandidateInfos checkedParent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _par) {
        if (_par != null) {
            return stopPar(_context, _stackCall, _p, _par.getInstance(), _par);
        }
        return null;
    }
    private static SuspendedCandidateInfos checkedAw(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos, int _state) {
        if (_infos instanceof ArrCheckedExecOperationNodeInfos) {
            return stopArr(_context, _stackCall, _p, (ArrCheckedExecOperationNodeInfos) _infos);
        }
        if (_infos instanceof FieldCheckedExecOperationNodeInfos) {
            FieldCheckedExecOperationNodeInfos infWatch_ = (FieldCheckedExecOperationNodeInfos) _infos;
            if (!okField(_context,_stackCall, infWatch_)) {
                return null;
            }
            ClassField clField_ = infWatch_.getIdClass();
            WatchPoint bp_ = _context.getNotNullWatch(infWatch_.isTrueField(), infWatch_.getNbType(),clField_.getFieldName());
            BreakPointCondition condition_ = stopCurrentWpCondition(bp_, infWatch_.getModeField());
            return stopCurrent(_context, _stackCall, _p, condition_, new CheckedMethodInfos(infWatch_),StopDbgEnum.FIELD);
        }
        return defBp(_context, _stackCall, _state);
    }

    private static SuspendedCandidateInfos enterCase(ContextEl _context, GroupCheckedExecOperationNodeInfos _g, StackCall _stackCall, AbstractPageEl _p) {
        if (!okMethod(_context,_stackCall,_g.getInfosStd())) {
            return null;
        }
        return entryCaseTrue(_context, _g, _stackCall, _p);
    }

    private static SuspendedCandidateInfos entryCaseTrue(ContextEl _context, GroupCheckedExecOperationNodeInfos _g, StackCall _stackCall, AbstractPageEl _p) {
        StdMethodCheckedExecOperationNodeInfos i_ = _g.getInfosStd();
        StandardNamedFunction call_ = i_.getFct();
        ExecFormattedRootBlock glClass_ = i_.getDeclaring();
        Struct instance_ = i_.getInstance();
        ArgumentListCall original_ = i_.getArgs();
        StdMethodPointBlockPair pairs_ = _context.getPair(call_);
        if (pairs_ != null) {
            Parameters params_ = build(_context, pairs_, original_);
            MethodPoint mp_ = pairs_.getValue();
            StopDbgEnum step_;
            if (i_.isExiting()) {
                step_ = StopDbgEnum.METHOD_STD_EXIT;
            } else {
                step_ = StopDbgEnum.METHOD_STD_ENTRY;
            }
            SuspendedCandidateInfos enCaseTrue_ = stopCurrentMp(_context, _stackCall, _p, mp_, i_.isExiting(), new CheckedMethodInfos(glClass_, instance_, params_), step_);
            if (enCaseTrue_ != null) {
                return enCaseTrue_;
            }
        }
        return nextEntry(_context, _g, _stackCall, _p);
    }

    private static SuspendedCandidateInfos nextEntry(ContextEl _context, GroupCheckedExecOperationNodeInfos _g, StackCall _stackCall, AbstractPageEl _p) {
        CallCheckedExecOperationNodeInfos ref_ = _g.getCheckRefl();
        if (!_g.getInfosStd().isExiting()) {
            if (ref_ != null) {
                return enterCase(_context, _stackCall, _p, ref_, StopDbgEnum.METHOD_ABS_REF_ENTRY);
            }
            return stdCallee(_context, _g, _stackCall, _p);
        }
        if (ref_ != null) {
            return exitMethod(_context, _stackCall, _p, ref_, StopDbgEnum.METHOD_ABS_REF_EXIT);
        }
        return null;
    }

    private static SuspendedCandidateInfos stdCallee(ContextEl _context, GroupCheckedExecOperationNodeInfos _g, StackCall _stackCall, AbstractPageEl _p) {
        CoreCheckedExecOperationNodeInfos c_ = _g.getCheckReflAw();
        if (c_ != null) {
            return checkedMicro(_context, _stackCall, _p, c_, _g);
        }
        return null;
    }

    private static SuspendedCandidateInfos enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        ExecBlock call_ = call(_stackCall.getCallingState());
        ExecFormattedRootBlock glClass_ = globalClass(_stackCall.getCallingState());
        Struct instance_ = instance(_stackCall.getCallingState());
        Parameters original_ = params(_stackCall.getCallingState());
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, instance_,MethodPoint.BPC_ENTRY);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            SuspendedCandidateInfos s_ = stopCurrentMp(_context, _stackCall, _p, mp_, false, new CheckedMethodInfos(m.getValue(), instance_, params_), StopDbgEnum.METHOD_ENTRY);
            if (s_ != null) {
                return s_;
            }
        }
        return null;
    }

    private static SuspendedCandidateInfos enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CallCheckedExecOperationNodeInfos _c, StopDbgEnum _flag) {
        ExecNamedFunctionBlock call_ = _c.getPoly().getPair().getFct();
        ExecFormattedRootBlock glClass_ = _c.getFormattedRootBlock();
        Struct instance_ = _c.getInstance();
        Parameters original_ = ExecTemplates.quickArgsSet(call_,glClass_, _c.getCache(), _c.getArgs(),_context,_stackCall);
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, instance_,MethodPoint.BPC_ENTRY, original_);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            SuspendedCandidateInfos s_ = stopCurrentMp(_context, _stackCall, _p, mp_, false, new CheckedMethodInfos(m.getValue(), instance_, params_), _flag);
            if (s_ != null) {
                return s_;
            }
        }
        return null;
    }

    private static SuspendedCandidateInfos exitMethod(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (exiting(_stackCall)) {
            CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(_p.getBlockRoot(), _p.getGlobalClass(), _p.getGlobalStruct(), MethodPoint.BPC_EXIT);
            for (MethodPointBlockPairRootBlock m: pairs_) {
                Parameters params_ = build(_p.getRefParams(), _p.getCache(), _context, m.getId());
                MethodPoint mp_ = m.getId().getValue();
                SuspendedCandidateInfos s_ = stopCurrentMp(_context, _stackCall, _p, mp_, true, new CheckedMethodInfos(m.getValue(), _p.getGlobalStruct(), params_),StopDbgEnum.METHOD_EXIT);
                if (s_ != null) {
                    return s_;
                }
            }
        }
        return null;
    }

    private static SuspendedCandidateInfos exitMethod(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CallCheckedExecOperationNodeInfos _c, StopDbgEnum _step) {
        ExecNamedFunctionBlock call_ = _c.getPoly().getPair().getFct();
        ExecFormattedRootBlock glClass_ = _c.getFormattedRootBlock();
        Struct instance_ = _c.getInstance();
        Parameters original_ = ExecTemplates.quickArgsSet(call_,glClass_, _c.getCache(), _c.getArgs(), _context, _stackCall);
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, instance_, MethodPoint.BPC_EXIT, original_);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            SuspendedCandidateInfos s_ = stopCurrentMp(_context, _stackCall, _p, mp_, true, new CheckedMethodInfos(m.getValue(), instance_, params_), _step);
            if (s_ != null) {
                return s_;
            }
        }
        return null;
    }

    private static ExecBlock call(CallingState _c) {
        if (_c instanceof CustomFoundBlock) {
            return ((CustomFoundBlock)_c).getBlock();
        }
        if (_c instanceof CustomFoundConstructor) {
            return ((CustomFoundConstructor)_c).getPair().getFct();
        }
        if (_c instanceof CustomFoundMethod) {
            return ((CustomFoundMethod)_c).getPair().getFct();
        }
        if (_c instanceof CustomFoundSwitch) {
            return ((CustomFoundSwitch)_c).getSwitchMethod();
        }
        return null;
    }

    private static Struct instance(CallingState _c) {
        if (_c instanceof CustomFoundBlock) {
            return ArgumentListCall.toStr(((CustomFoundBlock)_c).getCurrentObject());
        }
        if (_c instanceof CustomFoundConstructor) {
            return ArgumentListCall.toStr(((CustomFoundConstructor)_c).getCurrentObject());
        }
        if (_c instanceof CustomFoundMethod) {
            return ArgumentListCall.toStr(((CustomFoundMethod)_c).getGl());
        }
        if (_c instanceof CustomFoundSwitch) {
            return ArgumentListCall.toStr(((CustomFoundSwitch)_c).getGl());
        }
        return NullStruct.NULL_VALUE;
    }

    private static ExecFormattedRootBlock globalClass(CallingState _c) {
        if (_c instanceof GlobalClassCallingState) {
            return ((GlobalClassCallingState)_c).getClassName();
        }
        return ExecFormattedRootBlock.defValue();
    }

    private static Parameters params(CallingState _c) {
        if (_c instanceof CustomFoundConstructor) {
            return ((CustomFoundConstructor)_c).getArguments();
        }
        if (_c instanceof CustomFoundMethod) {
            return ((CustomFoundMethod)_c).getArguments();
        }
        if (_c instanceof CustomFoundSwitch) {
            Parameters params_ = new Parameters();
            params_.setCache(((CustomFoundSwitch)_c).getCache());
            return params_;
        }
        return new Parameters();
    }

    private static Parameters build(ContextEl _conf, StdMethodPointBlockPair _id, ArgumentListCall _params) {
        CustList<String> ls_ = _id.getSm().names();
        StringMap<AbstractWrapper> pars_ = new StringMap<AbstractWrapper>();
        for (ArgumentWrapper a: _params.getArgumentWrappers()) {
            pars_.addEntry("",new VariableWrapper(LocalVariable.newLocalVariable(ArgumentListCall.toStr(a.getValue()),_conf)));
        }
        return build(pars_, null, ls_);
    }

    private static Parameters build(StringMap<AbstractWrapper> _params, Cache _cache, ContextEl _conf, MethodPointBlockPair _id) {
        CustList<String> ls_ = _id.getMp().names(_conf);
        return build(_params, _cache, ls_);
    }

    private static Parameters build(StringMap<AbstractWrapper> _params, Cache _cache, CustList<String> _ls) {
        int s_ = NumberUtil.min(_params.size(), _ls.size());
        Parameters params_ = new Parameters();
        for (int i = 0; i < s_; i++) {
            params_.getRefParameters().addEntry(_ls.get(i), _params.getValue(i));
        }
        params_.setCache(_cache);
        return params_;
    }

    private static StopDbgEnum stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str) {
        if (_str != null || _stackCall.normalCallNoExit(_context)) {
            return StopDbgEnum.NONE;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD && _stackCall.getReadWrite() != ReadWrite.ENTRY && _stackCall.nbPages() == 1) {
            return StopDbgEnum.STEP_RETURN_METHOD;
        }
        if (exiting(_stackCall)) {
            return StopDbgEnum.NONE;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() > _stackCall.nbPages()) {
            return StopDbgEnum.STEP_RETURN_METHOD;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && getCurrentOper(_p) != null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.CURSOR_EXPRESSION && stopTmp(_context, _p)) {
            return StopDbgEnum.STEP_CURSOR;
        }
        if (skipStepNotReturn(_stackCall, _p)) {
            return StopDbgEnum.NONE;
        }
        return stopStepFct(_context, _stackCall, _p);
    }

    private static StopDbgEnum stopStepFct(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_BLOCK && (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() == _stackCall.nbPages() && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbBlocks() > _p.nbBlock() || _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() > _stackCall.nbPages())) {
            return StopDbgEnum.STEP_NEXT_BLOCK;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_IN_METHOD && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() >= _stackCall.nbPages()) {
            return StopDbgEnum.STEP_NEXT_IN_METHOD;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_INSTRUCTION && okStack(_context, _stackCall)) {
            return StopDbgEnum.STEP_NEXT_INSTRUCTION;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.CURSOR_INSTRUCTION && stopTmp(_context, _p)) {
            return StopDbgEnum.STEP_CURSOR;
        }
        return StopDbgEnum.NONE;
    }

    private static boolean exiting(StackCall _stackCall) {
        return _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }

    private static boolean skipStepNotReturn(StackCall _stackCall, AbstractPageEl _p) {
        return _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null || getCurrentOper(_p) != null;
    }

    private static boolean stopTmp(ContextEl _context, AbstractPageEl _p) {
        for (int i : list(_p)) {
            if (_context.isTmp(_p.getFile(), i)) {
                return true;
            }
        }
        return false;
    }

    private static SuspendedCandidateInfos stopArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrCheckedExecOperationNodeInfos _arr) {
        String clName_ = _arr.getDeclaring().getFormatted();
        CheckedMethodInfos cm_ = new CheckedMethodInfos(_arr);
        SuspendedCandidateInfos exact_ = checkArr(_context, _stackCall, _p, _context.getNotNullArr(clName_, ExcPointBlockKey.SAME), cm_, _arr.getModeField());
        if (exact_ != null) {
            return exact_;
        }
        if (!clName_.isEmpty()) {
            SuspendedCandidateInfos inexact_ = checkArr(_context, _stackCall, _p, _context.getNotNullArr(StringExpUtil.getIdFromAllTypes(clName_), ExcPointBlockKey.SAME_FAMILY), cm_, _arr.getModeField());
            if (inexact_ != null) {
                return inexact_;
            }
        }
        for (CoreCheckedExecOperationNodeInfosPreference m: _context.getArr(_arr, _arr.getModeField())) {
            SuspendedCandidateInfos s_ = stopCurrent(_context, _stackCall, _p, m.getBreakPointCondition(), m.build(), StopDbgEnum.ARRAY);
            if (s_ != null) {
                return s_;
            }
        }
        return checkArr(_context, _stackCall, _p, _context.getNotNullArr("",ExcPointBlockKey.SAME_FAMILY),cm_, _arr.getModeField());
    }
    private static SuspendedCandidateInfos checkArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrPoint _bp, CheckedMethodInfos _arr, int _m) {
        BreakPointCondition bpc_ = stopArrValue(_bp, _m);
        return stopCurrent(_context, _stackCall, _p, bpc_, _arr, StopDbgEnum.ARRAY);
    }

    private static SuspendedCandidateInfos stopExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str) {
        String clName_ = _str.getClassName(_context);
        int m_ = stopExcMode(_p, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        CoreCheckedExecOperationNodeInfos core_ = new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(_str.getClassName(_context), _context.getClasses()), _str);
        CheckedMethodInfos cm_ = new CheckedMethodInfos(core_);
        SuspendedCandidateInfos exact_ = checkExc(_context, _stackCall, _p, _context.getNotNullExc(clName_, ExcPointBlockKey.SAME), cm_, m_);
        if (exact_ != null) {
            return exact_;
        }
        if (!clName_.isEmpty()) {
            SuspendedCandidateInfos inexact_ = checkExc(_context, _stackCall, _p, _context.getNotNullExc(StringExpUtil.getIdFromAllTypes(clName_), ExcPointBlockKey.SAME_FAMILY), cm_, m_);
            if (inexact_ != null) {
                return inexact_;
            }
        }
        for (CoreCheckedExecOperationNodeInfosPreference m: _context.getExc(core_, m_)) {
            SuspendedCandidateInfos s_ = stopCurrent(_context, _stackCall, _p, m.getBreakPointCondition(), m.build(), StopDbgEnum.EXCEPTION);
            if (s_ != null) {
                return s_;
            }
        }
        return checkExc(_context, _stackCall, _p, _context.getNotNullExc("",ExcPointBlockKey.SAME_FAMILY), cm_, m_);
    }

    private static SuspendedCandidateInfos stopPar(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, CoreCheckedExecOperationNodeInfos _arr) {
        String clName_ = _str.getClassName(_context);
        CheckedMethodInfos cm_ = new CheckedMethodInfos(_arr);
        SuspendedCandidateInfos exact_ = checkPar(_context, _stackCall, _p, _context.getNotNullPar(clName_, ExcPointBlockKey.SAME), cm_);
        if (exact_ != null) {
            return exact_;
        }
        if (!clName_.isEmpty()) {
            SuspendedCandidateInfos inexact_ = checkPar(_context, _stackCall, _p, _context.getNotNullPar(StringExpUtil.getIdFromAllTypes(clName_), ExcPointBlockKey.SAME_FAMILY), cm_);
            if (inexact_ != null) {
                return inexact_;
            }
        }
        for (CoreCheckedExecOperationNodeInfosPreference m: _context.getPar(_arr)) {
            SuspendedCandidateInfos s_ = stopCurrent(_context, _stackCall, _p, m.getBreakPointCondition(), m.build(), StopDbgEnum.PARENT);
            if (s_ != null) {
                return s_;
            }
        }
        return checkPar(_context, _stackCall, _p, _context.getNotNullPar("",ExcPointBlockKey.SAME_FAMILY),cm_);
    }

    private static SuspendedCandidateInfos checkExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ExcPoint _bp, CheckedMethodInfos _core, int _m) {
        BreakPointCondition bpc_ = stopExcValue(_bp, _m);
        return stopCurrent(_context, _stackCall, _p, bpc_, _core, StopDbgEnum.EXCEPTION);
    }

    private static SuspendedCandidateInfos checkPar(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ParPoint _bp, CheckedMethodInfos _arr) {
        BreakPointCondition bpc_ = stopParValue(_bp);
        return stopCurrent(_context, _stackCall, _p, bpc_, _arr, StopDbgEnum.PARENT);
    }

    private static SuspendedCandidateInfos checkOperNat(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, OperNatCheckedExecOperationNodeInfos _arr, BreakPointCondition _bpc) {
        return stopCurrent(_context, _stackCall, _p, _bpc, new CheckedMethodInfos(_arr),StopDbgEnum.OPER_NAT);
    }
    private static Struct stopExcValuRetThrowCatch(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null) {
            AbstractStask stLast_ = _p.tryGetLastStack();
            ExecBlock bl_ = _p.getBlock();
            if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
                Struct str_ = ((TryBlockStack) stLast_).getException();
                if (ExecHelperBlocks.firstMatch(_context, _stackCall, ((ExecAbstractCatchEval) bl_).getContent(), str_, ((ExecAbstractCatchEval) bl_).isCatchAll()) && _p.sizeEl() < 2) {
                    return str_;
                }
                return null;
            }
        }
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            return exc_.getStruct();
        }
        return null;
    }
    private static int stopExcMode(AbstractPageEl _p, AbstractPageEl _exiting) {
        if (_exiting == null) {
            AbstractStask stLast_ = _p.tryGetLastStack();
            ExecBlock bl_ = _p.getBlock();
            if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
                return ExcPoint.BPC_CAUGHT;
            }
            return ExcPoint.BPC_THROWN;
        }
        return ExcPoint.BPC_PROPAGATED;
    }
    private static BreakPointCondition stopExcValue(ExcPoint _ex, int _mode) {
        return _ex.result(_mode);
    }
    private static BreakPointCondition stopArrValue(ArrPoint _ex, int _m) {
        return _ex.result(_m);
    }

    private static BreakPointCondition stopParValue(ParPoint _ex) {
        return _ex.result();
    }

    private static BreakPointCondition stopOperNatValue(AbsOperNatPoint _ex, int _mode) {
        if (!_ex.isEnabled()) {
            return null;
        }
        return _ex.result(_mode);
    }

    private static SuspendedCandidateInfos stopCurrentBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPoint _bp) {
        BreakPointCondition condition_ = stopCurrentBpCondition(_bp);
        return stopCurrent(_context, _stackCall, _p, condition_, null, StopDbgEnum.INSTRUCTION);
    }

    private static SuspendedCandidateInfos stopCurrentTp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, TypePoint _bp) {
        BreakPointCondition condition_ = stopCurrentTpCondition(_p,_bp);
        return stopCurrent(_context, _stackCall, _p, condition_, null, StopDbgEnum.INSTRUCTION);
    }

    private static SuspendedCandidateInfos stopCurrentMp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, MethodPoint _bp, boolean _exit, CheckedMethodInfos _info, StopDbgEnum _step) {
        BreakPointCondition condition_ = stopCurrentMpCondition(_bp,_exit);
        return stopCurrent(_context, _stackCall, _p, condition_, _info, _step);
    }
    private static SuspendedCandidateInfos stopCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPointCondition _condition, CheckedMethodInfos _info, StopDbgEnum _step) {
        if (!okStack(_context, _stackCall, _condition)) {
            return null;
        }
        ResultContextLambda resLda_ = _condition.getResult();
        if (resLda_ != null && !condition(_context, _stackCall, _p, resLda_, _info, _condition.getStackErrLog().get(), _condition.getStackResErrLog().get())) {
            return null;
        }
        return postCondition(_p, _condition, _info,_step);
    }
    private static SuspendedCandidateInfos postCondition(AbstractPageEl _p, BreakPointCondition _condition, CheckedMethodInfos _info, StopDbgEnum _step) {
        if (countMatch(_condition)) {
            if (!_condition.getEnabled().get()) {
                return null;
            }
            for (BreakPointCondition o: _condition.getOthers().elts()) {
                if (_condition.getDisableAgain().get()) {
                    if (!o.getHit().getAndSet(false)) {
                        return null;
                    }
                } else if (!o.getHit().get()) {
                    return null;
                }
            }
            _condition.getHit().set(true);
            if (_condition.getDisableWhenHit().get()) {
                _condition.getEnabled().set(false);
            }
            return new SuspendedCandidateInfos(_info,_condition,_p, _step);
        }
        return null;
    }


    private static BreakPointCondition stopCurrentWpCondition(WatchPoint _bp, int _m) {
        return _bp.result(_m);
    }
    private static boolean countMatch(BreakPointCondition _cond) {
        int c_ = _cond.getCountModulo().get();
        if (c_ <= 0) {
            return true;
        }
        return NumberUtil.mod(_cond.incr(),c_) == 0;
    }
    private static boolean okStack(ContextEl _context, StackCall _stackCall, BreakPointCondition _bp) {
        if (_bp == null) {
            return false;
        }
        return okStack(0,_context, _stackCall, _bp.getExclude(), _bp.getInclude());
    }

    private static boolean okStack(ContextEl _context, StackCall _stackCall) {
        BreakPointBlockList l_ = _context.getBreakPointsBlock();
        int pr_ = _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages();
        int m_ = NumberUtil.max(0, pr_);
        AbsCollection<AbsCallContraints> cts_;
        if (pr_ < _stackCall.nbPages()) {
            cts_ = l_.getInclude();
        } else {
            cts_ = l_.getInclude().intercept().newExecFileBlockTraceIndexCollection();
        }
        return okStack(m_,_context,_stackCall, l_.getExclude(),cts_);
    }
    private static boolean okStack(int _f, ContextEl _context, StackCall _stackCall, AbsCollection<AbsCallContraints> _exc, AbsCollection<AbsCallContraints> _inc) {
        for (AbsCallContraints e: _exc.elts()) {
            if (existMatch(_f, _context, _stackCall, e)) {
                return false;
            }
        }
        for (AbsCallContraints e: _inc.elts()) {
            if (!existMatch(0, _context, _stackCall, e)) {
                return false;
            }
        }
        return true;
    }

    private static boolean existMatch(int _f, ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = _f; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return true;
            }
        }
        return false;
    }

    private static boolean condition(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ResultContextLambda _result, CheckedMethodInfos _info, boolean _errStack, boolean _errLogs) {
        StackCallReturnValue result_ = _result.eval(_info, _p);
        if (result_.getStack().getCallingState() != null) {
            if (_errStack) {
                for (String s: ResultContextLambda.trace(_stackCall,_context)) {
                    _stackCall.getStopper().getLogger().log(s);
                }
            }
            if (_errLogs) {
                for (String l: ResultContextLambda.traceView(result_.getStack(),_context)) {
                    _stackCall.getStopper().getLogger().log(l);
                }
            }
            _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setCallingStateSub(result_.getStack().getCallingState());
            return true;
        }
        return BooleanStruct.isTrue(ArgumentListCall.toStr(result_.getStack().aw().getValue()));
    }

    private static BreakPointCondition stopCurrentBpCondition(BreakPoint _bp) {
        if (!(_bp.isEnabled())) {
            return null;
        }
        return _bp.getResultStd();
    }

    private static BreakPointCondition stopCurrentTpCondition(AbstractPageEl _p, TypePoint _bp) {
        if (!(_bp.isEnabled() && (_bp.isInstanceType() && _p instanceof AbstractCallingInstancingPageEl || _bp.isStaticType() && _p instanceof StaticInitPageEl))) {
            return null;
        }
        if (_bp.isInstanceType()) {
            return _bp.getResultInstance();
        }
        return _bp.getResultStatic();
    }

    private static BreakPointCondition stopCurrentMpCondition(MethodPoint _bp, boolean _exit) {
        if (!_bp.isEnabled()) {
            return null;
        }
        if (!(_bp.isEntry() && !_exit || _bp.isExit() && _exit)) {
            return null;
        }
        if (_exit) {
            return _bp.getResultExit();
        }
        return _bp.getResultEntry();
    }
    private static int[] list(AbstractPageEl _p) {
        ExecBlock bl_ = _p.getBlock();
        AbstractStask st_ = _p.tryGetLastStack();
        if (st_ instanceof LoopBlockStack && simpleLoop(_p, bl_)) {
            if (!((LoopBlockStack) st_).getContent().hasNext()) {
                return NumberUtil.wrapIntArray(((ExecAbstractForEachLoop) bl_).getSeparator());
            }
            return NumberUtil.wrapIntArray(_p.getTraceIndex(), ((ExecAbstractForEachLoop) bl_).getSeparator());
        }
        if (_p.getLastLoopIfPossible(bl_) == null){
            if (bl_ instanceof ExecForEachIterable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachIterable) bl_).getIteratorOffset());
            }
            if (bl_ instanceof ExecForEachTable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachTable) bl_).getOffsets().getIteratorOffset());
            }
        }
        return NumberUtil.wrapIntArray(_p.getTraceIndex());
    }

    private static boolean checkBreakPoint(ContextEl _context, StackCall _stackCall, GroupCheckedExecOperationNodeInfos _g) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.trueException() != null || (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD) || _g.idDefined()) {
            return true;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            return false;
        }
        return checkBreakPointCurrent(_context, _stackCall, p_);
    }

    private static boolean checkBreakPointCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (enterExit(_context, _stackCall)) {
            return true;
        }
        return !_p.isEmptyEl();
    }

    private static boolean simpleLoop(AbstractPageEl _p, ExecBlock _bl) {
        return _bl instanceof ExecAbstractForEachLoop && !(_bl instanceof ExecForEachIterable) && ((ExecAbstractForEachLoop) _bl).getVariable().getOffset() == _p.getTraceIndex();
    }

    private static boolean okMethod(ContextEl _context, StackCall _stackCall, StdMethodCheckedExecOperationNodeInfos _check) {
        return ExecTemplates.checkParams(_context, _check.getOwn(),_check.getFct().id(), ArgumentListCall.toStr(_check.getInstance()), _check.getArgs().getArguments(), _stackCall) == null;
    }
    private static boolean okField(ContextEl _context, StackCall _stackCall, FieldCheckedExecOperationNodeInfos _check) {
        if (_check.getDeclaring() == null) {
            return false;
        }
        Struct v_ = strValue(_context, _check);
        if (v_ == null) {
            return false;
        }
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(_context.getClasses().getKeyWordValue(), 0, _check.getCache(), new StringMap<AbstractWrapper>());
        if (w_ == null) {
            return true;
        }
        String ret_;
        if (!_check.isStaticField()) {
            String arg_ = _check.getInstance().getClassName(_context);
            ret_ = ExecFieldTemplates.formatType(_context, _check.getFieldType().getRootBlock(), _check.getFieldType().getReturnType(), arg_);
        } else {
            if (_check.isCheckFinalField()) {
                return false;
            }
            ret_ = _check.getFieldType().getReturnType();
        }
        return ExecInheritsAdv.checkObjectEx(ret_, w_.getValue().getClassName(_context), _context, _stackCall) == null;
    }

    private static Struct strValue(ContextEl _context, FieldCheckedExecOperationNodeInfos _check) {
        if (_check.isStaticField()) {
            return NumParsers.getStaticField(_check.getIdClass(), _context.getClasses().getStaticFields());
        }
        Struct ins_ = _check.getInstance();
        if (!(ins_ instanceof FieldableStruct)) {
            return null;
        }
        ClassFieldStruct found_ = ((FieldableStruct) ins_).getEntryStruct(_check.getIdClass());
        if (found_ == null) {
            return null;
        }
        return found_.getStruct();
    }
    private static boolean enterExit(ContextEl _context, StackCall _stackCall) {
        return _stackCall.normalCallNoExit(_context) || _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }
}
