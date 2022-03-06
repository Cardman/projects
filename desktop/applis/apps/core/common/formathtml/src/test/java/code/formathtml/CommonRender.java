package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
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
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class CommonRender extends EquallableRenderUtil {

    protected static void addInnerPage(RendStackCall _rendStackCall) {
        _rendStackCall.addPage(new ImportingPage());
    }

    protected static Navigation newNavigation(DualNavigationContext _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getNavigation().getSession());

        return nav_;
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

    protected static boolean isEmptyErrors(AnalyzedTestConfiguration _cont) {
        return isEmptyErrors(_cont.getAnalyzing());
    }

    protected static boolean isEmptyErrors(DualNavigationContext _cont) {
        return isEmptyErrors(_cont.getDualAnalyzedContext().getAnalyzed());
    }

    protected static boolean isEmptyErrors(AnalyzedPageEl _cont) {
        return _cont.isEmptyErrors();
    }

    protected static void getHeaders(StringMap<String> _files, AnalyzedTestConfiguration _cont) {
        Classes.validateWithoutInit(_files, _cont.getAnalyzing());
    }

    protected static void getHeaders(StringMap<String> _files, DualNavigationContext _cont) {
        Classes.validateWithoutInit(_files, _cont.getDualAnalyzedContext().getAnalyzed());
    }

    protected static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        AnalyzingDoc.setupInts(_page, _analyzingDoc);
    }

    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getStruct();
    }
    protected static void setStruct(Struct _struct, ClassField _cl, Struct _value) {
        ((FieldableStruct) _struct).getEntryStruct(_cl).setStruct(_value);
    }

    protected static Struct getException(RendStackCall _cont) {
        CallingState str_ = _cont.getStackCall().getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static void setFiles(StringMap<String> _filesThree, Configuration _configuration) {
        _configuration.setFiles(_filesThree);
    }

    protected static CustList<OperationNode> getQuickAnalyzed(String _el, DualNavigationContext _conf, AnalyzingDoc _analyzingDoc) {
        _analyzingDoc.setup(_conf.getNavigation().getSession(), _conf.getDualAnalyzedContext().getContext());
        StringMap<LocalVariable> localVariables_ = new StringMap<LocalVariable>();
        StringMap<LoopVariable> vars_ = new StringMap<LoopVariable>();
        setupAnalyzing(_conf, _analyzingDoc, localVariables_, vars_);
//        Argument argGl_ = _conf.getArgument();
        boolean static_ = true;
        _conf.getDualAnalyzedContext().getAnalyzed().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(_conf, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _conf, d_, _el, _analyzingDoc);
        OperationNode op_ = rendOp(0, _conf, opTwo_, _analyzingDoc);
        return getSortedDescNodes(_conf, op_, _analyzingDoc);
    }

    protected static OperationNode rendOp(int _i, DualNavigationContext _conf, OperationsSequence _opTwo, AnalyzingDoc analyzingDoc) {
        return RenderAnalysis.createOperationNode(_i, IndexConstants.FIRST_INDEX, null, _opTwo, analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed());
    }

    protected static OperationsSequence rendOpSeq(int _i, DualNavigationContext _conf, Delimiters _d, String _el, AnalyzingDoc analyzingDoc) {
        return RenderAnalysis.getOperationsSequence(_i, _el, _d, analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed());
    }

    protected static CustList<OperationNode> getSortedDescNodes(DualNavigationContext _conf, OperationNode _op, AnalyzingDoc analyzingDoc) {
        return RenderAnalysis.getSortedDescNodes(_op, analyzingDoc, _conf.getDualAnalyzedContext().getAnalyzed());
    }

    protected static OperationsSequence getOperationsSequence(int _offset, String _el, DualNavigationContext _ctx, Delimiters _d) {
        return ElResolver.getOperationsSequence(_offset, _el, _d, _ctx.getDualAnalyzedContext().getAnalyzed());
    }

    protected static Delimiters checkSyntax(DualNavigationContext _ctx, String _elr) {
        ResultExpression res_ = new ResultExpression();
        AnalyzedPageEl analyzing_ = _ctx.getDualAnalyzedContext().getAnalyzed();
        analyzing_.setCurrentParts(res_.getParts());
        return ElResolver.checkSyntax(_elr, 0, analyzing_);
    }

    protected static OperationNode getOperationNode(int _ind, byte _ch, MethodOperation _par, OperationsSequence _opTwo, DualNavigationContext _ctx) {
        return OperationNode.createOperationNode(_ind, _ch, _par, _opTwo, _ctx.getDualAnalyzedContext().getAnalyzed());
    }

    protected static void setup(String _folder, String _relative, DualConfigurationContext _dual) {
        setup(_folder, _dual);
        _dual.getProperties().put("msg_example", _relative+".properties");
    }

    private static void setup(String _folder, DualConfigurationContext _dual) {
        _dual.setMessagesFolder(_folder);
        _dual.setProperties(new StringMap<String>());
    }

    protected static String getCommRes(String _html, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();

        ContextEl ctx_ = ana(_files, oneFile(_html), a_);
        assertTrue(isEmptyErrors(a_));
        return getString(a_, ctx_);
    }

    private static String getString(DualNavigationContext _a, ContextEl _analyzed) {
        return successRes(_analyzed,_a);
    }

    protected static String getCommRes(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, _filesThree, a_);
        ContextEl ctx_ = ana(_files, filRend(oneFile(_html), _filesThree), a_);
        assertTrue(isEmptyErrors(a_));
        return getString(a_, ctx_);
    }

    protected static String getRes(String _folder, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, a_.getDualAnalyzedContext().getContext());
        setFiles(_filesThree, a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_files, filRend(oneFile(_html), _filesThree), a_);
        assertTrue(isEmptyErrors(a_));
        return getString(a_, ctx_);
    }

    protected static String getRes2(String _folder, String _relative, String _html, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        ContextEl ctx_ = ana(_files, oneFile(_html), a_);
        assertTrue(isEmptyErrors(a_));
        return getString(a_, ctx_);
    }

    protected static void setFirst(Configuration _configuration) {
        RendDocumentBlock doc_ = _configuration.getRenders().getVal("page1.html");
        _configuration.setRendDocumentBlock(doc_);
    }

    protected static Struct getCommEx(String _html, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        ContextEl ctx_ = ana(_files, oneFile(_html), a_);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_, ctx_);
    }

    protected static ContextEl ana(StringMap<String> _files, StringMap<String> _rend, DualNavigationContext _dualNav) {
        DualAnalyzedContext dual_ = _dualNav.getDualAnalyzedContext();
        DualConfigurationContext du_ = dual_.getContext();
        for (String f: _rend.getKeys()) {
            if (!f.startsWith("page")) {
                continue;
            }
            du_.getRenderFiles().add(f);
        }
        StringMap<String> all_ = filRend(new StringMap<String>(_files), _rend);
        du_.setFilesConfName("hello");
        all_.addEntry(du_.getFilesConfName(), StringUtil.join(_files.getKeys(),"\n"));
        _dualNav.getNavigation().setFiles(all_);
        setFirst("page1.html", _dualNav.getNavigation().getSession());
        return ((BeanCustLgNames)dual_.getStds()).setupAll(_dualNav);
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
        DualNavigationContext a_ = buildNav();
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        ContextEl ctx_ = extracted(_html, _files, a_);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_, ctx_);
    }

    private static ContextEl extracted(String _html, StringMap<String> _files, DualNavigationContext a_) {
        return ana(_files, oneFile(_html), a_);
    }

    private static Struct getStruct(DualNavigationContext _a, ContextEl _analyzed) {
        return failRes(_analyzed,_a);
    }

    protected static Struct getEx(String _folder, String _html, StringMap<String> _filesThree) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, a_.getDualAnalyzedContext().getContext());
        setFiles(_filesThree, a_.getNavigation().getSession());
        ContextEl ctx_ = ana(new StringMap<String>(), oneFile(_html), a_);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_, ctx_);
    }

    protected static Struct getEx(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, _filesThree, a_);
        ContextEl ctx_ = ana(_files, filRend(oneFile(_html), _filesThree), a_);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_, ctx_);
    }

    protected static boolean hasCommErr(String _html, StringMap<String> _filesThree) {
        DualNavigationContext a_ = buildNav();

        extracted5(_html, _filesThree, a_);
        return !isEmptyErrors(a_);
    }

    private static void extracted5(String _html, StringMap<String> _filesThree, DualNavigationContext a_) {
        ana(_filesThree, oneFile(_html), a_);
    }

    protected static boolean hasErr(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _files) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, _filesThree, a_);
        ana(_files, filRend(oneFile(_html),_filesThree), a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErr(String _folder, String _relative, String _html, StringMap<String> _filesThree) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        extracted8(_html, _filesThree, a_);
        return !isEmptyErrors(a_);
    }

    private static void extracted8(String _html, StringMap<String> _filesThree, DualNavigationContext a_) {
        ana(_filesThree, oneFile(_html), a_);
    }


    protected static String getCommOneBean(String _html, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();

        ContextEl ctx_ = elOneBean(_filesSec, oneFile(_html), a_);

        return successRes(ctx_, a_);
    }

    protected static String getCommOneBeanParam(String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        setFiles(_filesThree, a_.getNavigation().getSession());
        ContextEl ctx_ = getContextEl1(_html, _filesSec, a_);

        return successRes(ctx_, a_);
    }

    private static ContextEl getContextEl1(String _html, StringMap<String> _filesSec, DualNavigationContext a_) {
        newSampleBean("pkg.BeanOne<$int>", "bean_one", a_.getNavigation().getSession());
        StringMap<String> files_ = oneFile(_html);
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        return ctx_;
    }

    protected static String getCommOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec, String... _types) {
        DualNavigationContext a_ = buildNav(_types);
        setup(_folder, _relative, _filesThree, a_);
        ContextEl ctx_ = elOneBean(_filesSec, filRend(oneFile(_html), _filesThree), a_);

        return successRes(ctx_, a_);
    }

    protected static Struct getCommExOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec, String... _types) {
        DualNavigationContext a_ = buildNav(_types);
        setup(_folder, _relative, _filesThree, a_);
        ContextEl ctx_ = elOneBean(_filesSec, filRend(oneFile(_html), _filesThree), a_);
        return failRes(ctx_, a_);
    }

    protected static ContextEl elOneBean(StringMap<String> _filesSec, StringMap<String> _fileRend, DualNavigationContext _dual) {
        newSampleBean("pkg.BeanOne", "bean_one", _dual.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, _fileRend, _dual);
        CustList<RendDynOperationNode> ops_ = _dual.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) _dual.getDualAnalyzedContext().getStds(), _dual.getNavigation().getSession());
        return ctx_;
    }

    protected static boolean hasCommErrOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, _filesThree, a_);
//        StringMap<String> files_ = oneFile(_html);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        ana(_filesSec, filRend(oneFile(_html),_filesThree), a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrOneBean(String _html, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();

        extracted10(_html, _filesSec, a_);
        return !isEmptyErrors(a_);
    }

    private static void extracted10(String _html, StringMap<String> _filesSec, DualNavigationContext a_) {
        StringMap<String> files_ = oneFile(_html);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        ana(_filesSec, files_, a_);
    }

    protected static boolean hasErrOneBean2(String _folder, String _relative, String _html, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        extracted11(_html, _filesSec, a_);
        return !isEmptyErrors(a_);
    }

    private static void extracted11(String _html, StringMap<String> _filesSec, DualNavigationContext a_) {
        StringMap<String> files_ = oneFile(_html);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        ana(_filesSec, files_, a_);
    }

    protected static String getAncOneBean(String _folder, String _relative, String _html, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, _filesThree, a_);
        ContextEl ctx_ = elOneBean(_filesSec, filRend(oneFile(_html), _filesThree), a_);
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
        String res_ = successRes(build_,ctx_, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        assertEq(1, build_.getHtmlPage().getAnchorsArgs().size());
        assertEq("2", build_.getHtmlPage().getAnchorsArgs().last().last());
        return res_;
    }

    protected static void setup(String _folder, String _relative, StringMap<String> _filesThree, DualNavigationContext _dual) {
        setup(_folder, _relative, _dual.getDualAnalyzedContext().getContext());
        setFiles(_filesThree, _dual.getNavigation().getSession());
    }

    protected static void setup(String _folder, String _relative, StringMap<String> _filesThree, DualConfigurationContext _dual, Configuration _configuration) {
        setup(_folder, _relative, _dual);
        setFiles(_filesThree, _configuration);
    }

    protected static void setFirst(String _firstUrl, Configuration _configuration) {
        _configuration.setFirstUrl(_firstUrl);
    }

    protected static void setFirst(AnalyzedTestConfigurationBis _conf, String _firstUrl) {
        setFirst(_firstUrl, _conf.getConfiguration());
    }


    protected static StringMap<String> filRend(StringMap<String> _html, StringMap<String> _filesThree) {
        _html.addAllEntries(_filesThree);
        return _html;
    }

    protected static DualNavigationContext getA_(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();

        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        return a_;
    }

    protected static boolean initSessionFail(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();
        
        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        ana(_filesSec,_filesThree,a_);
        return !isEmptyErrors(a_);
    }

    protected static DualNavigationContext getContextVal(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();

        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        return a_;
    }

    protected static DualNavigationContext getDualNavigationContext2(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        setNavigation(a_.getNavigation().getSession());
        a_.getNavigation().getSession().getNavigation().addEntry("bean_one.validate",new StringMap<String>());
        a_.getNavigation().getSession().getNavigation().getVal("bean_one.validate").addEntry("val1","page2.html");
        Navigation nav_ = a_.getNavigation();
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        return a_;
    }

    protected static DualNavigationContext getDualNavigationContext(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();
//        nav_.setLanguage(_locale);

//        nav_.setFiles(filesThree_);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        return a_;
    }

    protected static StringMap<String> getStringStringMap(String _folder, String _locale, String _relative, String _content, String _html, String _html1) {
        StringMap<String> filesThree_ = getStringStringMap(_folder, _locale, _relative, _content, _html);
        filesThree_.put("page2.html", _html1);
        return filesThree_;
    }

    protected static DualNavigationContext getDualNavigationContext1(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        return a_;
    }

    protected static DualNavigationContext getA(String _folder, String _relative, String _className, String _scope) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        addVal(nav_,"valRef","pkg.MyVal");
        return a_;
    }

    protected static DualNavigationContext getContext(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        return a_;
    }

    protected static DualNavigationContext getNavigationContext(String _folder, String _relative, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();

        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        return a_;
    }

    protected static StringMap<String> getStringStringMap(String _folder, String _locale, String _relative, String _content, String _html) {
        StringMap<String> filesThree_ = new StringMap<String>();
        filesThree_.put(EquallableRenderUtil.formatFile(_folder, _locale, _relative), _content);
        filesThree_.put("page1.html", _html);
        return filesThree_;
    }

    protected static RendStackCall initializeRendSession(ContextEl _ctx,Navigation _nav, DualNavigationContext _a) {
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, _ctx);
        _nav.initializeRendSession(_ctx, _a.getDualAnalyzedContext().getStds(), build_);
        return build_;
    }

    protected static DualNavigationContext buildNav() {
        return buildNav(new String[0]);
    }
    protected static DualNavigationContext buildNav(String... _types) {
        return buildNav(getConfiguration(),_types);
    }
    protected static DualNavigationContext buildNav(Configuration _conf,String... _types) {
        Options opt_ = ops(_types);

        BeanCustLgNames lgNames_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicCustStandards(lgNames_);
        InitializationLgNamesRender.basicStandards(lgNames_);
        lgNames_.getContent().getMathRef().setAliasMath("java.lang.$math");
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = new Forwards(lgNames_, BeanFileBuilder.newInstance(lgNames_.getContent(), lgNames_.getBeanAliases()), opt_);
        page_.setLogErr(forwards_.getGenerator());
        ReadConfiguration.loadContext(lgNames_,page_,forwards_,new KeyWords(),new AnalysisMessages(),new RendKeyWords());
        assertTrue(page_.isEmptyStdError());
        DualConfigurationContext dual_ = new DualConfigurationContext();
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf);
        nav_.setLanguage("en");
        nav_.setLanguages(new StringList("en"));
        return new DualNavigationContext(nav_,new DualAnalyzedContext(forwards_,page_,lgNames_,dual_));
    }

    protected static Options ops(String[] _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        WarningShow warningShow_ = new WarningShow();
        warningShow_.setTernary(true);
        opt_.setWarningShow(warningShow_);
        opt_.getTypesInit().addAllElts(new StringList(_types));
        return opt_;
    }

    protected static StringMap<String> getStringStringMap(String _locale, String _folder, String _relative, String _content, String _html, String _htmlTwo, String _htmlThree) {
        StringMap<String> filesThree_ = getStringStringMap(_folder, _locale, _relative, _content, _html, _htmlTwo);
        filesThree_.put("page3.html", _htmlThree);
        return filesThree_;
    }

    protected static Navigation getStdNavigation(String _locale, String _folder, String _relative, StringMap<String> _filesSec, StringMap<String> _filesThree, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();
        
        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");

//        StringMap<AnaRendDocumentBlock> d_ = extracted(_filesSec, a_, nav_);
//        simpleForward(a_, d_);
        ContextEl ctx_ =  ana(_filesSec,_filesThree,a_);
        initializeRendSession(ctx_, nav_, a_);
        return nav_;
    }

    protected static RendStackCall getStdNavigation2(String _locale, String _folder, String _relative, StringMap<String> _filesSec, StringMap<String> _filesThree, String _scope, String _className) {
        DualNavigationContext a_ = buildNav();
        
        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        addBeanInfo(nav_, i_, "bean_one");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        ContextEl ctx_ = ana(_filesSec,_filesThree,a_);
        return initializeRendSession(ctx_, nav_, a_);
    }


    protected static DualNavigationContext getDualNavigationContext(String _locale, String _folder, String _relative, StringMap<String> _filesThree) {
        DualNavigationContext a_ = buildNav();

        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page2.html");
        return a_;
    }

    protected static Navigation getStdNavigation3(String _locale, String _folder, String _relative, String _content, String _html, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();

        StringMap<String> filesThree_ = getStringStringMap(_folder, _locale, _relative, _content, _html);
        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(filesThree_);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        ContextEl ctx_ = ana(_filesSec,filesThree_,a_);
        initializeRendSession(ctx_, nav_, a_);
        return nav_;
    }

    protected static boolean getStdNavigation7(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec, String _s) {
        DualNavigationContext a_ = buildNav();
        
        setFirst(_s, a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = a_.getNavigation();

        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        ana(_filesSec,_filesThree,a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean getStdNavigation6(String _locale, String _folder, String _relative, StringMap<String> _filesThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setFirst("page1.html", a_.getNavigation().getSession());
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        Navigation nav_ = newNavigation(a_);
        nav_.setLanguage(_locale);

        nav_.setFiles(_filesThree);
        a_.getDualAnalyzedContext().getContext().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("page");
        i_.setClassName("pkg.BeanOne");
        addBeanInfo(nav_, i_, "bean_one");
        ana(_filesSec,_filesThree,a_);
        return !isEmptyErrors(a_);
    }

    private static void addVal(Navigation _nav, String _valId, String _class) {
        ValidatorInfo v_ = new ValidatorInfo();
        v_.setClassName(_class);
        _nav.getSession().getLateValidators().addEntry(_valId,v_);
    }

    protected static Struct getExTwoPages(String _folder, String _relative, String _html, String _htmlTwo) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        ContextEl ctx_ = extracted(_html, _htmlTwo, a_);
        assertTrue(isEmptyErrors(a_));
        return getStruct(a_, ctx_);
    }

    private static ContextEl extracted(String _html, String _htmlTwo, DualNavigationContext a_) {
        return ana(new StringMap<String>(), twoFiles(oneFile(_html), "page2.html", _htmlTwo), a_);
    }

    protected static String getResTwoPages(String _folder, String _relative, String _html, String _htmlTwo) {
        DualNavigationContext a_ = buildNav();
        

        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        ContextEl ctx_ = extracted1(_html, _htmlTwo, a_);
        assertTrue(isEmptyErrors(a_));
        return getString(a_, ctx_);
    }

    private static ContextEl extracted1(String _html, String _htmlTwo, DualNavigationContext a_) {
        return ana(new StringMap<String>(), twoFiles(oneFile(_html), "page2.html", _htmlTwo), a_);
    }

    protected static String getResTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = twoFiles(oneFile(_html), "page2.html", _htmlTwo);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        CustList<RendDynOperationNode> ops2_ = a_.getNavigation().getSession().getBeansInfos().getValue(1).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops2_, 1, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());

        return successRes(ctx_, a_);
    }

    protected static Struct getExTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = twoFiles(oneFile(_html), "page2.html", _htmlTwo);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        CustList<RendDynOperationNode> ops2_ = a_.getNavigation().getSession().getBeansInfos().getValue(1).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops2_, 1, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());

        return failRes(ctx_, a_);
    }

    protected static String getResTwoPagesOne(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = twoFiles(oneFile(_html), "page2.html", _htmlTwo);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());

        return successRes(ctx_, a_);
    }


    protected static Struct getExThreeBeans(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = threeFiles(twoFiles(oneFile(_html), "page2.html", _htmlTwo), "page3.html", _htmlThree);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanThree", "bean_three", a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        CustList<RendDynOperationNode> ops2_ = a_.getNavigation().getSession().getBeansInfos().getValue(1).getExps();
        CustList<RendDynOperationNode> ops3_ = a_.getNavigation().getSession().getBeansInfos().getValue(2).getExps();
        calcBean(ctx_, ops_,0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops2_, 1, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops3_, 2, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());

        return failRes(ctx_, a_);
    }

    private static void calcBean(ContextEl _ctx, CustList<RendDynOperationNode> _ops, int _index, BeanCustLgNames _advStandards, Configuration _configuration) {
        Struct bean_ = calculateReuse(_ctx, _ops, _advStandards);
        _configuration.getBuiltBeans().setValue(_index, bean_);
    }

    private static StringMap<String> twoFiles(StringMap<String> _html, String _k, String _html2) {
        return threeFiles(_html, _k, _html2);
    }

    private static StringMap<String> threeFiles(StringMap<String> _html, String _k, String _html3) {
        _html.addEntry(_k, _html3);
        return _html;
    }

    protected static StringMap<String> oneFile(String _html) {
        return twoFiles(new StringMap<String>(), "page1.html", _html);
    }

    private static void newSampleBean(String _className, String _bean, Configuration _configuration) {
        BeanInfo b2_ = new BeanInfo();
        b2_.setClassName(_className);
        b2_.setResolvedClassName(_className);
        _configuration.getBeansInfos().addEntry(_bean, b2_);
    }

    protected static String getResThreeBean(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = threeFiles(twoFiles(oneFile(_html), "page2.html", _htmlTwo), "page3.html", _htmlThree);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanThree", "bean_three", a_.getNavigation().getSession());
        ContextEl ctx_ = ana(_filesSec, files_, a_);
        assertTrue(isEmptyErrors(a_));
        CustList<RendDynOperationNode> ops_ = a_.getNavigation().getSession().getBeansInfos().getValue(0).getExps();
        CustList<RendDynOperationNode> ops2_ = a_.getNavigation().getSession().getBeansInfos().getValue(1).getExps();
        CustList<RendDynOperationNode> ops3_ = a_.getNavigation().getSession().getBeansInfos().getValue(2).getExps();
        calcBean(ctx_, ops_, 0, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops2_, 1, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());
        calcBean(ctx_, ops3_, 2, (BeanCustLgNames) a_.getDualAnalyzedContext().getStds(), a_.getNavigation().getSession());


        return successRes(ctx_, a_);
    }

    protected static boolean hasErrThree(String _folder, String _relative, String _html, String _htmlTwo, String _htmlThree, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = threeFiles(twoFiles(oneFile(_html), "page2.html", _htmlTwo), "page3.html", _htmlThree);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanThree", "bean_three", a_.getNavigation().getSession());
        ana(_filesSec, files_, a_);
        return !isEmptyErrors(a_);
    }

    protected static boolean hasErrTwoPagesTwo(String _folder, String _relative, String _html, String _htmlTwo, StringMap<String> _filesSec) {
        DualNavigationContext a_ = buildNav();
        
        setup(_folder, _relative, a_.getDualAnalyzedContext().getContext());
        StringMap<String> files_ = twoFiles(oneFile(_html), "page2.html", _htmlTwo);
        newSampleBean("pkg.BeanOne", "bean_one", a_.getNavigation().getSession());
        newSampleBean("pkg.BeanTwo", "bean_two", a_.getNavigation().getSession());
        ana(_filesSec, files_, a_);
        return !isEmptyErrors(a_);
    }

    protected static void addBeanInfo(Navigation _nav, BeanInfo _i, String _bean) {
        _nav.getSession().getBeansInfos().addEntry(_bean,_i);
    }

    protected static Struct calculateReuse(ContextEl _ctx, CustList<RendDynOperationNode> _ops, BeanCustLgNames _advStandards) {
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, _ctx);
        addInnerPage(build_);
        return RenderExpUtil.calculateReuse(_ops, _advStandards, _ctx, build_).getStruct();
    }

    protected static void setNavigation(Configuration _configuration) {
        _configuration.setNavigation(new StringMap<StringMap<String>>());
    }

    protected static Configuration getConfiguration() {
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c:");
        return conf_;
    }

    private static String successRes(ContextEl _ctx, DualNavigationContext _a) {
        return successRes(new RendStackCall(InitPhase.NOTHING, _ctx),_ctx, (BeanCustLgNames) _a.getDualAnalyzedContext().getStds(), _a.getNavigation().getSession());
    }

    protected static String successRes(RendStackCall _st, ContextEl _ctx, DualNavigationContext _nav) {
        String res_ = res(_ctx, _st, (BeanCustLgNames) _nav.getDualAnalyzedContext().getStds(), _nav.getNavigation().getSession());
        assertNull(_st.getStackCall().getCallingState());
        return res_;
    }

    protected static String successRes(RendStackCall _st, ContextEl _ctx, BeanCustLgNames _advStandards, Configuration _configuration) {
        String res_ = res(_ctx, _st, _advStandards, _configuration);
        assertNull(_st.getStackCall().getCallingState());
        return res_;
    }

    private static Struct failRes(ContextEl _ctx, DualNavigationContext _a) {
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING,_ctx);
        res(_ctx, build_, (BeanCustLgNames) _a.getDualAnalyzedContext().getStds(), _a.getNavigation().getSession());
        CallingState str_ = build_.getStackCall().getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static String res(ContextEl _ctx, RendStackCall _rendStackCall, BeanCustLgNames _advStandards, Configuration _configuration) {
        setFirst(_configuration);
        _rendStackCall.clearPages();
        return BeanCustLgNames.getRes(_configuration.getRendDocumentBlock(), _configuration, _advStandards, _ctx, _rendStackCall);
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
