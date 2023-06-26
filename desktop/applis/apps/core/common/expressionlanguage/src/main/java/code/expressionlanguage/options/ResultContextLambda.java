package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.util.CustList;
import code.util.comparators.NaturalComparator;
import code.util.core.StringUtil;

public final class ResultContextLambda {
    private final ExecTypeFunction lambda;
    private final ContextEl context;
    private final ReportedMessages reportedMessages;
    private boolean initTypes;

    public ResultContextLambda(ContextEl _c, ExecTypeFunction _l, ReportedMessages _r) {
        this.lambda = _l;
        this.context = _c;
        this.reportedMessages = _r;
    }

    public static ResultContextLambda dynamicAnalyze(String _exp, String _fileName, int _caret, ResultContext _result, String _type, AbsLightContextGenerator _gene, MethodAccessKind _flag) {
        if (_exp.trim().isEmpty()) {
            return new ResultContextLambda(null,null,new ReportedMessages());
        }
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(_fileName, _caret, _result.getPageEl(),_flag);
        MethodAccessKind stCtx_ = a_.getStaticContext();
        MemberCallingsBlock memb_ = a_.getCurrentFct();
        Line l_ = new Line(new OffsetStringInfo(0,"(:"+ _type+")->"+_exp),0);
        StringComment s_ = new StringComment(l_.getExpression(),new CustList<CommentDelimiters>());
        l_.getRes().partsAbsol(s_.getStringParts());
        l_.setFile(a_.getCurrentFile());
        String value_ = l_.getExpression();
        ResultExpression resultExpression_ = l_.getRes();
        resultExpression_.setAnalyzedString(value_);
        resultExpression_.setSumOffset(l_.getExpressionOffset());
        IntermediaryResults anon_ = new IntermediaryResults();
        extractAnon(a_, anon_, a_.getGlobalType().getRootBlock(), resultExpression_);

        a_.setNextResults(SplitExpressionUtil.anonymous(anon_,a_));
        a_.setCurrentFct(memb_);
        a_.setAccessStaticContext(stCtx_);
        a_.setAccessedFct(null);
        a_.setCurrentFile(l_.getFile());
        a_.setCurrentBlock(l_);
        l_.buildExpressionLanguageReadOnly(a_);
        CustList<AnonymousLambdaOperation> al_ = a_.getAnonymousLambda();
        if (al_.isEmpty()) {
            return new ResultContextLambda(null,null,a_.getMessages());
        }
        AnonymousLambdaOperation mainLambda_ = al_.first();
        ClassesUtil.processAnonymous(a_);
        a_.setAnnotAnalysis(false);
        ClassesUtil.validateSimFinals(a_);
        ClassesUtil.checkEnd(a_);
        if (a_.notAllEmptyErrors()) {
            return new ResultContextLambda(null,null,a_.getMessages());
        }
        Forwards forwards_ = ForwardInfos.generalForward(a_,_result);
        ContextEl ctx_ = _gene.gene(forwards_);
        Classes.forwardAndClear(ctx_);
        return new ResultContextLambda(ctx_, ForwardInfos.buildAnonFctPair(forwards_, mainLambda_), a_.getMessages());
    }

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, RootBlock _type, ResultExpression _resultExpression) {
        _page.setSumOffset(_resultExpression.getSumOffset());
        ElRetrieverAnonymous.commonCheckQuick(0, _page, _resultExpression);
        feedResult(_page, _resultExpression, _int, _type);
    }

    private static void feedResult(AnalyzedPageEl _page, ResultExpression _resultExpression, IntermediaryResults _int, RootBlock _type) {
        SplitExpressionUtil.feed(_resultExpression, _int, _type, _page.getImporting());
    }

    public static ReportedMessages after(ResultContextLambda _res) {
        if (_res.getContext() == null) {
            return _res.getReportedMessages();
        }
        return new ReportedMessages();
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
//    private static AccessedBlock tryGetOperator(BracedBlock _mem) {
//        AccessedBlock op_ = null;
//        if (AbsBk.isAnonBlock(_mem)) {
//            op_ = ((NamedCalledFunctionBlock) _mem).getAccessedBlock();
//        }
//        if (_mem instanceof OperatorBlock) {
//            op_ = (OperatorBlock) _mem;
//        }
//        if (_mem instanceof SwitchMethodBlock) {
//            op_ = ((SwitchMethodBlock) _mem).getAccessedBlock();
//        }
//        return op_;
//    }
    public StackCallReturnValue eval(ContextEl _original,AbstractPageEl _page) {
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
        }
        return eval(_page);
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

    public StackCallReturnValue eval(AbstractPageEl _page) {
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, context);
        Parameters p_ = new Parameters();
        p_.getRefParameters().addAllEntries(_page.getRefParams());
        p_.setCache(_page.getCache());
        AbstractPageEl page_ = new CustomFoundMethod(_page.getGlobalArgument(),_page.getGlobalClass(),lambda, p_).processAfterOperation(context,stackCall_);
        page_.getVars().addAllEntries(_page.getVars());
        return loop(stackCall_, page_);
    }

    private StackCallReturnValue loop(StackCall _stack, AbstractPageEl _page) {
        ExecutingUtil.addPage(_page, _stack);
        Initializer i_ = context.getInit();
        AbstractInterceptorStdCaller c_ = context.getCaller();
        while (true) {
            if (c_.stopNormal(i_, context, _stack) || c_.exitAfterCallInt(i_,context, _stack)) {
                return new StackCallReturnValue(_stack, new ArgumentWrapper(ArgumentListCall.toStr(_page.getReturnedArgument())), new CustList<ViewPage>());
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
