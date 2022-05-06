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
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendForwardInfos;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class CommonRenderExpUtil extends CommonRender {
    protected static CustList<RendDynOperationNode> getQuickExecutableNodes(DualNavigationContext _an, CustList<OperationNode> _ops) {
        OperationNode root_ = _ops.last();
        return RendForwardInfos.getExecutableNodes(root_, _an.getDualAnalyzedContext().getForwards());
    }

    protected static CustList<OperationNode> getQuickAnalyzed(String _el, DualNavigationContext _conf, AnalyzingDoc _analyzingDoc) {
        _analyzingDoc.setup(_conf.getNavigation().getSession(), _conf.getDualAnalyzedContext().getContext().getProperties(), _conf.getDualAnalyzedContext().getContext().getMessagesFolder());
        StringMap<LocalVariable> localVariables_ = new StringMap<LocalVariable>();
        StringMap<LoopVariable> vars_ = new StringMap<LoopVariable>();
        setupAnalyzing(_conf, _analyzingDoc, localVariables_, vars_);
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
        Classes.validateWithoutInit(_files, a_.getDualAnalyzedContext().getAnalyzed());
        assertTrue(isEmptyErrors(a_));
        return a_;
    }

    protected static void setupAnalyzing(DualNavigationContext _analyzing, AnalyzingDoc _analyzingDoc, StringMap<LocalVariable> _localVariables, StringMap<LoopVariable> _vars) {

        String globalClass_ = _analyzing.getDualAnalyzedContext().getAnalyzed().getGlobalClass();
        setupAna(_analyzingDoc, _analyzing.getDualAnalyzedContext().getAnalyzed());
        _analyzing.getDualAnalyzedContext().getAnalyzed().setGlobalType(new AnaFormattedRootBlock(_analyzing.getDualAnalyzedContext().getAnalyzed(),globalClass_));
        for (EntryCust<String, LocalVariable> e: _localVariables.entryList()) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getValue().getClassName());
            a_.setConstType(ConstType.LOC_VAR);
            _analyzing.getDualAnalyzedContext().getAnalyzed().getInfosVars().put(e.getKey(), a_);
        }
        for (EntryCust<String,LoopVariable> e: _vars.entryList()) {
            AnaLoopVariable a_ = new AnaLoopVariable();
            a_.setIndexClassName(e.getValue().getIndexClassName());
            _analyzing.getDualAnalyzedContext().getAnalyzed().getLoopsVars().put(e.getKey(), a_);
        }
    }

    protected static void setupValues(ImportingPage _lastPage, StringMap<LocalVariable> _localVariables, StringMap<LoopVariable> _vars) {
        for (EntryCust<String, LocalVariable> e: _localVariables.entryList()) {
            _lastPage.getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
        }
        for (EntryCust<String,LoopVariable> e: _vars.entryList()) {
            _lastPage.getPageEl().getVars().addEntry(e.getKey(),e.getValue());
        }
//        _lastPage.setGlobalArgumentStruct(_analyzing.getArgument().getStruct(), _context);
    }
    protected static void setLocalVars(StringMap<LocalVariable> _localVars, StringMap<LocalVariable> _localVariables) {
//        for (EntryCust<String, LocalVariable> e: _localVars.entryList()) {
//            _importingPage.getLastPage().getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
//        }
        _localVariables.addAllEntries(_localVars);
    }

    protected static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        AnalyzingDoc.setupInts(_page, _analyzingDoc);
    }

    protected static ContextEl getGenerate(DualNavigationContext _an) {
        return _an.getDualAnalyzedContext().getForwards().generate();
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
        analyzing_.setGlobalOffset(0);
        analyzing_.zeroOffset();
        analyzing_.setTranslatedOffset(0);
        ElRetrieverAnonymous.commonCheckQuick(_elr,0,analyzing_,res_);
        analyzing_.setCurrentParts(res_.getParts());
        analyzing_.setCurrentNumbers(res_.getNumbers());
        return ElResolver.checkSyntax(_elr, 0, analyzing_);
    }

    protected static OperationNode getOperationNode(int _ind, byte _ch, MethodOperation _par, OperationsSequence _opTwo, DualNavigationContext _ctx) {
        return OperationNode.createOperationNode(_ind, _ch, _par, _opTwo, _ctx.getDualAnalyzedContext().getAnalyzed());
    }

}
