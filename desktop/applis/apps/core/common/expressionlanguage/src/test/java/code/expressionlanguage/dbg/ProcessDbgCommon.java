package code.expressionlanguage.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;

public abstract class ProcessDbgCommon extends ProcessMethodCommon {
    protected static final String CUST_ITER_PATH = "pkg/CustIter";
    protected static final String CUST_LIST_PATH = "pkg/CustList";
    protected static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    protected static final String CUST_PAIR_PATH = "pkg/CustPair";
    protected static final String CUST_TABLE_PATH = "pkg/CustTable";
    protected int now(StackCall _stack) {
        return _stack.getLastPage().getTraceIndex();
//        return _stack.getGlobalOffset();
    }
    protected int nowTrace(StackCall _stack) {
        return _stack.getLastPage().getTraceIndex();
    }
    protected static StackCall dbgNormal(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        return dbgNormalInit(_class, _method, _cont).getStack();
//        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
//        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
//        Argument argGlLoc_ = new Argument();
//        Parameters p_ = new Parameters();
//        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),null,new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_)).getStack();
    }
    protected static StackCall dbgNormalCheck(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        StackCallReturnValue st_ = dbgNormalInit(_class, _method, _cont);
        assertNull(st_.getStack().getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        return st_.getStack();
//        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
//        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
//        Argument argGlLoc_ = new Argument();
//        Parameters p_ = new Parameters();
//        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),null,new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_)).getStack();
    }
    protected static StackCall dbgNormal(String _class, MethodId _method, ResultContext _cont, StackCall _st) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        return dbgNormalInfoKeep(_class, _method, _cont, _st).getStack();
    }
    protected static StackCallReturnValue dbgNormalInit(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        return dbgNormalInfoInit(_class, _method, _cont);
    }
    protected static StackCallReturnValue dbgNormalInfoKeep(String _class, MethodId _method, ResultContext _cont, StackCall _st) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),_st,new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),StepDbgActionEnum.KEEP, false);
    }
    protected static StackCallReturnValue dbgNormalInfoInit(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),null,new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),StepDbgActionEnum.DEBUG, false);
    }
//    protected static StackCall dbgNormalAfterInit(String _class, MethodId _method, ResultContext _cont) {
//        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
//        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
//        Argument argGlLoc_ = new Argument();
//        Parameters p_ = new Parameters();
//        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont.getContext());
//        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont.getContext(), stackCall_);
//        return stackCall_;
//    }

    protected static StackCall dbgContinueNormal(StackCall _stack, ContextEl _cont) {
        return dbgContinueNormalValue(_stack, _cont).getStack();
    }

    protected static StackCall dbgContinueNormalCheck(StackCall _stack, ContextEl _cont) {
        StackCallReturnValue st_ = dbgContinueNormalValue(_stack, _cont);
        assertNull(st_.getStack().getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub());
        return st_.getStack();
    }

    protected static StackCall dbgContinueNormalMute(StackCall _stack, ContextEl _cont) {
        return dbgContinueNormalValueMute(_stack, _cont).getStack();
    }


    protected static StackCall dbgContinueNormalQuick(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont, new Options(), _stack, null,StepDbgActionEnum.KEEP, false).getStack();
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValueMute(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, true);
    }

    protected static StackCall dbgContinueNormalValueNextInst(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.NEXT_INSTRUCTION, false).getStack();
    }

    protected static StackCall dbgContinueNormalValueNextInstMethod(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.NEXT_IN_METHOD, false).getStack();
    }
    protected static StackCall dbgContinueNormalValueStepRet(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.RETURN_METHOD, false).getStack();
    }

    protected static StackCall dbgContinueNormalValueStepBlock(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.NEXT_BLOCK, false).getStack();
    }
    protected static StackCall dbgContinueNormalValueCursorInstruction(StackCall _stack, ResultContext _cont, int _caret) {
        cursor(_stack, _cont, _caret);
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),null,_stack,null,StepDbgActionEnum.CURSOR_INSTRUCTION, false).getStack();
    }
    protected static StackCall dbgContinueNormalValueCursorExpression(StackCall _stack, ResultContext _cont, int _caret) {
        int off_ = ResultExpressionOperationNode.beginPartExp(_caret, _cont.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"));
        assertEq(off_,_caret);
        cursor(_stack, _cont, off_);
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),null,_stack,null,StepDbgActionEnum.CURSOR_EXPRESSION, false).getStack();
    }

    protected static void cursor(StackCall _stack, ResultContext _cont, int _caret) {
        int index_ = new IdList<ExecFileBlock>(_cont.getFiles().values()).indexOfObj(_stack.getLastPage().getFile());
        int nf_ = _cont.getFiles().getKey(index_).getNumberFile();
        _cont.getContext().tmpList().add(new BreakPointBlockKey(_stack.getLastPage().getFile(), nf_, _caret));
    }
    protected static ResultContext ctxLgReadOnlyOkQuick(StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();
        KeyWords kwl_ = en(lgName_);
        ResultContext res_ = validateAndRetWithoutInit(opt_, lgName_, kwl_, _files, new StringMap<String>(), new DbgStackStopper(new DefLogDbg()));
        res_.getContext().setExiting(new NoExiting());
        return res_;
//        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
//        Forwards forwards_ = getForwards(opt_,lgName_,kwl_,page_);
//        AnalyzedPageEl a_ = validateWithoutInit(_files, page_);
//        assertTrue( isEmptyErrors(a_));
//        generalForward(a_, forwards_);
//        ContextEl ctx_ = forwardAndClear(forwards_);
//        ResultContext res_ = new ResultContext(a_, forwards_, a_.getMessages());
//        res_.setContext(ctx_);
//        return res_;
    }
    public static StackCall tryInitStaticlyTypes(ContextEl _context, Options _options) {
        return tryInitStaticlyTypes(null, _options, _context);
    }
    public static StackCall tryInitStaticlyTypes(StackCall _original, Options _options, ContextEl _context) {
        return ExecClassesUtil.tryInitStaticlyTypes(_context, _options, _original, false);
    }
    protected static int toInt(Struct _str) {
        return ((NumberStruct)_str).intStruct();
    }
    protected static Struct valueDbg(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = resCtx(_class, _caret, _files);
        return end(_dyn, _class, _meth, res_);
    }

    protected static ResultContext resCtx(String _class, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        return res_;
    }

    protected ArrayStruct valueDbgExc(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),_caret);
        return endExc(_dyn, _class, _meth, res_);
    }
    protected Struct valueDbg(String _dyn, String _class, String _meth, String _file, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        res_.toggleBreakPoint(_file,_caret);
        return end(_dyn, _class, _meth, res_);
    }

    protected Struct valueDbgStatic(String _dyn, String _class, String _meth, StringMap<String> _files, String... _types) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files, _types);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        return end(_dyn,_class,_meth,res_);
    }

    protected Struct valueDbgInst(String _dyn, String _class, String _meth, StringMap<String> _files, String... _types) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files, _types);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        return end(_dyn,_class,_meth,res_);
    }

    protected ReportedMessages valueDbgKo(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        return endKo(_dyn,_class,_meth,res_);
    }

    protected ReportedMessages valueDbgKoStType(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        return endKoSt(_dyn,_class,_meth,res_);
    }

    protected StackCall conditionalSt(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxSt(_class, _files);
        return conditionalSt(_dyn, _class, _meth, res_);
    }

    protected StackCall countSt(int _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxSt(_class, _files);
        return countSt(_dyn, _class, _meth, res_);
    }

    protected StackCall conditionalSt(String _dyn, String _class, String _meth, ResultContext _res) {
        return conditionalStView(_dyn, _class, _meth, _res).getStack();
    }

    protected StackCall countSt(int _dyn, String _class, String _meth, ResultContext _res) {
        return countStView(_dyn, _class, _meth, _res).getStack();
    }

    protected StackCallReturnValue conditionalStView(String _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        pair_.getValue().getResultStatic().analyze(pair_,_dyn,"", "", _res,new DefContextGenerator());
        assertTrue(pair_.getValue().getResultStatic().lda().getReportedMessages().isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultStatic().getResultStr());
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null, false);
    }

    protected StackCallReturnValue countStView(int _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        pair_.getValue().getResultStatic().getCountModulo().set(_dyn);
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null, false);
    }

    protected ResultContext ctxSt(String _class, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        return res_;
    }

    protected StackCall conditionalInst(String _dyn, String _class, String _meth, StringMap<String> _files) {
        return conditionalInstView(_dyn, _class, _meth, _files).getStack();
    }

    protected StackCall countInst(int _dyn, String _class, String _meth, StringMap<String> _files) {
        return countInstView(_dyn, _class, _meth, _files).getStack();
    }

    protected StackCallReturnValue conditionalInstView(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxInst(_class, _files);
        return conditionalInstView(_dyn, _class, _meth, res_);
    }

    protected StackCallReturnValue countInstView(int _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxInst(_class, _files);
        return countInstView(_dyn, _class, _meth, res_);
    }

    protected StackCallReturnValue conditionalInstView(String _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        pair_.getValue().getResultInstance().analyze(pair_,_dyn,"", "", _res,new DefContextGenerator());
        assertTrue(pair_.getValue().getResultInstance().lda().getReportedMessages().isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultInstance().getResultStr());
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null, false);
    }

    protected StackCallReturnValue countInstView(int _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        pair_.getValue().getResultInstance().getCountModulo().set(_dyn);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null, false);
    }
    protected StackCallReturnValue disableHitStdView(String _class, String _meth, int _caret, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().getEnabled().set(true);
        pair_.getValue().getResultStd().getDisableWhenHit().set(true);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null, false);
    }
    protected StackCallReturnValue disableHitUntilStdView(String _class, String _meth, int _caret, int _depend, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _depend);
        BreakPointBlockPair pairDep_ = _res.getPair(classBody_.getFile(), _depend);
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().getHit().set(false);
        pairDep_.getValue().getResultStd().getHit().set(false);
        CustList<BreakPointCondition> g_ = new CustList<BreakPointCondition>();
        g_.add(pairDep_.getValue().getResultStd());
        pair_.getValue().getResultStd().setAll(g_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null, false);
    }

    protected CustList<String> disableHitUntilStdViewLog(String _class, String _meth, int _caret, int _depend, String _logDep, String _log, ResultContext _res, boolean _dis) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _depend);
        BreakPointBlockPair pairDep_ = _res.getPair(classBody_.getFile(), _depend);
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        pairDep_.getValue().getResultStd().getSuspend().set(false);
        pair_.getValue().getResultStd().getSuspend().set(false);
        pairDep_.getValue().getResultStd().analyze(pairDep_,"",_logDep, "", _res,new DefContextGenerator());
        pair_.getValue().getResultStd().analyze(pair_,"",_log, "", _res,new DefContextGenerator());
        pair_.getValue().getResultStd().getHit().set(false);
        pairDep_.getValue().getResultStd().getHit().set(false);
        pair_.getValue().getResultStd().getDisableAgain().set(_dis);
        CustList<BreakPointCondition> g_ = new CustList<BreakPointCondition>();
        g_.add(pairDep_.getValue().getResultStd());
        pair_.getValue().getResultStd().setAll(g_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        StackCallReturnValue st_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
        return ((DefLogDbg)st_.getStack().getStopper().getLogger()).getList();
    }
    protected CustomFoundMethod state(ResultContext _res, String _class, String _meth) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Parameters p_ = new Parameters();
        return new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_);
    }

    protected ResultContext ctxInst(String _class, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol());
        updateInstType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),true);
        updateStaType(res_, ana_.getFile().getFileName(),ana_.getIdRowCol(),false);
        return res_;
    }
    protected static void updateInstType(ResultContext _res,String _file, int _caret, boolean _newValue) {
        TypePointBlockPair type_ = _res.getPairType(_res.getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_file)), _caret);
        type_.getValue().setInstanceType(_newValue);
    }
    protected static void updateStaType(ResultContext _res,String _file, int _caret, boolean _newValue) {
        TypePointBlockPair type_ = _res.getPairType(_res.getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_file)), _caret);
        type_.getValue().setStaticType(_newValue);
    }

    protected StackCall conditionalStd(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        return conditionalStdView(_dyn, _class, _meth, _caret, _files).getStack();
    }

    protected StackCall conditionalStdLogsErr(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        ExecRootBlock classBody_ = res_.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = res_.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().getStackErrLog().set(true);
        pair_.getValue().getResultStd().getStackResErrLog().set(true);
        pair_.getValue().getResultStd().analyze(pair_,_dyn,"", "", res_,new DefContextGenerator());
        assertTrue(pair_.getValue().getResultStd().lda().getReportedMessages().isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultStd().getResultStr());
        CustomFoundMethod state_ = state(res_, _class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getPageEl().getOptions(), null, state_, null, false).getStack();
    }

    protected StackCall countStd(int _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        return countStdView(_dyn, _class, _meth, _caret, _files).getStack();
    }
    protected StackCallReturnValue conditionalStdView(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return conditionalStdView(_dyn, _class, _meth, _caret, _files, res_);
    }
    protected StackCallReturnValue countStdView(int _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return countStdView(_dyn, _class, _meth, _caret, _files, res_);
    }
    protected ResultContext ctxStd(String _class, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        return res_;
    }

    protected CustList<String> conditionalStdViewLogs(String _exp, String _log, String _file, int _caret, String _class, String _meth, StringMap<String> _files, boolean _st, int _mod, boolean _d, boolean _en) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        res_.toggleBreakPoint(_file, _caret);
        BreakPointBlockPair pair_ = res_.getPair(res_.getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal(_file)), _caret);
        BreakPointCondition std_ = pair_.getValue().getResultStd();
        std_.getSuspend().set(false);
        std_.getEnabled().set(_en);
        std_.getStackLog().set(_st);
        std_.getCountModulo().set(_mod);
        std_.getDisableWhenHit().set(_d);
        std_.analyze(pair_,_exp,_log, "", res_,new DefContextGenerator());
        assertEq(_exp, std_.getResultStr());
        assertEq(_log, std_.getLogsStr());
        CustomFoundMethod state_ = state(res_,_class, _meth);
        StackCallReturnValue v_ = ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getPageEl().getOptions(), null, state_, null, false);
        return ((DefLogDbg) v_.getStack().getStopper().getLogger()).getList();
    }

    protected CustList<String> conditionalStdViewLogs(String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        CustomFoundMethod state_ = state(res_,_class, _meth);
        StackCallReturnValue v_ = ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getPageEl().getOptions(), null, state_, null, false);
        return ((DefLogDbg) v_.getStack().getStopper().getLogger()).getList();
    }
    protected BreakPointOutputInfo conditionalStdViewLogsWacthes(String _exp, String _watch, String _file, int _caret, String _class, String _meth, StringMap<String> _files, boolean _st, int _mod, boolean _d, boolean _en) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        res_.toggleBreakPoint(_file, _caret);
        BreakPointBlockPair pair_ = res_.getPair(res_.getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal(_file)), _caret);
        BreakPointCondition std_ = pair_.getValue().getResultStd();
        std_.getSuspend().set(true);
        std_.getEnabled().set(_en);
        std_.getStackLog().set(_st);
        std_.getCountModulo().set(_mod);
        std_.getDisableWhenHit().set(_d);
        std_.analyze(pair_,_exp,"", _watch, res_,new DefContextGenerator());
        assertEq(_exp, std_.getResultStr());
        assertEq(_watch, std_.getWatchesStr());
        CustomFoundMethod state_ = state(res_,_class, _meth);
        StackCallReturnValue v_ = ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getPageEl().getOptions(), null, state_, null, false);
        return v_.getStack().getBreakPointInfo().getBreakPointOutputInfo();
    }
    protected StackCallReturnValue conditionalStdView(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files,ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_dyn,"", "", _res,new DefContextGenerator());
        assertTrue(pair_.getValue().getResultStd().lda().getReportedMessages().isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultStd().getResultStr());
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCallReturnValue countStdView(int _dyn, String _class, String _meth, int _caret, StringMap<String> _files,ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().getCountModulo().set(_dyn);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCallReturnValue stdViewMute(String _class, String _meth, int _first, int _second, ResultContext _res, StepDbgActionEnum _step, boolean _mute) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _first);
        _res.toggleBreakPoint(ana_.getFile().getFileName(), _second);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, _step, _mute);
    }

    protected StackCallReturnValue stdViewMuteContinue(StackCallReturnValue _previous, ResultContext _res, StepDbgActionEnum _step, boolean _mute) {
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), _previous.getStack(), null, _step, _mute);
    }
    protected StackCallReturnValue stackStdView(String _class, String _meth, int _caret, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return stackStdView(_class, _meth, _caret, res_, _names, _carets);
    }
    protected StackCallReturnValue stackStdView(String _class, String _meth, int _caret, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        AbsCallContraints inc_ = fileBlockTraceIndex(_res, _names[0], _carets[0]);
        AbsCallContraints exc_ = fileBlockTraceIndex(_res, _names[1], _carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStd().getInclude(),incList_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStd().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCallReturnValue stackStdViewCall(String _class, String _meth, int _caret, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return stackStdViewCall(_class, _meth, _caret, res_, _names, _carets);
    }
    protected StackCallReturnValue stackStdViewCall(String _class, String _meth, int _caret, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getPair(classBody_.getFile(), _caret);
        AbsCallContraints inc_ = execFileBlockFct(_res, _carets[0], _names[0]);
        AbsCallContraints exc_ = execFileBlockFct(_res, _carets[1], _names[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStd().getInclude(),incList_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStd().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCall stackSta(String _class, String _meth, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxSt(_class, _files);
        return stackStaView(_class, _meth, res_, _names, _carets).getStack();
    }
    protected StackCallReturnValue stackStaView(String _class, String _meth, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        AbsCallContraints inc_ = fileBlockTraceIndex(_res, _names[0], _carets[0]);
        AbsCallContraints exc_ = fileBlockTraceIndex(_res, _names[1], _carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStatic().getInclude(),incList_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultStatic().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCall stackIns(String _class, String _meth, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxInst(_class, _files);
        return stackInsView(_class, _meth, res_, _names, _carets).getStack();
    }

    protected StackCallReturnValue stackInsView(String _class, String _meth, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        TypePointBlockPair pair_ = _res.getPairType(classBody_.getFile(), ana_.getIdRowCol());
        AbsCallContraints inc_ = fileBlockTraceIndex(_res, _names[0], _carets[0]);
        AbsCallContraints exc_ = fileBlockTraceIndex(_res, _names[1], _carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultInstance().getInclude(),incList_);
        ExecFileBlockTraceIndex.setAll(pair_.getValue().getResultInstance().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCallReturnValue stackInsViewStepExc(String _class, String _meth, String _excFile, int _excCaret, ResultContext _res) {
        AbsCallContraints excOne_ = execFileBlockFct(_res, _excCaret, _excFile);
        RootBlock ana_ = _res.getPageEl().getAnaClassBody("pkg.Ex0");
        AbsCallContraints excTwo_ = fileBlockTraceIndex(_res, ana_.getFile().getFileName(), ana_.getIdRowCol());
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(excOne_);
        excList_.add(excTwo_);
        ExecFileBlockTraceIndex.setAll(_res.getBreakPointsBlock().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res, _class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected StackCallReturnValue stackInsViewStepInc(String _class, String _meth, String _excFile, int _excCaret, ResultContext _res) {
        AbsCallContraints inc_ = execFileBlockFct(_res, _excCaret, _excFile);
        RootBlock ana_ = _res.getPageEl().getAnaClassBody("pkg.Ex0");
        AbsCallContraints exc_ = fileBlockTraceIndex(_res, ana_.getFile().getFileName(), ana_.getIdRowCol());
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        excList_.add(exc_);
        ExecFileBlockTraceIndex.setAll(_res.getBreakPointsBlock().getInclude(),incList_);
        ExecFileBlockTraceIndex.setAll(_res.getBreakPointsBlock().getExclude(),excList_);
        CustomFoundMethod state_ = state(_res, _class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null, false);
    }

    protected ExecFileBlockTraceIndex fileBlockTraceIndex(ResultContext _res, String _name, int _caret) {
        return new ExecFileBlockTraceIndex(_res.getFiles().getVal(file(_res, _name)), FileBlock.number(file(_res, _name)), _caret);
    }

    private FileBlock file(ResultContext _res, String _name) {
        return _res.getPageEl().getPreviousFilesBodies().getVal(_name);
    }

    protected ReportedMessages conditionalStdViewBad(String _dyn, String _class, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(), _caret);
        ExecRootBlock classBody_ = res_.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = res_.getPair(classBody_.getFile(), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_dyn,"", "", res_,new DefContextGenerator());
        return pair_.getValue().getResultStd().lda().getReportedMessages();
    }
    protected ReportedMessages valueDbgKo(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = firstAna(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.toggleBreakPoint(ana_.getFile().getFileName(),_caret);
        return endKo(_dyn,_class,_meth,res_);
    }

    protected ResultContext firstAna(StringMap<String> _files) {
        return ctxLgReadOnlyOkQuick(_files);
    }

    protected static Struct end(String _dyn, String _class, String _meth, ResultContext _res) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return WatchResults.dynamicAnalyze(_dyn,_res,new DefContextGenerator(),page_).getWatchedObject();
    }

    protected static Struct endOper(String _dyn, String _class, String _meth, ResultContext _res) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, null);
        StackCall st_ = stVal_.getStack();
        return WatchResults.dynamicAnalyze(_dyn,st_, _res, new DefContextGenerator()).getWatchedObject();
    }

    protected ResultContextLambda dynAna(String _dyn, String _class, int _caret, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        _res.toggleBreakPoint(ana_.getFile().getFileName(),_caret);
        return ResultContextLambda.dynamicAnalyze(_dyn, _res.getPair(_res.getFiles().getVal(ana_.getFile()), _caret), _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(),BreakPoint.BPC_STD);
    }

    protected Struct eval(ResultContextLambda _dyn, AbstractPageEl _page) {
        return _dyn.eval(null, _page).getStack().aw().getValue().getStruct();
    }

    protected AbstractPageEl goToBp(ResultContext _res, String _class, String _meth, StackCall _stack) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, _stack);
        StackCall st_ = stVal_.getStack();
        return st_.getLastPage();
    }

    protected static StackCallReturnValue goLoop(ResultContext _res, String _class, String _meth, StackCall _stack) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), _stack, new CustomFoundMethod(ArgumentListCall.toStr(NullStruct.NULL_VALUE), new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null, false);
        return stVal_;
    }

    private ArrayStruct endExc(String _dyn, String _class, String _meth, ResultContext _res) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return (ArrayStruct)WatchResults.dynamicAnalyze(_dyn,_res,new DefContextGenerator(),page_).getWatchedTrace();
    }
    private ReportedMessages endKo(String _dyn, String _class, String _meth, ResultContext _res) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return WatchResults.dynamicAnalyze(_dyn,_res,new DefContextGenerator(),page_).getReportedMessages();
    }
    private ReportedMessages endKoSt(String _dyn, String _class, String _meth, ResultContext _res) {
        StackCallReturnValue stVal_ = goLoop(_res, _class, _meth, null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return WatchResults.dynamicAnalyze(_dyn,_res,new DefContextGenerator(),page_).getReportedMessages();
    }
    protected AnalyzedPageEl scope(StringMap<String> _files, String _fileName, int _caret, String... _types) {
        ResultContext res_ = ctxLgReadOnlyOkQuick(_files, _types);
        return ResultExpressionOperationNode.prepare(_fileName,_caret,res_.getPageEl(),BreakPoint.BPC_STD);
    }

    protected ExecFileBlockFct execFileBlockFct(ResultContext _res, int _caret, String _name) {
        return new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFctKey(_caret, file(_res, _name)),ResultExpressionOperationNode.beginPartFct(_caret, file(_res, _name), _res.getPageEl().getDisplayedStrings()));
    }

    protected static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustList<U> :Iterable<U>{\n");
        xml_.append(" private U[] list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" public (){\n");
        xml_.append("  list=new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" public void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" public void add(int index,U elt){\n");
        xml_.append("  U[] newlist=new U[length+1i];\n");
        xml_.append("  iter(int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  iter(int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" public int size(){\n");
        xml_.append("  return length;\n");
        xml_.append(" }\n");
        xml_.append(" public U get(int index){\n");
        xml_.append("  return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" public void set(int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" public void remove(int index){\n");
        xml_.append("  iter(int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" public Iterator<U> iterator(){\n");
        xml_.append("  return new pkg.CustIter<U>(this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustIter<T> :Iterator<T>{\n");
        xml_.append(" private pkg.CustList<T> list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" private int index;\n");
        xml_.append(" public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean hasNext(){\n");
        xml_.append("  return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustPair<U,V> :Pair<U,V>{\n");
        xml_.append(" private U first;\n");
        xml_.append(" private V second;\n");
        xml_.append(" public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair(U f,V s){\n");
        xml_.append("  first = f;\n");
        xml_.append("  second = s;\n");
        xml_.append(" }\n");
        xml_.append(" public U getFirst(){\n");
        xml_.append("  return first;\n");
        xml_.append(" }\n");
        xml_.append(" public V getSecond(){\n");
        xml_.append("  return second;\n");
        xml_.append(" }\n");
        xml_.append(" public void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustTable<U,V> :IterableTable<U,V>{\n");
        xml_.append(" private CustList<CustPair<U,V>> list;\n");
        xml_.append(" public (){\n");
        xml_.append("  list=new CustList<CustPair<U,V>>();\n");
        xml_.append(" }\n");
        xml_.append(" public void add(U f,V s){\n");
        xml_.append("  list.add(new CustPair<U,V>(f,s));\n");
        xml_.append(" }\n");
        xml_.append(" public void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p);\n");
        xml_.append(" }\n");
        xml_.append(" public int size(){\n");
        xml_.append("  return list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair<U,V> get(int index){\n");
        xml_.append("  return list.get(index);\n");
        xml_.append(" }\n");
        xml_.append(" public IteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  return new CustIterTable<U,V>(this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustIterTable<U,V> :IteratorTable<U,V>{\n");
        xml_.append(" private CustTable<U,V> list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" private int index;\n");
        xml_.append(" public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean hasNextPair(){\n");
        xml_.append("  return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
