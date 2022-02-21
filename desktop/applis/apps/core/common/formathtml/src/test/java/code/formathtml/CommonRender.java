package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.analyze.files.DefaultAccessType;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecAbstractFileBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.DefExecFileListBuilder;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.WarningShow;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.VirtualImportingBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.Classes;
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
import code.util.core.IndexConstants;

public abstract class CommonRender extends EquallableRenderUtil {

    protected static void addInnerPage(AnalyzedTestConfiguration _conf) {
        _conf.getRendStackCall().addPage(new ImportingPage());
    }

    protected static Navigation newNavigation(AnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());

        return nav_;
    }

    protected static void setupAnalyzing(AnalyzedTestConfiguration _analyzing, AnalyzingDoc _analyzingDoc) {

        String globalClass_ = _analyzing.getAnalyzing().getGlobalClass();
        setupAna(_analyzingDoc, _analyzing.getAnalyzing());
        _analyzing.getAnalyzing().setGlobalType(new AnaFormattedRootBlock(_analyzing.getAnalyzing(),globalClass_));
        for (EntryCust<String, LocalVariable> e: _analyzing.getLocalVariables().entryList()) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getValue().getClassName());
            a_.setConstType(ConstType.LOC_VAR);
            _analyzing.getAnalyzing().getInfosVars().put(e.getKey(), a_);
        }
        for (EntryCust<String,LoopVariable> e: _analyzing.getVars().entryList()) {
            AnaLoopVariable a_ = new AnaLoopVariable();
            a_.setIndexClassName(e.getValue().getIndexClassName());
            _analyzing.getAnalyzing().getLoopsVars().put(e.getKey(), a_);
        }
    }

    protected static void setupValues(AnalyzedTestConfiguration _analyzing, ImportingPage _lastPage, ContextEl _context) {
        for (EntryCust<String, LocalVariable> e: _analyzing.getLocalVariables().entryList()) {
            _lastPage.getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
        }
        for (EntryCust<String,LoopVariable> e: _analyzing.getVars().entryList()) {
            _lastPage.getPageEl().getVars().addEntry(e.getKey(),e.getValue());
        }
        _lastPage.setGlobalArgumentStruct(_analyzing.getArgument().getStruct(), _context);
    }
    protected static void setLocalVars(AnalyzedTestConfiguration _importingPage, StringMap<LocalVariable> _localVars) {
//        for (EntryCust<String, LocalVariable> e: _localVars.entryList()) {
//            _importingPage.getLastPage().getPageEl().getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
//        }
        _importingPage.getLocalVariables().addAllEntries(_localVars);
    }

    protected static boolean isEmptyErrors(AnalyzedTestConfiguration _cont) {
        return _cont.getAnalyzing().isEmptyErrors();
    }

    protected static void getHeaders(StringMap<String> _files, AnalyzedTestConfiguration _cont) {
        Classes.validateWithoutInit(_files, _cont.getAnalyzing());
    }

    protected static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        AnalyzingDoc.setupInts(_page, _analyzingDoc);
    }

    private static void setLocalFiles(AnalyzedTestConfiguration _context, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = _context.getAnalyzing();
        Configuration conf_ = _context.getConfiguration();
        _analyzingDoc.setup(conf_, _context.getDual());
        setInnerLocalFilesLg(_analyzingDoc, analyzing_, conf_);
    }

    private static void setInnerLocalFilesLg(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing, Configuration _conf) {
        _conf.setCurrentLanguage("en");
        setInnerLocalFiles(_analyzingDoc, _analyzing);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }

    protected static void tryInitStaticlyTypes(ContextEl _ctx, AnalyzedTestConfiguration _context) {
        ExecClassesUtil.tryInitStaticlyTypes(_ctx, _context.getOpt());
//        addInnerPage(_context);
    }

    protected static ContextEl tryForward(AnalyzedTestConfiguration _context) {
        return _context.getAdvStandards().forwardAndClear(_context.getOpt(), _context.getForwards());
    }

    protected static void simpleForward(AnalyzedTestConfiguration _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        CustList<ExecAbstractFileBlock> rendFiles_ = BeanCustLgNames.execFiles(_context.getAnalyzed());
        ForwardInfos.generalForward(page_, _context.getForwards(), new DefExecFileListBuilder(page_, _context.getForwards()));
        RendForwardInfos.buildExec(_context.getAnalyzingDoc(),rendFiles_, _context.getAnalyzed(), _context.getForwards(), _context.getConfiguration());
    }

    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getStruct();
    }
    protected static void setStruct(Struct _struct, ClassField _cl, Struct _value) {
        ((FieldableStruct) _struct).getEntryStruct(_cl).setStruct(_value);
    }

    protected static Struct getException(AnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getRendStackCall().getStackCall().getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static void setFiles(StringMap<String> _filesThree, AnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFiles(_filesThree);
    }

    protected static CustList<OperationNode> getQuickAnalyzed(String _el, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc) {
        _analyzingDoc.setup(_conf.getConfiguration(), _conf.getDual());
        setupAnalyzing(_conf, _conf.getAnalyzingDoc());
        Argument argGl_ = _conf.getArgument();
        boolean static_ = argGl_.isNull();
        _conf.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(_conf, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _conf, d_, _el);
        OperationNode op_ = rendOp(0, _conf, opTwo_);
        return getSortedDescNodes(_conf, op_);
    }

    protected static OperationNode rendOp(int _i, AnalyzedTestConfiguration _conf, OperationsSequence _opTwo) {
        return RenderAnalysis.createOperationNode(_i, IndexConstants.FIRST_INDEX, null, _opTwo, _conf.getAnalyzingDoc(), _conf.getAnalyzing());
    }

    protected static OperationsSequence rendOpSeq(int _i, AnalyzedTestConfiguration _conf, Delimiters _d, String _el) {
        return RenderAnalysis.getOperationsSequence(_i, _el, _d, _conf.getAnalyzingDoc(), _conf.getAnalyzing());
    }

    protected static CustList<OperationNode> getSortedDescNodes(AnalyzedTestConfiguration _conf, OperationNode _op) {
        return RenderAnalysis.getSortedDescNodes(_op, _conf.getAnalyzingDoc(), _conf.getAnalyzing());
    }

    protected static OperationsSequence getOperationsSequence(int _offset, String _el, AnalyzedTestConfiguration _ctx, Delimiters _d) {
        return ElResolver.getOperationsSequence(_offset, _el, _d, _ctx.getAnalyzing());
    }

    protected static Delimiters checkSyntax(AnalyzedTestConfiguration _ctx, String _elr) {
        ResultExpression res_ = new ResultExpression();
        AnalyzedPageEl analyzing_ = _ctx.getAnalyzing();
        analyzing_.setCurrentParts(res_.getParts());
        return ElResolver.checkSyntax(_elr, 0, analyzing_);
    }

    protected static OperationNode getOperationNode(int _ind, byte _ch, MethodOperation _par, OperationsSequence _opTwo, AnalyzedTestConfiguration _ctx) {
        return OperationNode.createOperationNode(_ind, _ch, _par, _opTwo, _ctx.getAnalyzing());
    }

    protected static void setup(String _folder, String _relative, AnalyzedTestConfiguration _conf) {
        setup(_folder, _conf);
        _conf.getDual().getProperties().put("msg_example", _relative+".properties");
    }

    private static void setup(String _folder, AnalyzedTestConfiguration _conf) {
        _conf.getDual().setMessagesFolder(_folder);
        _conf.getDual().setProperties(new StringMap<String>());
    }

    protected static String getCommRes(String _html, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getString(a_);
    }

    private static String getString(AnalyzedTestConfiguration _a) {
        simpleForward(_a);
        ContextEl ctx_ = tryFwdInit(_a);
        return successRes(ctx_,_a);
    }

    private static AnalyzedTestConfiguration validateBase(StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = build();
        getHeaders(_files, a_);
        assertTrue(isEmptyErrors(a_));
        return a_;
    }

    protected static String getCommRes(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);

        setup(_folder, _relative, _filesThree, a_);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getString(a_);
    }

    protected static String getRes(String _folder, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);

        setup(_folder, a_);
        setFiles(_filesThree, a_);

        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getString(a_);
    }

    protected static String getRes2(String _folder, String _relative, String _html, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);
        setup(_folder, _relative, a_);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getString(a_);
    }

    protected static void setFirst(AnalyzedTestConfiguration _cont) {
        RendDocumentBlock doc_ = _cont.getConfiguration().getRenders().getVal("page1.html");
        _cont.getConfiguration().setRendDocumentBlock(doc_);
    }

    protected static Struct getCommEx(String _html, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_);
    }

    private static AnalyzedTestContextRender buildStd(String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        opt_.setWarningShow(warningShow_);
        opt_.getTypesInit().addAllElts(new StringList(_types));

        return InitializationLgNamesRender.buildStdThree(opt_);
    }

    private static Options newOptions() {
        Options options_ = new Options();
        DefaultAccess defaultAccess_ = options_.getDefaultAccess();
        setup(defaultAccess_.getAccAnonymous(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccClass(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccEnum(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccInnerEnum(),AccessEnum.PACKAGE);
        setup(defaultAccess_.getAccAnnotation(),AccessEnum.PUBLIC);
        setup(defaultAccess_.getAccInterface(),AccessEnum.PUBLIC);
        defaultAccess_.setAccOuter(AccessEnum.PACKAGE);
        return options_;
    }
    private static void setup(DefaultAccessType _def, AccessEnum _value) {
        _def.setAccLocalTypes(_value);
        _def.setAccMember(_value);
        _def.setAccInners(_value);
    }
    protected static Struct getCommEx(String _folder, String _relative, String _html, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);
        setup(_folder, _relative, a_);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_);
    }

    private static Struct getStruct(AnalyzedTestConfiguration _a) {
        simpleForward(_a);
        ContextEl ctx_ = tryFwdInit(_a);
        return failRes(ctx_,_a);
    }

    protected static Struct getEx(String _folder, String _html, StringMap<String> _filesThree) {
        AnalyzedTestConfiguration a_ = validateBase(new StringMap<String>());

        setup(_folder, a_);
        setFiles(_filesThree, a_);

        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_);
    }
    protected static Struct getEx(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);

        setup(_folder, _relative, _filesThree, a_);
        
        analyzeInner(a_, _html);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_);
    }

    protected static boolean hasCommErr(String _html, StringMap<String> _filesThree) {
        AnalyzedTestConfiguration a_ = validateBase(_filesThree);
        
        analyzeInner(a_, _html);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErr(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        AnalyzedTestConfiguration a_ = validateBase(_files);

        setup(_folder, _relative, _filesThree, a_);
        
        analyzeInner(a_, _html);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErr(String _folder, String _relative, String _html, StringMap<String> _filesThree) {
        AnalyzedTestConfiguration a_ = validateBase(_filesThree);
        setup(_folder, _relative, a_);
        
        analyzeInner(a_, _html);
        return !isEmptyErrors(a_);
    }



    protected static String getCommOneBean(String _html, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        ContextEl ctx_ = calcOneBean(_html, a_);

        return successRes(ctx_, a_);
    }

    protected static String getCommOneBeanParam(String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = build();
        getHeaders(_filesSec, a_);
        assertTrue(isEmptyErrors(a_));
        setFiles(_filesThree, a_);
        ContextEl ctx_ = calcOneBeanParam(_html, a_);

        return successRes(ctx_, a_);
    }

    protected static String getCommOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec, String... _types) {
        AnalyzedTestConfiguration a_ = build(_types);
        getHeaders(_filesSec, a_);
        assertTrue(isEmptyErrors(a_));

        setup(_folder, _relative, _filesThree, a_);

        ContextEl ctx_ = calcOneBean(_html, a_);

        return successRes(ctx_, a_);
    }

    protected static Struct getCommExOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec, String... _types) {
        AnalyzedTestConfiguration a_ = build(_types);
        getHeaders(_filesSec, a_);
        assertTrue(isEmptyErrors(a_));
        setup(_folder, _relative, _filesThree, a_);

        ContextEl ctx_ = calcOneBean(_html, a_);
        return failRes(ctx_, a_);
    }

    protected static boolean hasCommErrOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setup(_folder, _relative, _filesThree, a_);

        errOneBean(_html, a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrOneBean(String _html, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        errOneBean(_html, a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrOneBean2(String _folder, String _relative, String _html, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setup(_folder, _relative, a_);

        errOneBean(_html, a_);
        return !isEmptyErrors(a_);
    }

    private static void errOneBean(String _html, AnalyzedTestConfiguration _a) {
        newOneBean(_a);
        analyzeInner(_a, _html);
        buildExecPart(_a, "bean_one");
    }

    protected static String getAncOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setup(_folder, _relative, _filesThree, a_);

        ContextEl ctx_ = calcOneBean(_html, a_);

        String res_ = successRes(ctx_, a_);
        assertEq(1, a_.getRendStackCall().getHtmlPage().getAnchorsArgs().size());
        assertEq("2", a_.getRendStackCall().getHtmlPage().getAnchorsArgs().last().last());
        return res_;
    }

    private static ContextEl calcOneBean(String _html, AnalyzedTestConfiguration _a) {
        newOneBean(_a);
        analyzeInner(_a, _html);
        assertTrue(isEmptyErrors(_a));
        simpleForward(_a);
        CustList<RendDynOperationNode> ops_ = buildExecPart(_a, "bean_one");
        ContextEl ctx_ = tryFwdInit(_a);
        calcBean(ctx_,_a, ops_, "bean_one");
        return ctx_;
    }

    private static ContextEl calcOneBeanParam(String _html, AnalyzedTestConfiguration _a) {
        newOneBeanParam(_a);
        analyzeInner(_a, _html);
        assertTrue(isEmptyErrors(_a));
        simpleForward(_a);
        CustList<RendDynOperationNode> ops_ = buildExecPart(_a, "bean_one");
        ContextEl ctx_ = tryFwdInit(_a);
        calcBean(ctx_,_a, ops_, "bean_one");
        return ctx_;
    }

    protected static ContextEl tryFwdInit(AnalyzedTestConfiguration _a) {
        ContextEl ctx_ = tryForward(_a);
        tryInitStaticlyTypes(ctx_,_a);
        return ctx_;
    }

    private static void newOneBean(AnalyzedTestConfiguration _a) {
        newBeanInfo(_a, "pkg.BeanOne", "bean_one");
    }

    private static void newOneBeanParam(AnalyzedTestConfiguration _a) {
        newBeanInfo(_a, "pkg.BeanOne<$int>", "bean_one");
    }

    private static void setup(String _folder, String _relative, StringMap<String> _filesThree, AnalyzedTestConfiguration _a) {
        setup(_folder, _relative, _a);
        setFiles(_filesThree, _a);
    }

    private static CustList<RendDynOperationNode> buildExecPart(AnalyzedTestConfiguration _a, String _bean) {
        StringMap<BeanInfo> beansInfos_ = _a.getConfiguration().getBeansInfos();
        int i_ = beansInfos_.indexOfEntry(_bean);
        BeanInfo b_ = beansInfos_.getValue(i_);
        CustList<RendDynOperationNode> ops_ = RendForwardInfos.getExecutableNodes(_a.getAnalyzingDoc().getBeansInfos().getKey(i_), _a.getForwards());
        b_.setExps(ops_);
        return ops_;
    }

    protected static AnalyzedTestConfiguration simulateNav2(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setFirst(a_, "page1.html");
        setup(_folder, _relative, _filesThree, a_);
        setNavigation(a_);

        ContextEl ctx_ = calcOneBean(_html, a_);
        successRes(ctx_, a_);
        a_.setContext(ctx_);
        return a_;
    }

    protected static void setFirst(AnalyzedTestConfiguration _conf, String _firstUrl) {
        _conf.getConfiguration().setFirstUrl(_firstUrl);
    }

    protected static void setFirst(AnalyzedTestConfigurationBis _conf, String _firstUrl) {
        _conf.getConfiguration().setFirstUrl(_firstUrl);
    }

    protected static AnalyzedTestConfiguration simulateNav(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setup(_folder, _relative, _filesThree, a_);

        ContextEl ctx_ = calcOneBean(_html, a_);

        successRes(ctx_, a_);
        a_.setContext(ctx_);
        return a_;
    }

    protected static AnalyzedTestConfiguration simulateNavEx(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);

        setup(_folder, _relative, _filesThree, a_);

        ContextEl ctx_ = calcOneBean(_html, a_);

        successRes(ctx_, a_);
        a_.setContext(ctx_);
        return a_;
    }


    protected static AnalyzedTestNavigation initSession(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static boolean initSessionFail(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_,nav_);
        return !isEmptyErrors(a_);
    }

    protected static AnalyzedTestNavigation initSession2(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static AnalyzedTestNavigation initSession3(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        setNavigation(a_);
        a_.getNavigation().addEntry("bean_one.validate",new StringMap<String>());
        a_.getNavigation().getVal("bean_one.validate").addEntry("val1","page2.html");
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    private static AnalyzedTestNavigation nav(AnalyzedTestConfiguration _a, Navigation _nav) {
        ContextEl ctx_ = nav2(_a, _nav);
        return new AnalyzedTestNavigation(ctx_, _nav, _a);
    }

    private static ContextEl nav2(AnalyzedTestConfiguration _a, Navigation _nav) {
        simpleForward(_a);
        ContextEl ctx_ = tryFwdInit(_a);
        initializeRendSession(ctx_,_nav, _a);
        return ctx_;
    }

    protected static AnalyzedTestNavigation initSession4(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        filesThree_.put("page2.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static AnalyzedTestNavigation initSession44(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        filesThree_.put("page2.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static AnalyzedTestNavigation initSessionSim(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _className, String _scope) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);
        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static AnalyzedTestNavigation initSessionSim2(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    private static void initializeRendSession(ContextEl _ctx,Navigation _nav, AnalyzedTestConfiguration _a) {
        RendStackCall build_ = _a.build(InitPhase.NOTHING, _ctx);
        _nav.initializeRendSession(_ctx, _a.getAdvStandards(), build_);
    }

    protected static AnalyzedTestNavigation initSession5(String _locale, String _folder, String _relative, String _content, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec, String _scope, String _className) {
        return initSession55(_locale, _folder, _relative, _content, _html, _htmlTwo, _htmlThree, _filesSec, "bean_one.click()", "page2.html", "page3.html", _scope, _className);
    }

    protected static AnalyzedTestNavigation initSession55(String _locale, String _folder, String _relative, String _content, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec, String _s, String _s2, String _s3, String _session, String _s4) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        filesThree_.put("page2.html", _htmlTwo);
        filesThree_.put("page3.html", _htmlThree);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        setNavigation(a_);
        a_.getNavigation().addEntry(_s,new StringMap<String>());
        a_.getNavigation().getVal(_s).addEntry("val1", _s2);
        a_.getNavigation().getVal(_s).addEntry("val2", _s3);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        a_.getDual().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_session);
        i_.setClassName(_s4);
        addBeanInfo(nav_, i_, "bean_one");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static AnalyzedTestConfiguration build(Configuration _conf,String... _types) {
        AnalyzedTestContextRender cont_ = buildStd(_types);
        return new AnalyzedTestConfiguration(_conf, cont_, cont_.getForwards(), cont_.getStds());
    }

    protected static AnalyzedTestNavigation initSession56(String _locale, String _folder, String _relative, String _content, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        filesThree_.put("page2.html", _htmlTwo);
        filesThree_.put("page3.html", _htmlThree);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        setNavigation(a_);
        a_.getNavigation().addEntry("bean_one.click()",new StringMap<String>());
        a_.getNavigation().getVal("bean_one.click()").addEntry("val1", "page2.html");
        a_.getNavigation().getVal("bean_one.click()").addEntry("val2", "page3.html");
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        a_.getDual().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        addBeanInfo(nav_, i_, "bean_one");
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanTwo");
        addBeanInfo(nav_, i_, "bean_two");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static Navigation getStdNavigation(String _locale, String _folder, String _relative, StringMap<String> _filesSec, StringMap<String> _filesThree, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        nav2(a_, nav_);
        return nav_;
    }

    protected static AnalyzedTestNavigation getStdNavigation2(String _locale, String _folder, String _relative, StringMap<String> _filesSec, StringMap<String> _filesThree, String _scope, String _className) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }


    protected static AnalyzedTestNavigation getStdNavigation2(String _locale, String _folder, String _relative, StringMap<String> _filesSec, StringMap<String> _filesThree) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        analyze(a_,nav_);
        assertTrue(isEmptyErrors(a_));
        return nav(a_, nav_);
    }

    protected static Navigation getStdNavigation3(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDual().getRenderFiles().add("page1.html");
        analyze(a_, nav_);
        assertTrue(isEmptyErrors(a_));
        nav2(a_, nav_);
        return nav_;
    }

    protected static boolean getStdNavigation7(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _s) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, _s);
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        analyze(a_,nav_);
        return !isEmptyErrors(a_);
    }

    protected static boolean getStdNavigation6(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setFirst(a_, "page1.html");
        setup(_folder, _relative, a_);
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDual().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("page");
        i_.setClassName("pkg.BeanOne");
        addBeanInfo(nav_, i_, "bean_one");
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
        anaDoc_.setup(_cont.getConfiguration(), _cont.getDual());
        setupAna(anaDoc_, _cont.getAnalyzing());
        _nav.initInstancesPattern(_cont.getAnalyzing(), anaDoc_);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, _cont.getAdvStandards(), anaDoc_, _cont.getDual());
        _cont.setAnalyzed(d_);
    }

    protected static Struct getExTwoPages(String _folder, String _relative, String _html, String _htmlTwo) {
        AnalyzedTestConfiguration a_ = validateBase(new StringMap<String>());

        setup(_folder, _relative, a_);
        
        analyzeInner(a_, _html, _htmlTwo);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_);
    }

    protected static String getResTwoPages(String _folder, String _relative, String _html, String _htmlTwo) {
        AnalyzedTestConfiguration a_ = validateBase(new StringMap<String>());

        setup(_folder, _relative, a_);
        
        analyzeInner(a_, _html, _htmlTwo);
        assertTrue(isEmptyErrors(a_));
        return getString(a_);
    }

    protected static String getResTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);
        ContextEl ctx_ = calcTwoPagesTwoBean(_html, _htmlTwo, a_);

        return successRes(ctx_, a_);
    }

    protected static Struct getExTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);

        ContextEl ctx_ = calcTwoPagesTwoBean(_html, _htmlTwo, a_);

        return failRes(ctx_, a_);
    }

    private static ContextEl calcTwoPagesTwoBean(String _html, String _htmlTwo, AnalyzedTestConfiguration _a) {
        newTwoBean(_a);
        analyzeInner(_a, _html, _htmlTwo);
        assertTrue(isEmptyErrors(_a));
        simpleForward(_a);
        CustList<RendDynOperationNode> ops_ = buildExecPart(_a, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(_a, "bean_two");
        ContextEl ctx_ = tryFwdInit(_a);
        calcBean(ctx_,_a, ops_, "bean_one");
        calcBean(ctx_,_a, ops2_, "bean_two");
        return ctx_;
    }

    protected static String getResTwoPagesOne(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);
        ContextEl ctx_ = calcTwoPagesOneBean(_html, _htmlTwo, a_);

        return successRes(ctx_, a_);
    }

    private static ContextEl calcTwoPagesOneBean(String _html, String _htmlTwo, AnalyzedTestConfiguration _a) {
        newOneBean(_a);
        analyzeInner(_a, _html, _htmlTwo);
        assertTrue(isEmptyErrors(_a));
        simpleForward(_a);
        CustList<RendDynOperationNode> ops_ = buildExecPart(_a, "bean_one");
        ContextEl ctx_ = tryFwdInit(_a);
        calcBean(ctx_, _a, ops_, "bean_one");
        return ctx_;
    }


    protected static Struct getExThreeBeans(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);

        ContextEl ctx_ = calcThree(_html, _htmlTwo, _htmlThree, a_);

        return failRes(ctx_, a_);
    }

    private static ContextEl calcThree(String _html, String _htmlTwo, String _htmlThree, AnalyzedTestConfiguration _a) {
        newThreeBean(_a);
        analyzeInner(_a, _html, _htmlTwo, _htmlThree);
        assertTrue(isEmptyErrors(_a));
        simpleForward(_a);
        CustList<RendDynOperationNode> ops_ = buildExecPart(_a, "bean_one");
        CustList<RendDynOperationNode> ops2_ = buildExecPart(_a, "bean_two");
        CustList<RendDynOperationNode> ops3_ = buildExecPart(_a, "bean_three");
        ContextEl ctx_ = tryFwdInit(_a);
        calcBean(ctx_,_a, ops_, "bean_one");
        calcBean(ctx_,_a, ops2_, "bean_two");
        calcBean(ctx_,_a, ops3_, "bean_three");
        return ctx_;
    }

    private static void newThreeBean(AnalyzedTestConfiguration _a) {
        newTwoBean(_a);
        newBeanInfo(_a, "pkg.BeanThree", "bean_three");
    }

    private static void calcBean(ContextEl _ctx,AnalyzedTestConfiguration _a, CustList<RendDynOperationNode> _ops, String _bean) {
        Struct bean_ = calculateReuse(_ctx,_a, _ops);
        addBean(_a, bean_, _bean);
    }

    private static void analyzeInner(AnalyzedTestConfiguration _a, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(_a, _a.getAnalyzingDoc(), _html);
        _a.setAnalyzed(d_);
    }

    private static StringMap<AnaRendDocumentBlock> analyze(AnalyzedTestConfiguration _a, AnalyzingDoc _analyzingDoc, String... _html) {
        int c_ = 1;
        Configuration conf_ = _a.getConfiguration();
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock(c_ -1,"c:", doc_, h, _a.getAnalyzing().getPrimTypes(), "page"+c_+".html", conf_.getRendKeyWords());
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(_a, _analyzingDoc);
        _analyzingDoc.setDocs(d_);
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(_analyzingDoc, _a.getAnalyzing(), _analyzingDoc.getBeansInfosBefore());
        }
        return d_;
    }

    private static void newBeanInfo(AnalyzedTestConfiguration _a, String _className, String _bean) {
        _a.getAnalyzing().setImportingAcces(new VirtualImportingBlock());
        BeanInfo b2_ = new BeanInfo();
        b2_.setClassName(_className);
        b2_.setResolvedClassName(_className);
        _a.getConfiguration().getBeansInfos().addEntry(_bean, b2_);
        ResultExpression res_ = new ResultExpression();
        OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations("$new "+_className+"()", 0, _a.getAnalyzingDoc(), _a.getAnalyzing(),res_);
        _a.getAnalyzingDoc().getBeansInfos().addEntry(root_,b2_);
    }

    protected static String getResThreeBean(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);

        ContextEl ctx_ = calcThree(_html, _htmlTwo, _htmlThree, a_);


        return successRes(ctx_, a_);
    }

    protected static boolean hasErrThree(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);

        errThreeBean(_html, _htmlTwo, _htmlThree, a_);
        return !isEmptyErrors(a_);
    }

    private static void errThreeBean(String _html, String _htmlTwo, String _htmlThree, AnalyzedTestConfiguration _a) {
        newThreeBean(_a);
        analyzeInner(_a, _html, _htmlTwo, _htmlThree);
        buildExecPart(_a, "bean_one");
        buildExecPart(_a, "bean_two");
        buildExecPart(_a, "bean_three");
    }

    protected static boolean hasErrTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        AnalyzedTestConfiguration a_ = validateBase(_filesSec);
        setup(_folder, _relative, a_);

        errTwoBean(_html, _htmlTwo, a_);
        return !isEmptyErrors(a_);
    }

    private static void errTwoBean(String _html, String _htmlTwo, AnalyzedTestConfiguration _a) {
        newTwoBean(_a);
        analyzeInner(_a, _html, _htmlTwo);
        buildExecPart(_a, "bean_one");
        buildExecPart(_a, "bean_two");
    }

    private static void newTwoBean(AnalyzedTestConfiguration _a) {
        newOneBean(_a);
        newBeanInfo(_a, "pkg.BeanTwo", "bean_two");
    }

    private static void addBeanInfo(Navigation _nav, BeanInfo _i, String _bean) {
        _nav.getSession().getBeansInfos().addEntry(_bean,_i);
    }

    private static void addBean(AnalyzedTestConfiguration _conf, Struct _bean, String _beanName) {
        _conf.getConfiguration().getBuiltBeans().addEntry(_beanName, _bean);
    }

    protected static Struct calculateReuse(ContextEl _ctx,AnalyzedTestConfiguration _a, CustList<RendDynOperationNode> _ops) {
        RendStackCall build_ = _a.build(InitPhase.NOTHING, _ctx);
        build_.addPage(new ImportingPage());
        setupValues(_a,build_.getLastPage(), _ctx);
        return RenderExpUtil.calculateReuse(_ops, _a.getAdvStandards(), _ctx, build_).getStruct();
    }

    private static void setNavigation(AnalyzedTestConfiguration _conf) {
        _conf.setNavigation(new StringMap<StringMap<String>>());
    }

    protected static AnalyzedTestConfiguration build() {
        return build(new String[0]);
    }

    private static AnalyzedTestConfiguration build(String[] _types) {
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c:");
        return build(conf_,_types);
    }

    private static String successRes(ContextEl _ctx, AnalyzedTestConfiguration _a) {
        RendStackCall build_ = _a.build(InitPhase.NOTHING, _ctx);
        String res_ = res(_ctx,_a, build_);
        assertNull(build_.getStackCall().getCallingState());
        return res_;
    }

    private static Struct failRes(ContextEl _ctx, AnalyzedTestConfiguration _a) {
        RendStackCall build_ = _a.build(InitPhase.NOTHING, _ctx);
        res(_ctx,_a, build_);
        CallingState str_ = build_.getStackCall().getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static String res(ContextEl _ctx, AnalyzedTestConfiguration _a, RendStackCall _rendStackCall) {
        setFirst(_a);
        Configuration conf_ = _a.getConfiguration();
        BeanCustLgNames stds_ = _a.getAdvStandards();
        _rendStackCall.clearPages();
        return BeanCustLgNames.getRes(conf_.getRendDocumentBlock(), conf_, stds_, _ctx, _rendStackCall);
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
