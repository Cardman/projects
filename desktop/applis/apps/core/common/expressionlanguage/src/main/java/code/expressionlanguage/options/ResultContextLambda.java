package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ViewPage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.SecondForwardGenerator;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class ResultContextLambda {
    private final ExecTypeFunction lambda;
    private final ContextEl context;
    private final ReportedMessages reportedMessages;

    public ResultContextLambda(ContextEl _c, ExecTypeFunction _l, ReportedMessages _r) {
        this.lambda = _l;
        this.context = _c;
        this.reportedMessages = _r;
    }

    public static ResultContextLambda dynamicAnalyze(String _exp, String _fileName, int _caret, ResultContext _result, String _type, AbsContextGenerator _gene) {
        AnalyzedPageEl a_ = ResultExpressionOperationNode.prepare(_fileName, _caret, _result.getPageEl());
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
        ClassesUtil.postValidation(a_);
        for (EntryCust<String, StringMap<Struct>> e:a_.getStaticFieldsAna().entryList()) {
            if (!AnaTypeUtil.allCst(a_,e.getKey(),e.getValue().getKeys())) {
                return new ResultContextLambda(null,null,a_.getMessages());
            }
        }
        if (a_.notAllEmptyErrors()) {
            return new ResultContextLambda(null,null,a_.getMessages());
        }
        Forwards forwards_ = new Forwards(_result.getForwards(),a_);
        ForwardInfos.generalForward(a_,forwards_,new SecondForwardGenerator());
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
    public StackCallReturnValue eval(AbstractPageEl _page) {
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, context);
        Parameters p_ = new Parameters();
        p_.getRefParameters().addAllEntries(_page.getRefParams());
        p_.setCache(_page.getCache());
        AbstractPageEl page_ = new CustomFoundMethod(_page.getGlobalArgument(),_page.getGlobalClass(),lambda, p_).processAfterOperation(context,stackCall_);
        ExecutingUtil.addPage(page_, stackCall_);
        Initializer i_ = context.getInit();
        while (true) {
            if (i_.stopNormal(context, stackCall_) || i_.exitAfterCallInt(context, stackCall_)) {
                return new StackCallReturnValue(stackCall_,new ArgumentWrapper(ArgumentListCall.toStr(page_.getReturnedArgument())),new CustList<ViewPage>());
            }
        }
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
