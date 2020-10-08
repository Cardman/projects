package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public abstract class CommonRender {

    protected static void addImportingPage(AnalyzedTestConfiguration _conf) {
        addInnerPage(_conf.getConfiguration());
    }

    protected static void addInnerPage(Configuration _conf) {
        _conf.addPage(new ImportingPage());
    }

    protected static Navigation newNavigation(AnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());
        ContextEl context_ = _conf.getContext();

        return nav_;
    }

    protected static void setupAnalyzing(AnalyzedPageEl _analyzing, ImportingPage _lastPage, AnalyzingDoc _analyzingDoc) {
        //

        String globalClass_ = _analyzing.getGlobalClass();
        setupAna(_analyzingDoc, _analyzing);
        _analyzing.setGlobalType(_analyzing.getAnaClassBody(StringExpUtil.getIdFromAllTypes(globalClass_)));
        for (EntryCust<String,LocalVariable> e: _lastPage.getValueVars().entryList()) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getValue().getClassName());
            a_.setConstType(ConstType.LOC_VAR);
            _analyzing.getInfosVars().put(e.getKey(), a_);
        }
        for (EntryCust<String,LoopVariable> e: _lastPage.getVars().entryList()) {
            AnaLoopVariable a_ = new AnaLoopVariable();
            a_.setIndexClassName(e.getValue().getIndexClassName());
            _analyzing.getLoopsVars().put(e.getKey(), a_);
        }
    }

    protected static void setVars(ImportingPage _importingPage, StringMap<LoopVariable> _vars) {
        _importingPage.getPageEl().setVars(_vars);
    }

    protected static void setLocalVars(ImportingPage _importingPage, StringMap<LocalVariable> _localVars) {
        _importingPage.getPageEl().getValueVars().putAllMap(_localVars);
    }

    protected static boolean isEmptyErrors(AnalyzedTestConfiguration cont_) {
        return cont_.getAnalyzing() == null || (cont_.getAnalyzing().isEmptyErrors());
    }

    protected static void getHeaders(StringMap<String> _files, AnalyzedTestConfiguration _cont) {
        Classes.validateWithoutInit(_files, _cont.getAnalyzing());
    }

    protected static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl page_) {
        AnalyzingDoc.setupInts(page_, _analyzingDoc);
    }

    private static void setLocalFiles(AnalyzedTestConfiguration context_, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = context_.getAnalyzing();
        Configuration conf_ = context_.getConfiguration();
        _analyzingDoc.setup(conf_, context_.getAdvStandards(), context_.getDual());
        setInnerLocalFilesLg(_analyzingDoc, analyzing_, conf_);
    }

    private static void setInnerLocalFilesLg(AnalyzingDoc _analyzingDoc, AnalyzedPageEl analyzing_, Configuration conf_) {
        conf_.setCurrentLanguage("en");
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, AnalyzedPageEl analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, analyzing);
    }

    protected static void tryInitStaticlyTypes(AnalyzedTestConfiguration _context) {
        if (_context.isEmptyErrors()) {
            AnalyzedPageEl page_ = _context.getAnalyzing();
            Classes.tryInitStaticlyTypes(_context.getContext(), page_.getOptions());
        }
        addInnerPage(_context.getConfiguration());
    }

    protected static void tryForward(AnalyzedTestConfiguration _context) {
        if (_context.isEmptyErrors()) {
            AnalyzedPageEl page_ = _context.getAnalyzing();
            ((BeanCustLgNames)_context.getStandards()).forwardAndClear(_context.getConfiguration(),page_, _context.getAnalyzingDoc(),
                    _context.getForwards(), _context.getAnalyzed(), _context.getContext());
        }
    }

    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getStruct();
    }
    protected static void setStruct(Struct _struct, ClassField _cl, Struct _value) {
        ((FieldableStruct) _struct).getEntryStruct(_cl).setStruct(_value);
    }

    protected static Struct getException(AnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }

    private static void setFiles(StringMap<String> files_, Configuration conf_) {
        conf_.setFiles(files_);
    }

    protected static CustList<RendDynOperationNode> getAnalyzed(String _el, int _index, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc) {
        _analyzingDoc.setup(_conf.getConfiguration(), _conf.getAdvStandards(), _conf.getDual());
        setupAnalyzing(_conf.getAnalyzing(), _conf.getLastPage(), _conf.getAnalyzingDoc());
        Argument argGl_ = _conf.getConfiguration().getPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        _conf.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        return getList(_el, _index, _conf, _analyzingDoc);
    }

    protected static CustList<RendDynOperationNode> getList(String _el, int _index, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc) {
        Delimiters d_ = checkSyntax(_conf,_el, _index);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_conf.getAnalyzingDoc().getFileName());
            badEl_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_conf.getAnalyzing(), _conf.getAnalyzingDoc()));
            badEl_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadExpression(),
                    " ",
                    Integer.toString(badOffset_),
                    _el);
            AnalyzingDoc.addError(badEl_, _conf.getAnalyzingDoc(), _conf.getAnalyzing());
            return new CustList<RendDynOperationNode>();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = rendOpSeq(_index, _conf, d_, el_);
        OperationNode op_ = rendOp(_index, _conf, _analyzingDoc, opTwo_);
        CustList<OperationNode> all_ = getSortedDescNodes(_conf, op_);
        return getExecutableNodes(_conf, all_);
    }

    protected static OperationNode rendOp(int i, AnalyzedTestConfiguration conf_, AnalyzingDoc analyzingDoc_, OperationsSequence opTwo_) {
        return RenderAnalysis.createOperationNode(i, CustList.FIRST_INDEX, null, opTwo_, conf_.getAnalyzingDoc(), conf_.getAnalyzing());
    }

    protected static OperationsSequence rendOpSeq(int i, AnalyzedTestConfiguration conf_, Delimiters d_, String el_) {
        return RenderAnalysis.getOperationsSequence(i, el_, d_, conf_.getAnalyzingDoc(), conf_.getAnalyzing());
    }

    protected static CustList<OperationNode> getSortedDescNodes(AnalyzedTestConfiguration _conf, OperationNode op_) {
        return RenderAnalysis.getSortedDescNodes(op_, _conf.getAnalyzingDoc(), _conf.getAnalyzing());
    }

    protected static CustList<RendDynOperationNode> getExecutableNodes(AnalyzedTestConfiguration _an, CustList<OperationNode> _ops) {
        return getExecutableNodes(_ops, _an.getForwards());
    }

    protected static OperationsSequence getOperationsSequence(int _offset, String el_, AnalyzedTestConfiguration ctx_, Delimiters d_) {
        return ElResolver.getOperationsSequence(_offset, el_, d_, ctx_.getAnalyzing());
    }

    protected static Delimiters checkSyntax(AnalyzedTestConfiguration ctx_, String elr_, int _off) {
        return ElResolver.checkSyntax(elr_, _off, ctx_.getAnalyzing());
    }

    protected static OperationNode getOperationNode(int _ind, byte _ch, MethodOperation _par, OperationsSequence opTwo_, AnalyzedTestConfiguration ctx_) {
        return OperationNode.createOperationNode(_ind, _ch, _par, opTwo_, ctx_.getAnalyzing());
    }

    protected static void setup(String folder_, String relative_, DualConfigurationContext conf_) {
        setup(folder_, conf_);
        conf_.getProperties().put("msg_example", relative_);
    }

    private static void setup(String folder_, DualConfigurationContext conf_) {
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
    }

    protected static String getCommRes(String html_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(files_, a_);
        assertTrue(isEmptyErrors(a_));
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        String res_ = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res_;
    }

    protected static String getRes(Configuration conf_, BeanLgNames _stds, ContextEl _context) {
        return RendBlock.getRes(conf_.getRendDocumentBlock(), conf_, _stds, _context);
    }

    protected static String getCommRes(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());

        setFiles(files_, conf_);
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        String res_ = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res_;
    }

    protected static String getRes(String folder_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, a_.getDual());
        setFiles(files_, conf_);

        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }

    protected static String getRes2(String folder_, String relative_, String html_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }

    protected static void setFirst(AnalyzedTestConfiguration cont_) {
        RendDocumentBlock doc_ = cont_.getConfiguration().getRenders().getVal("page1.html");
        cont_.getConfiguration().setRendDocumentBlock(doc_);
    }

    protected static Struct getCommEx(String html_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    private static AnalyzedTestContext buildStd(String... _types) {
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        for (String t: _types) {
            opt_.getTypesInit().add(t);
        }

        return InitializationLgNames.buildStdThree(opt_);
    }

    protected static Struct getCommEx(String folder_, String relative_, String html_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    protected static Struct getEx(String folder_, String html_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(new StringMap<String>(), a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, a_.getDual());
        setFiles(files_, conf_);

        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }
    protected static Struct getEx(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());

        setFiles(files_, conf_);
        
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    protected static boolean hasCommErr(String html_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(files_, a_);
        assertTrue(isEmptyErrors(a_));
        
        analyzeInner(conf_, a_, html_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErr(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());

        setFiles(files_, conf_);
        
        analyzeInner(conf_, a_, html_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErr(String folder_, String relative_, String html_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(files_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        analyzeInner(conf_, a_, html_);
        return !isEmptyErrors(a_);
    }



    protected static String getCommOneBean(String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        String res_ = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res_;
    }

    protected static String getCommOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_, String... _types) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_,_types);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        String res_ = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res_;
    }

    protected static Struct getCommExOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_, String... _types) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_,_types);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    protected static boolean hasCommErrOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrOneBean(String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrOneBean2(String folder_, String relative_, String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        return !isEmptyErrors(a_);
    }
    protected static String getAncOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        String res_ = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        assertEq(1, conf_.getHtmlPage().getAnchorsArgs().size());
        assertEq("2", conf_.getHtmlPage().getAnchorsArgs().last().last());
        return res_;
    }

    private static CustList<RendDynOperationNode> buildExecPart(AnalyzedTestConfiguration a_, String _bean_one) {
        CustList<RendDynOperationNode> ops2_ = new CustList<RendDynOperationNode>();
        int i_ = 0;
        for (EntryCust<String,BeanInfo> e: a_.getConfiguration().getBeansInfos().entryList()) {
            if (StringList.quickEq(e.getKey(),_bean_one)) {
                BeanInfo b_ = e.getValue();
                ops2_ = RendForwardInfos.getExecutableNodes(a_.getAnalyzingDoc().getBeansInfos().getKey(i_), a_.getForwards());
                b_.setExps(ops2_);
            }
            i_++;
        }
        return ops2_;
    }

    protected static AnalyzedTestConfiguration simulateNav2(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        String firstUrl_ = "page1.html";
        conf_.setFirstUrl(firstUrl_);
        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        conf_.setNavigation(new StringMap<StringMap<String>>());
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return a_;
    }

    protected static AnalyzedTestConfiguration simulateNav(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return a_;
    }

    protected static AnalyzedTestConfiguration simulateNavEx(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        setFiles(files_, conf_);

        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return a_;
    }


    protected static AnalyzedTestNavigation initSession(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_, a_);
    }

    protected static boolean initSessionFail(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_,nav_);
        return !isEmptyErrors(a_);
    }

    protected static AnalyzedTestNavigation initSession2(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestNavigation initSession3(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry("bean_one.validate",new StringMap<String>());
        conf_.getNavigation().getVal("bean_one.validate").addEntry("val1","page2.html");
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestNavigation initSession4(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestNavigation initSession44(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestNavigation initSessionSim(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _className, String _scope) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestNavigation initSessionSim2(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    private static void initializeRendSession(Navigation nav_, AnalyzedTestConfiguration _a) {
        nav_.initializeRendSession(_a.getContext(), _a.getAdvStandards());
    }

    protected static AnalyzedTestNavigation initSession5(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String _scope, String _className) {
        return initSession55(locale_, folder_, relative_, content_, html_, htmlTwo_, htmlThree_, filesSec_, "bean_one.click()", "page2.html", "page3.html", _scope, _className);
    }

    protected static AnalyzedTestNavigation initSession55(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String s, String s2, String s3, String session, String s4) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        files_.put("page3.html", htmlThree_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry(s,new StringMap<String>());
        conf_.getNavigation().getVal(s).addEntry("val1", s2);
        conf_.getNavigation().getVal(s).addEntry("val2", s3);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        a_.getDual().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(session);
        i_.setClassName(s4);
        nav_.getSession().getBeansInfos().addEntry("bean_one", i_);
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static AnalyzedTestConfiguration build(Configuration conf_,String... _types) {
        AnalyzedTestContext cont_ = buildStd(_types);
        return new AnalyzedTestConfiguration(conf_, cont_, cont_.getForwards(), cont_.getStds());
    }

    protected static AnalyzedTestNavigation initSession56(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        files_.put("page3.html", htmlThree_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry("bean_one.click()",new StringMap<String>());
        conf_.getNavigation().getVal("bean_one.click()").addEntry("val1", "page2.html");
        conf_.getNavigation().getVal("bean_one.click()").addEntry("val2", "page3.html");
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        a_.getDual().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanTwo");
        nav_.getSession().getBeansInfos().addEntry("bean_two",i_);
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static Navigation getStdNavigation(String locale_, String folder_, String relative_, StringMap<String> filesSec_, StringMap<String> files_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return nav_;
    }

    protected static AnalyzedTestNavigation getStdNavigation2(String locale_, String folder_, String relative_, StringMap<String> filesSec_, StringMap<String> files_, String _scope, String _className) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }


    protected static AnalyzedTestNavigation getStdNavigation2(String locale_, String folder_, String relative_, StringMap<String> filesSec_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return new AnalyzedTestNavigation(nav_,a_);
    }

    protected static Navigation getStdNavigation3(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        initializeRendSession(nav_, a_);
        return nav_;
    }

    protected static boolean getStdNavigation7(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String s) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl(s);
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        analyze(a_,nav_);
        return !isEmptyErrors(a_);
    }

    protected static boolean getStdNavigation6(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        conf_.setFirstUrl("page1.html");
        setup(folder_, relative_, a_.getDual());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("page");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(a_,nav_);
        return !isEmptyErrors(a_);
    }

    private static void addVal(Navigation _nav, String _valId, String _class) {
        ValidatorInfo v_ = new ValidatorInfo();
        v_.setClassName(_class);
        _nav.getSession().getLateValidators().addEntry(_valId,v_);
    }

    protected static void analyze(AnalyzedTestConfiguration _cont,Navigation _nav) {
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        AnalyzingDoc anaDoc_ = _cont.getAnalyzingDoc();
        anaDoc_.setup(_cont.getConfiguration(), _cont.getAdvStandards(), _cont.getDual());
        setupAna(anaDoc_, _cont.getAnalyzing());
        _nav.initInstancesPattern(_cont.getAnalyzing(), anaDoc_);
        AnalyzedPageEl _page = _cont.getAnalyzing();
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(_page, _cont.getAdvStandards(), anaDoc_.getRendAnalysisMessages(), anaDoc_, _cont.getDual());
        _cont.setAnalyzed(d_);
    }

    protected static Struct getExTwoPages(String folder_, String relative_, String html_, String htmlTwo_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(new StringMap<String>(), a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        
        analyzeInner(conf_, a_, html_, htmlTwo_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    protected static String getResTwoPages(String folder_, String relative_, String html_, String htmlTwo_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(new StringMap<String>(), a_);
        assertTrue(isEmptyErrors(a_));

        setup(folder_, relative_, a_.getDual());
        
        analyzeInner(conf_, a_, html_, htmlTwo_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        tryInitStaticlyTypes(a_);
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }

    protected static String getResTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        analyzeInner(conf_, a_, html_, htmlTwo_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(a_, "bean_two");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_two", bean_);
        conf_.clearPages();
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }

    protected static Struct getExTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        analyzeInner(conf_, a_, html_, htmlTwo_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(a_, "bean_two");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_two", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    protected static String getResTwoPagesOne(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        analyzeInner(conf_, a_, html_, htmlTwo_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }


    protected static Struct getExThreeBeans(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        newBeanInfo(a_, "pkg.BeanThree", "bean_three");
        analyzeInner(conf_, a_, html_, htmlTwo_, htmlThree_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(a_, "bean_two");
        CustList<RendDynOperationNode> ops3_ = buildExecPart(a_, "bean_three");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_two", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops3_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_three", bean_);
        conf_.clearPages();
        setFirst(a_);
        getRes(conf_, a_.getAdvStandards(), a_.getContext());
        return getException(a_);
    }

    private static void analyzeInner(Configuration conf_, AnalyzedTestConfiguration a_, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(conf_, a_, a_.getAnalyzingDoc(), _html);
        a_.setAnalyzed(d_);
    }

    private static StringMap<AnaRendDocumentBlock> analyze(Configuration conf_, AnalyzedTestConfiguration a_, AnalyzingDoc analyzingDoc_, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock("c:", doc_, h, a_.getAnalyzing().getPrimTypes(), conf_.getCurrentUrl(), conf_.getRendKeyWords());
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(a_, analyzingDoc_);
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(analyzingDoc_, a_.getAnalyzing());
        }
        return d_;
    }

    private static void newBeanInfo(AnalyzedTestConfiguration a_, String className, String bean_two) {
        BeanInfo b2_ = new BeanInfo();
        b2_.setClassName(className);
        b2_.setResolvedClassName(className);
        a_.getConfiguration().getBeansInfos().addEntry(bean_two, b2_);
        OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations("$new "+className+"()", 0, a_.getAnalyzingDoc(), a_.getAnalyzing());
        a_.getAnalyzingDoc().getBeansInfos().addEntry(root_,b2_);
    }

    protected static String getResThreeBean(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String _currentUrl) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        newBeanInfo(a_, "pkg.BeanThree", "bean_three");
        analyzeInner(conf_, a_, html_, htmlTwo_, htmlThree_);
        assertTrue(isEmptyErrors(a_));
        tryForward(a_);
        CustList<RendDynOperationNode> ops_ = buildExecPart(a_, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(a_, "bean_two");
        CustList<RendDynOperationNode> ops3_ = buildExecPart(a_, "bean_three");
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_two", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops3_, conf_, a_.getAdvStandards(), a_.getContext()).getStruct();
        conf_.getBuiltBeans().addEntry("bean_three", bean_);
        conf_.clearPages();
        conf_.setCurrentUrl(_currentUrl);
        setFirst(a_);
        String res = getRes(conf_, a_.getAdvStandards(), a_.getContext());
        assertNull(getException(a_));
        return res;
    }

    protected static boolean hasErrThree(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        newBeanInfo(a_, "pkg.BeanThree", "bean_three");
        analyzeInner(conf_, a_, html_, htmlTwo_, htmlThree_);
        buildExecPart(a_, "bean_one");
        buildExecPart(a_, "bean_two");
        buildExecPart(a_, "bean_three");
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        AnalyzedTestConfiguration a_ = build(conf_);
        getHeaders(filesSec_, a_);
        assertTrue(isEmptyErrors(a_));
        setup(folder_, relative_, a_.getDual());
        
        newBeanInfo(a_, "pkg.BeanOne", "bean_one");
        newBeanInfo(a_, "pkg.BeanTwo", "bean_two");
        analyzeInner(conf_, a_, html_, htmlTwo_);
        buildExecPart(a_, "bean_one");
        buildExecPart(a_, "bean_two");
        return !isEmptyErrors(a_);
    }

    private static CustList<RendDynOperationNode> getExecutableNodes(CustList<OperationNode> _list, Forwards _forwards) {
        OperationNode root_ = _list.last();
        return RendForwardInfos.getExecutableNodes(root_, _forwards);
    }

    protected static CustList<RendDynOperationNode> getReducedNodes(RendDynOperationNode _root) {
        CustList<RendDynOperationNode> out_ = new CustList<RendDynOperationNode>();
        RendDynOperationNode current_ = _root;
        while (current_ != null) {
            RendDynOperationNode op_ = current_.getFirstChild();
            if (op_ != null) {
                if (current_.getArgument() == null) {
                    current_ = op_;
                    continue;
                }
            }
            while (true) {
                current_.setOrder(out_.size());
                out_.add(current_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == _root) {
                    op_.setOrder(out_.size());
                    out_.add(op_);
                    current_ = null;
                    break;
                }
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                current_ = op_;
            }
        }
        return out_;
    }
}
