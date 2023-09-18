package code.expressionlanguage.options;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.common.DefaultFileEscapedCalc;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.ExecAbstractLambdaOperation;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.comparators.NaturalComparator;
import code.util.core.StringUtil;

public final class ResultContextLambda {
    private final ExecTypeFunction lambda;
    private final ContextEl context;
    private final ReportedMessages reportedMessages;
    private boolean initTypes;
    private final int delta;
    private final AnonymousLambdaOperation lda;

    public ResultContextLambda(ContextEl _c, ExecTypeFunction _l, ReportedMessages _r, int _d, AnonymousLambdaOperation _ld) {
        this.lambda = _l;
        this.context = _c;
        this.reportedMessages = _r;
        this.delta = _d;
        this.lda = _ld;
    }

    public static ResultContextLambda dynamicAnalyze(String _exp, BreakPointBlockPair _mp, ResultContext _result, String _type, AbsLightContextGenerator _gene, int _phasePoint) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(ExecFileBlock.name(_mp.getBp().getFile()), _mp.getBp().getOffset(), _result.getPageEl(),_phasePoint);
        return build(_exp, _result, _type, _gene, a_);
    }

    public static ResultContextLambda dynamicAnalyze(String _exp, StdMethodPointBlockPair _instance, ResultContext _result, String _type, AbsLightContextGenerator _gene) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(_instance, _result.getPageEl());
        return build(_exp, _result, _type, _gene, a_);
    }


    public static ResultContextLambda dynamicAnalyze(String _exp, OperNatPointBlockPair _instance, ResultContext _result, String _type, AbsLightContextGenerator _gene) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(_instance, _result.getPageEl());
        return build(_exp, _result, _type, _gene, a_);
    }

    public static ResultContextLambda dynamicAnalyze(String _exp, MethodPointBlockPair _instance, ResultContext _result, String _type, AbsLightContextGenerator _gene) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(_instance, _result.getPageEl());
        return build(_exp, _result, _type, _gene, a_);
    }

    public static ResultContextLambda dynamicAnalyzeField(String _exp, WatchPointBlockPair _trField, ResultContext _result, String _type, AbsLightContextGenerator _gene, int _flag) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepareFields(_trField, _result.getPageEl(),_flag);
        return build(_exp, _result, _type, _gene, a_);
    }

    public static ResultContextLambda dynamicAnalyzeArr(String _exp, ArrPointBlockPair _ex, ResultContext _result, String _type, AbsLightContextGenerator _gene, int _flag) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepareArr(_ex.getEp().getClName(),_result.getPageEl(), _flag);
        return build(_exp, _result, _type, _gene, a_);
    }

    public static ResultContextLambda dynamicAnalyzeExc(String _exp, ExcPointBlockPair _ex, ResultContext _result, String _type, AbsLightContextGenerator _gene) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepareExc(_ex.getEp().getClName(), _ex.getEp().isExact(), _result.getPageEl());
        return build(_exp, _result, _type, _gene, a_);
    }


    public static ResultContextLambda dynamicAnalyzePar(String _exp, ParPointBlockPair _ex, ResultContext _result, String _type, AbsLightContextGenerator _gene) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages(), 0, null);
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.preparePar(_ex.getPp().getClName(), _ex.getPp().isExact(), _ex.getRootBlock(), _result.getPageEl());
        return build(_exp, _result, _type, _gene, a_);
    }

    private static ResultContextLambda build(String _exp, ResultContext _result, String _type, AbsLightContextGenerator _gene, AnalyzedPageEl _a) {
        String pref_ = "(:" + _type + ")->";
        String dynLda_ = pref_ + _exp;
        int d_ = pref_.length();
        FileBlock anonFile_ = new FileBlock(0, false, "", new DefaultFileEscapedCalc());
        _a.setCurrentFile(anonFile_);
        anonFile_.metrics(dynLda_);
        _a.putFileBlock(anonFile_);
        _a.backupFiles();
        FileBlock file_ = _a.getCurrentFile();
        MethodAccessKind stCtx_ = _a.getStaticContext();
        MemberCallingsBlock memb_ = _a.getCurrentFct();
        Line l_ = new Line(new OffsetStringInfo(0, dynLda_),0);
        StringComment s_ = new StringComment(l_.getExpression(),new CustList<CommentDelimiters>());
        l_.getRes().partsAbsol(s_.getStringParts());
        l_.setFile(file_);
        String value_ = l_.getExpression();
        ResultExpression resultExpression_ = l_.getRes();
        resultExpression_.setAnalyzedString(value_);
        resultExpression_.setSumOffset(l_.getExpressionOffset());
        IntermediaryResults anon_ = new IntermediaryResults();
        extractAnon(_a, anon_, resultExpression_);

        _a.setNextResults(SplitExpressionUtil.anonymous(anon_, _a));
        _a.setCurrentFct(memb_);
        _a.setAccessStaticContext(stCtx_);
        _a.setAccessedFct(null);
        _a.setCurrentFile(l_.getFile());
        _a.setCurrentBlock(l_);
        l_.buildExpressionLanguageReadOnly(_a);
        CustList<AnonymousLambdaOperation> al_ = _a.getAnonymousLambda();
        if (al_.isEmpty()) {
            return new ResultContextLambda(null, null, _a.getMessages(), 0, null);
        }
        AnonymousLambdaOperation mainLambda_ = al_.first();
        ClassesUtil.processAnonymous(_a);
        _a.setAnnotAnalysis(false);
        ClassesUtil.validateSimFinals(_a);
        ClassesUtil.checkEnd(_a);
        if (_a.notAllEmptyErrors()) {
            return new ResultContextLambda(null, null, _a.getMessages(), 0, null);
        }
        Forwards forwards_ = ForwardInfos.generalForward(_a, _result);
        ContextEl ctx_ = _gene.gene(forwards_);
        Classes.forwardAndClear(ctx_);
        return new ResultContextLambda(ctx_, ForwardInfos.buildAnonFctPair(forwards_, mainLambda_), _a.getMessages(), d_, mainLambda_);
    }

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, ResultExpression _resultExpression) {
        _page.setSumOffset(_resultExpression.getSumOffset());
        ElRetrieverAnonymous.commonCheckQuick(0, _page, _resultExpression, _page.getImporting());
        feedResult(_page, _resultExpression, _int);
    }

    private static void feedResult(AnalyzedPageEl _page, ResultExpression _resultExpression, IntermediaryResults _int) {
        SplitExpressionUtil.feed(_page,_resultExpression, _int, _page.getImporting());
    }

    public static ResultContextLambda okOrNull(ResultContextLambda _res) {
        if (_res.getContext() == null) {
            return null;
        }
        return _res;
    }

    public static String okOrEmpty(ResultContextLambda _res, String _str) {
        if (_res.getContext() == null) {
            return "";
        }
        return StringUtil.nullToEmpty(_str);
    }
    public StackCallReturnValue eval(ContextEl _original, CoreCheckedExecOperationNodeInfos _addon, AbstractPageEl _page) {
        prepare(_original);
        return eval(_addon,_page);
    }
    public CustList<String> evalLog(ContextEl _original, CoreCheckedExecOperationNodeInfos _addon, AbstractPageEl _page) {
        prepare(_original);
        return evalStr(eval(_addon, _page));
    }
    public CustList<String> evalStr(StackCallReturnValue _pre) {
        StackCall st_ = _pre.getStack();
        CallingState stateOld_ = st_.getCallingState();
        if (stateOld_ != null) {
            return traceView(st_, context);
        }
        Argument arg_ = IndirectCalledFctUtil.processString(st_.aw().getValue(), context, st_);
        AbstractPageEl page_ = ExecutingUtil.processAfterOperation(context,st_);
        if (page_ == null) {
            CustList<String> e_ = new CustList<String>();
            e_.add(ExecCatOperation.getString(arg_,context));
            return e_;
        }
        StackCallReturnValue ret_ = loop(st_, page_);
        CallingState state_ = ret_.getStack().getCallingState();
        if (state_ != null) {
            return traceView(ret_.getStack(), context);
        }
        CustList<String> e_ = new CustList<String>();
        e_.add(ExecCatOperation.getString(ret_.getStack().aw().getValue(), context));
        return e_;
    }

    public static CustList<String> traceView(StackCall _st, ContextEl _ctx) {
        ArrayStruct v_ = _st.getStackView();
        int count_ = v_.getLength();
        CustList<String> e_ = new CustList<String>();
        for (int i = 0; i < count_; i++) {
            e_.add(NumParsers.getStack(v_.get(i)).getDisplayedString(_ctx).getInstance());
        }
        return e_;
    }

    public static CustList<String> trace(StackCall _st, ContextEl _ctx) {
        int count_ = _st.nbPages();
        CustList<String> e_ = new CustList<String>();
        for (int i = 0; i < count_; i++) {
            e_.add(MetaInfoUtil.newStackTraceElement(_ctx,i, _st).getDisplayedString(_ctx).getInstance());
        }
        return e_;
    }

    private void prepare(ContextEl _original) {
        if (!initTypes) {
            initTypes = true;
            ExecClassesUtil.forwardClassesMetaInfos(context);
            DefaultLockingClass dl_ = context.getLocks();
            dl_.init(context);
            context.setExiting(new DefaultExiting(context));
            StackCall st_ = StackCall.newInstance(InitPhase.LIST,context);
            st_.getInitializingTypeInfos().setInitEnums(InitPhase.LIST);
            endOrder(_original,st_);
            ExecClassesUtil.updateAfter(context);
        } else {
            context.getClasses().getCommon().getStaticFields().putAllMap(_original.getClasses().getStaticFields());
        }
        for (EntryCust<ExecRootBlock, InitClassState> c: _original.getLocks().getClasses().entryList()) {
            context.getLocks().state(c.getKey(),c.getValue());
        }
    }

    private void endOrder(ContextEl _original, StackCall _st) {
        CustList<String> f_ = context.getClasses().getClassesBodies().getKeys();
        StringUtil.removeAllElements(f_, _original.getClasses().getClassesBodies().getKeys());
        f_.sortElts(new NaturalComparator());
        Classes cl_ = context.getClasses();
        cl_.getCommon().getStaticFields().putAllMap(_original.getClasses().getStaticFields());
        for (String c: f_) {
            _st.getInitializingTypeInfos().resetInitEnums(_st);
            ExecRootBlock r_ = cl_.getClassBody(c);
            if (ProcessMethod.stateMismatch(r_, context, InitClassState.SUCCESS)) {
                context.getLocks().initClass(r_);
                AbstractPageEl page_ = new NotInitializedClass(new ExecFormattedRootBlock(r_,c),r_,null).processAfterOperation(context,_st);
                loop(_st, page_);
            }
        }
        _st.getInitializingTypeInfos().resetInitEnums(_st);
    }

    public StackCallReturnValue eval(CoreCheckedExecOperationNodeInfos _addon, AbstractPageEl _page) {
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, context);
        if (_addon instanceof CheckedMethodInfos) {
            AbstractPageEl page_ = new CustomFoundMethod(new Argument(_addon.getInstance()),_addon.getDeclaring(),lambda, ((CheckedMethodInfos)_addon).getParameters()).processAfterOperation(context,stackCall_);
            return loop(stackCall_, page_);
        }
        Parameters p_ = new Parameters();
        if (_addon == null) {
            p_.getRefParameters().addAllEntries(_page.getRefParams());
            p_.setCache(_page.getCache());
            AbstractPageEl page_ = new CustomFoundMethod(_page.getGlobalArgument(),_page.getGlobalClass(),lambda, p_).processAfterOperation(context,stackCall_);
            page_.getVars().addAllEntries(_page.getVars());
            return loop(stackCall_, page_);
        }
        p_.setCache(_addon.getCache());
        AbstractPageEl page_ = new CustomFoundMethod(new Argument(_addon.getInstance()),_addon.getDeclaring(),lambda, p_).processAfterOperation(context,stackCall_);
        return loop(stackCall_, page_);
    }

    private StackCallReturnValue loop(StackCall _stack, AbstractPageEl _page) {
        ExecLambdaMethodContent m_ = new ExecLambdaMethodContent(lda.getMethod(), lambda);
        ExecFormattedRootBlock f_ = _page.getGlobalClass();
        MethodMetaInfo meth_ = new MethodMetaInfo(ExecAbstractLambdaOperation.build(m_, f_), _page.getCache(), new ExecLambdaCommonContent(lda.getLambdaCommonContent(), f_), m_);
        _stack.setGlobalLda(meth_);
        _stack.setCallCondition(delta);
        ExecutingUtil.addPage(_page, _stack);
        Initializer i_ = context.getInit();
        AbstractInterceptorStdCaller c_ = context.getCaller();
        while (true) {
            if (c_.stopNormal(i_, context, _stack)) {
                _stack.setReturnedArgument(ArgumentListCall.toStr(_page.getReturnedArgument()));
                _stack.setWrapper(null);
                return new StackCallReturnValue(_stack, new CustList<ViewPage>());
            }
        }
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }

    public ContextEl getContext() {
        return context;
    }
}
