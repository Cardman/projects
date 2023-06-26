package code.expressionlanguage.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public abstract class ProcessDbgCommon extends ProcessMethodCommon {
    protected static final String CUST_ITER_PATH = "pkg/CustIter";
    protected static final String CUST_LIST_PATH = "pkg/CustList";
    protected static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    protected static final String CUST_PAIR_PATH = "pkg/CustPair";
    protected static final String CUST_TABLE_PATH = "pkg/CustTable";
    protected int now(StackCall _stack) {
        return _stack.getGlobalOffset();
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
        return dbgNormalInit(_class, _method, _cont);
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
    protected static StackCall dbgNormalInit(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        return dbgNormalInfoInit(_class, _method, _cont).getStack();
    }
    protected static StackCallReturnValue dbgNormalInfoKeep(String _class, MethodId _method, ResultContext _cont, StackCall _st) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),_st,new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),StepDbgActionEnum.KEEP);
    }
    protected static StackCallReturnValue dbgNormalInfoInit(String _class, MethodId _method, ResultContext _cont) {
//        tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
//        return dbgNormalAfterInit(_class, _method, _cont);
//    }
//
//    protected static StackCall dbgNormalAfterInitGene(String _class, MethodId _method, ResultContext _cont) {
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(),_cont.getForwards().getOptions(),null,new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),StepDbgActionEnum.DEBUG);
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


    protected static StackCall dbgContinueNormalQuick(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont, new Options(), _stack, null,StepDbgActionEnum.KEEP).getStack();
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP);
    }

    protected static StackCall dbgContinueNormalValueNextInst(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.NEXT_INSTRUCTION).getStack();
    }

    protected static StackCall dbgContinueNormalValueNextInstMethod(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.NEXT_IN_METHOD).getStack();
    }
    protected static StackCall dbgContinueNormalValueStepRet(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.RETURN_METHOD).getStack();
    }

    protected static StackCall dbgContinueNormalValueCursor(StackCall _stack, ContextEl _cont, int _caret) {
        _cont.getClasses().getDebugMapping().getBreakPointsBlock().getListTmp().add(new BreakPointBlockPair(_stack.getLastPage().getFile(), _caret, new BreakPoint(_cont.getCaller())));
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.CURSOR).getStack();
    }
    protected static ResultContext ctxLgReadOnlyOkQuick(String _lg, StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setDebugging(false);
        opt_.setDebugging(true);
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);
        return validateAndRetWithoutInit(opt_,lgName_,kwl_,_files,new StringMap<String>());
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
        return ExecClassesUtil.tryInitStaticlyTypes(_context, _options, _original);
    }
    protected static int toInt(Struct _str) {
        return ((NumberStruct)_str).intStruct();
    }
    protected Struct valueDbg(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),_caret,res_);
        return end(_dyn, _class, _meth, res_);
    }
    protected ArrayStruct valueDbgExc(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),_caret,res_);
        return endExc(_dyn, _class, _meth, res_);
    }
    protected Struct valueDbg(String _dyn, String _class, String _meth, String _file, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(_file,_caret,res_);
        return end(_dyn, _class, _meth, res_);
    }

    protected Struct valueDbg(String _dyn, String _class, String _meth, StringMap<String> _files, String... _types) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files, _types);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,false);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,true);
        return end(_dyn,_class,_meth,res_);
    }

    protected ReportedMessages valueDbgKo(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,false);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,true);
        return endKo(_dyn,_class,_meth,res_);
    }

    protected ReportedMessages valueDbgKoStType(String _dyn, String _class, String _meth, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,false);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,true);
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
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        assertTrue(BreakPointBlockList.breakPointCtxStatic(pair_, _res,new DefContextGenerator(), _dyn).isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultStatic().getResultStr());
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
    }

    protected StackCallReturnValue countStView(int _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        BreakPointBlockList.breakPointCountStatic(pair_, _dyn);
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
    }

    protected ResultContext ctxSt(String _class, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,false);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,true);
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
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        assertTrue(BreakPointBlockList.breakPointCtxInstance(pair_, _res,new DefContextGenerator(), _dyn).isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultInstance().getResultStr());
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null);
    }

    protected StackCallReturnValue countInstView(int _dyn, String _class, String _meth, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        BreakPointBlockList.breakPointCountInstance(pair_, _dyn);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_,null);
    }
    protected CustomFoundMethod state(ResultContext _res, String _class, String _meth) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        return new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_);
    }

    protected ResultContext ctxInst(String _class, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,true);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType(ana_.getFile().getFileName(),ana_.getIdRowCol(),res_,false);
        return res_;
    }

    protected StackCall conditionalStd(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        return conditionalStdView(_dyn, _class, _meth, _caret, _files).getStack();
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
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(), _caret,res_);
        return res_;
    }

    protected StackCallReturnValue conditionalStdView(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files,ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), _caret);
        assertTrue(BreakPointBlockList.breakPointCtxStd(pair_, _res,new DefContextGenerator(), _dyn).isAllEmptyErrors());
        assertEq(_dyn,pair_.getValue().getResultStd().getResultStr());
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }

    protected StackCallReturnValue countStdView(int _dyn, String _class, String _meth, int _caret, StringMap<String> _files,ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), _caret);
        BreakPointBlockList.breakPointCountStd(pair_, _dyn);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }

    protected StackCallReturnValue stackStdView(String _class, String _meth, int _caret, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return stackStdView(_class, _meth, _caret, res_, _names, _carets);
    }
    protected StackCallReturnValue stackStdView(String _class, String _meth, int _caret, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), _caret);
        AbsCallContraints inc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[0])),_carets[0]);
        AbsCallContraints exc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[1])),_carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        BreakPointBlockList.breakPointFileIndexUpdaterIncludeStd(pair_, incList_);
        BreakPointBlockList.breakPointFileIndexUpdaterExcludeStd(pair_, excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }

    protected StackCallReturnValue stackStdViewCall(String _class, String _meth, int _caret, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxStd(_class, _caret, _files);
        return stackStdViewCall(_class, _meth, _caret, res_, _names, _carets);
    }
    protected StackCallReturnValue stackStdViewCall(String _class, String _meth, int _caret, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), _caret);
        AbsCallContraints inc_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(_carets[0],_res.getPageEl().getPreviousFilesBodies().getVal(_names[0]),_res.getPageEl().getDisplayedStrings()));
        AbsCallContraints exc_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(_carets[1],_res.getPageEl().getPreviousFilesBodies().getVal(_names[1]),_res.getPageEl().getDisplayedStrings()));
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        BreakPointBlockList.breakPointFileIndexUpdaterIncludeStd(pair_, incList_);
        BreakPointBlockList.breakPointFileIndexUpdaterExcludeStd(pair_, excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }

    protected StackCall stackSta(String _class, String _meth, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxSt(_class, _files);
        return stackStaView(_class, _meth, res_, _names, _carets).getStack();
    }
    protected StackCallReturnValue stackStaView(String _class, String _meth, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        AbsCallContraints inc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[0])),_carets[0]);
        AbsCallContraints exc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[1])),_carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        BreakPointBlockList.breakPointFileIndexUpdaterIncludeStatic(pair_, incList_);
        BreakPointBlockList.breakPointFileIndexUpdaterExcludeStatic(pair_, excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }

    protected StackCall stackIns(String _class, String _meth, StringMap<String> _files, String[] _names, int[] _carets) {
        ResultContext res_ = ctxInst(_class, _files);
        return stackInsView(_class, _meth, res_, _names, _carets).getStack();
    }

    protected StackCallReturnValue stackInsView(String _class, String _meth, ResultContext _res, String[] _names, int[] _carets) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        BreakPointBlockPair pair_ = _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), ana_.getIdRowCol());
        AbsCallContraints inc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[0])),_carets[0]);
        AbsCallContraints exc_ = new ExecFileBlockTraceIndex(_res.getForwards().dbg().getFiles().getVal(_res.getPageEl().getPreviousFilesBodies().getVal(_names[1])),_carets[1]);
        CustList<AbsCallContraints> incList_ = new CustList<AbsCallContraints>();
        incList_.add(inc_);
        CustList<AbsCallContraints> excList_ = new CustList<AbsCallContraints>();
        excList_.add(exc_);
        BreakPointBlockList.breakPointFileIndexUpdaterIncludeInstance(pair_, incList_);
        BreakPointBlockList.breakPointFileIndexUpdaterExcludeInstance(pair_, excList_);
        CustomFoundMethod state_ = state(_res,_class, _meth);
        return ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, state_, null);
    }
    protected ReportedMessages conditionalStdViewBad(String _dyn, String _class, int _caret, StringMap<String> _files) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(), _caret,res_);
        ExecRootBlock classBody_ = res_.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        BreakPointBlockPair pair_ = res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(classBody_.getFile(), _caret);
        return BreakPointBlockList.breakPointCtxStd(pair_, res_,new DefContextGenerator(), _dyn);
    }
    protected ReportedMessages valueDbgKo(String _dyn, String _class, String _meth, int _caret, StringMap<String> _files) {
        ResultContext res_ = firstAna(_files);
        RootBlock ana_ = res_.getPageEl().getAnaClassBody(_class);
        res_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),_caret,res_);
        return endKo(_dyn,_class,_meth,res_);
    }

    protected ResultContext firstAna(StringMap<String> _files) {
        return ctxLgReadOnlyOkQuick("en", _files);
    }

    private Struct end(String _dyn, String _class, String _meth, ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return ResultContextLambda.dynamicAnalyze(_dyn, page_.getFile().getFileName(), st_.getGlobalOffset(), _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(),null).eval(page_).getRetValue().getValue().getStruct();
    }

    protected ResultContextLambda dynAna(String _dyn, String _class, int _caret, ResultContext _res) {
        RootBlock ana_ = _res.getPageEl().getAnaClassBody(_class);
        _res.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint(ana_.getFile().getFileName(),_caret,_res);
        return ResultContextLambda.dynamicAnalyze(_dyn, ana_.getFile().getFileName(), _caret, _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(),null);
    }

    protected Struct dynEval(ResultContextLambda _dyn, String _class, String _meth, ResultContext _res) {
        AbstractPageEl page_ = goToBp(_res, _class, _meth);
        return _dyn.eval(_res.getContext(),page_).getRetValue().getValue().getStruct();
    }

    protected AbstractPageEl goToBp(ResultContext _res, String _class, String _meth) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
        StackCall st_ = stVal_.getStack();
        return st_.getLastPage();
    }

    private ArrayStruct endExc(String _dyn, String _class, String _meth, ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        ResultContextLambda resLam_ = ResultContextLambda.dynamicAnalyze(_dyn, page_.getFile().getFileName(), st_.getGlobalOffset(), _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(), null);
        StackCall locSt_ = resLam_.eval(page_).getStack();
        return ((ErrorStruct)((CustomFoundExc)locSt_.getCallingState()).getStruct()).getStack();
    }
    private ReportedMessages endKo(String _dyn, String _class, String _meth, ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return ResultContextLambda.dynamicAnalyze(_dyn, page_.getFile().getFileName(), st_.getGlobalOffset(), _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(),null).getReportedMessages();
    }
    private ReportedMessages endKoSt(String _dyn, String _class, String _meth, ResultContext _res) {
        ExecRootBlock classBody_ = _res.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, getMethodId(_meth)).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCallReturnValue stVal_ = ExecClassesUtil.tryInitStaticlyTypes(_res.getContext(), _res.getPageEl().getOptions(), null, new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_),null);
        StackCall st_ = stVal_.getStack();
        AbstractPageEl page_ = st_.getLastPage();
        return ResultContextLambda.dynamicAnalyze(_dyn, page_.getFile().getFileName(), st_.getGlobalOffset(), _res, _res.getPageEl().getAliasPrimInteger(), new DefContextGenerator(), MethodAccessKind.STATIC).getReportedMessages();
    }
    protected AnalyzedPageEl scope(StringMap<String> _files, String _fileName, int _caret, String... _types) {
        ResultContext res_ = ctxLgReadOnlyOkQuick("en", _files, _types);
        return ResultExpressionOperationNode.prepare(_fileName,_caret,res_.getPageEl(),null);
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
