package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.ResultContext;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.util.DualAnalyzedContext;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class CommonRenderExpUtil extends CommonRender {
    protected static CustList<RendDynOperationNode> getQuickExecutableNodes(DualNavigationContext _an, CustList<OperationNode> _ops) {
        OperationNode root_ = _ops.last();
        return RendForwardInfos.getExecutableNodes(root_, _an.getDualAnalyzedContext().getForwards());
    }

    protected static CustList<OperationNode> getQuickAnalyzed(String _el, DualNavigationContext _conf, AnalyzingDoc _analyzingDoc) {
        _analyzingDoc.setup(_conf.getNavigation().getSession(), _conf.getDualAnalyzedContext().getContext().getProperties(), _conf.getDualAnalyzedContext().getContext().getMessagesFolder());

        setupAnalyzing(_conf);
//        Argument argGl_ = _conf.getArgument();
        boolean static_ = true;
        _conf.getDualAnalyzedContext().getAnalyzed().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(_conf, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _conf, d_, _el, _analyzingDoc);
        OperationNode op_ = rendOp(0, _conf, opTwo_, _analyzingDoc);
        return getSortedDescNodes(_conf, op_, _analyzingDoc,d_);
    }

    protected static DualNavigationContext getConfigurationQuick(StringMap<String> _files,String... _types) {
        Configuration conf_ = EquallableRenderUtil.newConfiguration();
        DualNavigationContext a_ = buildNav(conf_,_types);
        DualAnalyzedContext f_ = a_.getDualAnalyzedContext();

        AnalyzedPageEl page_ = f_.getAnalyzed();
        ResultContext r_ = new ResultContext(page_, f_.getForwards());
        StringMap<String> all_ = new StringMap<String>();
        ResultContext user_ = ResultContext.afterDef(r_, all_, ResultContext.defFilter(page_, all_, _files));

        AnalyzedPageEl copy_ = user_.getPageEl();
        assertTrue(isEmptyErrors(copy_));
        return new DualNavigationContext(a_.getNavigation(),new DualAnalyzedContext(f_.getForwards(),copy_,f_.getStds(),f_.getContext(),f_.getBlock()));
    }

    protected static void setupAnalyzing(DualNavigationContext _analyzing) {
        String globalClass_ = _analyzing.getDualAnalyzedContext().getAnalyzed().getGlobalClass();
        setupAna(_analyzing.getDualAnalyzedContext().getAnalyzed());
        _analyzing.getDualAnalyzedContext().getAnalyzed().globalType(new AnaFormattedRootBlock(_analyzing.getDualAnalyzedContext().getAnalyzed(),globalClass_));
    }
    protected static void setLocalVars(StringMap<LocalVariable> _localVars, StringMap<LocalVariable> _localVariables) {
//        for (EntryCust<String, LocalVariable> e: _localVars.entryList()) {
//            _importingPage.getLastPage().getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
//        }
        _localVariables.addAllEntries(_localVars);
    }

    protected static void setupAna(AnalyzedPageEl _page) {
        AnalyzingDoc.setupInts(_page);
    }

    protected static ContextEl getGenerate(DualNavigationContext _an) {
        return new DefRenderContextGenerator().gene(_an.getDualAnalyzedContext().getForwards());
    }

    protected static void generalForward(DualNavigationContext _cont) {
        ForwardInfos.generalForward( _cont.getDualAnalyzedContext().getAnalyzed(), _cont.getDualAnalyzedContext().getForwards());
    }

    protected static OperationNode rendOp(int _i, DualNavigationContext _conf, OperationsSequence _opTwo, AnalyzingDoc _analyzingDoc) {
        return RenderAnalysis.createOperationNode(_i, IndexConstants.FIRST_INDEX, null, _opTwo, _analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed());
    }

    protected static OperationsSequence rendOpSeq(int _i, DualNavigationContext _conf, Delimiters _d, String _el, AnalyzingDoc _analyzingDoc) {
        return RenderAnalysis.getOperationsSequence(_i, _el, _d, _analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed(), null);
    }

    protected static CustList<OperationNode> getSortedDescNodes(DualNavigationContext _conf, OperationNode _op, AnalyzingDoc _analyzingDoc, Delimiters _d) {
        return RenderAnalysis.getSortedDescNodes(_op, _analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed(),_d);
    }

    protected static OperationsSequence getOperationsSequence(int _offset, String _el, DualNavigationContext _ctx, Delimiters _d) {
        return ElResolver.getOperationsSequence(_offset, _el, _d, _ctx.getDualAnalyzedContext().getAnalyzed(),null);
    }

    protected static Delimiters checkSyntax(DualNavigationContext _ctx, String _elr) {
        ResultExpression res_ = new ResultExpression();
        AnalyzedPageEl analyzing_ = _ctx.getDualAnalyzedContext().getAnalyzed();
//        analyzing_.setCurrentParts(res_.getParts());
        StringComment str_ = new StringComment(_elr,new CustList<CommentDelimiters>());
        res_.partsAbsol(str_.getStringParts());
        res_.setAnalyzedString(_elr);
        analyzing_.setSumOffset(0);
        analyzing_.zeroOffset();
        ElRetrieverAnonymous.commonCheckQuick(0,analyzing_,res_, null);
        analyzing_.setCurrentParts(res_.getParts());
        analyzing_.setCurrentNumbers(res_.getNumbers());
        return ElResolver.checkSyntax(res_, 0, analyzing_);
    }

    protected static OperationNode getOperationNode(int _ind, int _ch, MethodOperation _par, OperationsSequence _opTwo, DualNavigationContext _ctx) {
        return OperationNode.createOperationNode(_ind, _ch, _par, _opTwo, _ctx.getDualAnalyzedContext().getAnalyzed());
    }

}
