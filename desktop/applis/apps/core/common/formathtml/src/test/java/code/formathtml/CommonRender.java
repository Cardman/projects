package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
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

    protected static void addImportingPage(Configuration _conf) {
        addInnerPage(_conf);
    }

    protected static void addImportingPage(AnalyzedTestConfiguration _conf) {
        addInnerPage(_conf.getConfiguration());
    }

    protected static void addInnerPage(Configuration _conf) {
        _conf.addPage(new ImportingPage());
    }

    protected static Navigation newNavigation(Configuration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf);
        ContextEl context_ = _conf.getContext();
        BeanLgNames standards_ = (BeanLgNames) context_.getStandards();
        nav_.getSession().setStandards(standards_);
        setStack(nav_.getSession(), context_);
        return nav_;
    }

    protected static Navigation newNavigation(AnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());
        ContextEl context_ = _conf.getContext();
        BeanLgNames standards_ = (BeanLgNames) context_.getStandards();
        nav_.getSession().setStandards(standards_);
        setStack(nav_.getSession(), context_);
        return nav_;
    }

    public static void setupAnalyzing(AnalyzedPageEl _analyzing, ImportingPage _lastPage) {
        //

        String globalClass_ = _analyzing.getGlobalClass();
        setupAna(new AnalyzingDoc(), _analyzing);
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

    public static void setVars(ImportingPage _importingPage, StringMap<LoopVariable> _vars) {
        _importingPage.getPageEl().setVars(_vars);
    }

    public static void setLocalVars(ImportingPage _importingPage, StringMap<LocalVariable> _localVars) {
        _importingPage.getPageEl().getValueVars().putAllMap(_localVars);
    }

    protected static boolean isEmptyErrors(AnalyzedTestContext cont_) {
        return cont_.getAnalyzing() == null || (cont_.getAnalyzing().isEmptyErrors());
    }
    protected static boolean isEmptyErrors(AnalyzedTestConfiguration cont_) {
        return cont_.getAnalyzing() == null || (cont_.getAnalyzing().isEmptyErrors());
    }

    protected static AnalyzedPageEl getHeaders(StringMap<String> _files,AnalyzedTestContext _cont) {
        Classes.validateWithoutInit(_files,_cont.getContext());
        return _cont.getAnalyzing();
    }

    protected static void setupAna(AnalyzingDoc _analyzingDoc, AnalyzedPageEl page_) {
        Configuration.setupInts(page_, _analyzingDoc);
    }

    protected static void setLocalFiles(AnalyzedTestContext _a,Configuration context_, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = _a.getAnalyzing();
        setInnerLocalFilesLg(_analyzingDoc, analyzing_, context_);
    }

    protected static void setLocalFiles(AnalyzedTestConfiguration context_, AnalyzingDoc _analyzingDoc) {
        AnalyzedPageEl analyzing_ = context_.getAnalyzing();
        Configuration conf_ = context_.getConfiguration();
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

    protected static void tryInitStaticlyTypes(AnalyzedTestContext _cont,Configuration _context) {
        if (isEmptyErrors(_cont)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            Classes.forwardAndClear(_context.getContext(), page_);
            AnalysisMessages analysisMessages_ = page_.getAnalysisMessages();
            ReportedMessages messages_ = page_.getMessages();
            Classes.tryInitStaticlyTypes(_context.getContext(),analysisMessages_,messages_, page_.getOptions());
        }
        addInnerPage(_context);
    }

    protected static void tryInitStaticlyTypes(AnalyzedTestConfiguration _context) {
        if (_context.isEmptyErrors()) {
            AnalyzedPageEl page_ = _context.getAnalyzing();
            Classes.forwardAndClear(_context.getContext(), page_);
            AnalysisMessages analysisMessages_ = page_.getAnalysisMessages();
            ReportedMessages messages_ = page_.getMessages();
            Classes.tryInitStaticlyTypes(_context.getContext(),analysisMessages_,messages_, page_.getOptions());
        }
        addInnerPage(_context.getConfiguration());
    }

    protected static Struct getStruct(Struct _struct, ClassField _cl) {
        return ((FieldableStruct) _struct).getEntryStruct(_cl).getStruct();
    }
    protected static void setStruct(Struct _struct, ClassField _cl, Struct _value) {
        ((FieldableStruct) _struct).getEntryStruct(_cl).setStruct(_value);
    }
    protected static Struct getException(Configuration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }
    protected static Struct getException(AnalyzedTestConfiguration _cont) {
        CallingState str_ = _cont.getContext().getCallingState();
        if (str_ instanceof Struct) {
            return (Struct) str_;
        }
        return null;
    }

    static Argument processEl(String _el, int _index, AnalyzedTestConfiguration _conf) {
        ContextEl context_ = _conf.getContext();
        CustList<RendDynOperationNode> out_ = getAnalyzed(_el,_index,_conf, new AnalyzingDoc());
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (!_conf.isEmptyErrors()) {
            return Argument.createVoid();
        }
        Classes.forwardAndClear(context_, page_);
        for (ClassMetaInfo c: context_.getClasses().getClassMetaInfos()) {
            String name_ = c.getName();
            ClassMetaInfo.forward(ExecutingUtil.getClassMetaInfo(context_, name_), c);
        }
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        return RenderExpUtil.calculateReuse(out_, _conf.getConfiguration());
    }

    protected static void setFiles(StringMap<String> files_, Configuration conf_) {
        conf_.setFiles(files_);
    }

    protected static void setFiles(StringMap<String> files_, AnalyzedTestConfiguration conf_) {
        conf_.getConfiguration().setFiles(files_);
    }

    protected static CustList<RendDynOperationNode> getAnalyzed(String _el, int _index, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc) {
        setupAnalyzing(_conf.getAnalyzing(), _conf.getLastPage());
        Argument argGl_ = _conf.getConfiguration().getPageEl().getGlobalArgument();
        boolean static_ = argGl_.isNull();
        _conf.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        return getList(_el, _index, _conf, _analyzingDoc);
    }

    protected static CustList<RendDynOperationNode> getList(String _el, int _index, AnalyzedTestConfiguration _conf, AnalyzingDoc _analyzingDoc) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf.getContext(), _index);
        int badOffset_ = d_.getBadOffset();
        if (badOffset_ >= 0) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_analyzingDoc.getFileName());
            badEl_.setIndexFile(Configuration.getCurrentLocationIndex(_conf.getAnalyzing(), _analyzingDoc));
            badEl_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadExpression(),
                    " ",
                    Integer.toString(badOffset_),
                    _el);
            Configuration.addError(badEl_, _analyzingDoc, _conf.getAnalyzing());
            return new CustList<RendDynOperationNode>();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = RenderExpUtil.getOperationsSequence(_index, el_, _conf.getConfiguration(), d_, _analyzingDoc);
        OperationNode op_ = RenderExpUtil.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf.getConfiguration(), _analyzingDoc);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, _conf.getConfiguration(), _analyzingDoc);
        return RenderExpUtil.getExecutableNodes(all_,_conf.getContext());
    }

    protected static void setup(String folder_, String relative_, Configuration conf_) {
        setup(folder_, conf_);
        conf_.getProperties().put("msg_example", relative_);
    }

    protected static void setup(String folder_, Configuration conf_) {
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
    }

    protected static String getCommRes(String html_, StringMap<String> files_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res_;
    }

    protected static String getCommRes(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);

        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res_;
    }

    protected static String getRes(String folder_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, conf_);
        setFiles(files_, conf_);

        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res;
    }

    protected static String getRes2(String folder_, String relative_, String html_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setup(folder_, relative_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res;
    }

    protected static Struct getCommEx(String html_, StringMap<String> _files) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,context_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(context_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        context_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,context_);
        context_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_, context_);
        return getException(context_);
    }

    protected static Struct getCommEx(String folder_, String relative_, String html_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setup(folder_, relative_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return getException(conf_);
    }

    protected static Struct getEx(String folder_, String html_, StringMap<String> files_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(new StringMap<String>(), cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, conf_);
        setFiles(files_,conf_);

        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return getException(conf_);
    }
    protected static Struct getEx(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);

        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        conf_.setDocument(doc_);
        tryInitStaticlyTypes(cont_,conf_);
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return getException(conf_);
    }

    protected static boolean hasCommErr(String html_, StringMap<String> files_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        getHeaders(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,context_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(context_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean hasErr(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> _files) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(_files, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);

        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(conf_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean hasErr(String folder_, String relative_, String html_, StringMap<String> files_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setup(folder_, relative_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(conf_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }



    protected static String getCommOneBean(String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,context_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(context_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,context_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_).getStruct();
        context_.getBuiltBeans().addEntry("bean_one", bean_);
        context_.clearPages();
        context_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res_ = RendBlock.getRes(rendDocumentBlock_, context_);
        assertNull(getException(context_));
        return res_;
    }

    protected static String getCommOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        setFiles(files_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,conf_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res_;
    }

    protected static Struct getCommExOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setup(folder_, relative_, conf_);
        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,conf_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return getException(conf_);
    }

    protected static boolean hasCommErrOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(conf_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean hasErrOneBean(String html_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", doc_, html_);
        context_.getRenders().put("page1.html",rendDocumentBlock_);
        setLocalFiles(cont_,context_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(context_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean hasErrOneBean2(String folder_, String relative_, String html_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html",rendDocumentBlock_);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(conf_, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }
    protected static String getAncOneBean(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        setFiles(files_, conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,conf_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res_ = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        assertEq(1, conf_.getHtmlPage().getAnchorsArgs().size());
        assertEq("2", conf_.getHtmlPage().getAnchorsArgs().last().last());
        return res_;
    }

    private static void setStack(Configuration conf_1, ContextEl cont_) {
        cont_.setFullStack(new AdvancedFullStack(conf_1));
    }

    private static void setStack(Configuration conf_1, AnalyzedTestContext cont_) {
        cont_.getContext().setFullStack(new AdvancedFullStack(conf_1));
    }

    private static void setCtx(Configuration conf_1, AnalyzedTestContext cont_) {
        conf_1.setContext(cont_.getContext());
    }

    protected static Configuration simulateNav2(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_, "page1.html");
        setFiles(files_,conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,conf_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return conf_;
    }

    protected static Configuration simulateNav(String folder_, String relative_, String html_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        simpleSetup(folder_, relative_, conf_);
        setFiles(files_,conf_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_, "c:", doc_, html_);
        conf_.getRenders().put("page1.html", rendDocumentBlock_1);
        setLocalFiles(cont_,conf_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,conf_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_).getStruct();
        conf_.getBuiltBeans().addEntry("bean_one", bean_);
        conf_.clearPages();
        conf_.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return conf_;
    }

    private static void simpleSetup(String folder_, String relative_, Configuration conf_) {
        setup(folder_, relative_, conf_);
    }

    private static void setup(String folder_, String relative_, Configuration conf_, String _firstUrl) {
        conf_.setFirstUrl(_firstUrl);
        setup(folder_, relative_, conf_);
    }
















    protected static Navigation initSession(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static boolean initSessionFail(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        return !isEmptyErrors(cont_);
    }

    protected static Navigation initSession2(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSession3(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry("bean_one.validate",new StringMap<String>());
        conf_.getNavigation().getVal("bean_one.validate").addEntry("val1","page2.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    private static void setupNav(String folder_, String relative_, Configuration conf_, String s) {
        conf_.setFirstUrl(s);
        setup(folder_,relative_,conf_);
    }

    protected static Navigation initSession4(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSession44(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSessionSim(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String _className, String _scope) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        addVal(nav_,"valRef","pkg.MyVal");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSessionSim2(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSession5(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String _scope, String _className) {
        return initSession55(locale_, folder_, relative_, content_, html_, htmlTwo_, htmlThree_, filesSec_, "bean_one.click()", "page2.html", "page3.html", _scope, _className);
    }

    protected static Navigation initSession55(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String s, String s2, String s3, String session, String s4) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        files_.put("page3.html", htmlThree_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry(s,new StringMap<String>());
        conf_.getNavigation().getVal(s).addEntry("val1", s2);
        conf_.getNavigation().getVal(s).addEntry("val2", s3);
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        nav_.getSession().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope(session);
        i_.setClassName(s4);
        nav_.getSession().getBeansInfos().addEntry("bean_one", i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation initSession56(String locale_, String folder_, String relative_, String content_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        files_.put("page3.html", htmlThree_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().addEntry("bean_one.click()",new StringMap<String>());
        conf_.getNavigation().getVal("bean_one.click()").addEntry("val1", "page2.html");
        conf_.getNavigation().getVal("bean_one.click()").addEntry("val2", "page3.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        nav_.getSession().getRenderFiles().add("page3.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("pkg.BeanTwo");
        nav_.getSession().getBeansInfos().addEntry("bean_two",i_);
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation getStdNavigation(String locale_, String folder_, String relative_, StringMap<String> filesSec_, StringMap<String> files_, String _scope, String _className) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        BeanInfo i_ = new BeanInfo();
        i_.setScope(_scope);
        i_.setClassName(_className);
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }


    protected static Navigation getStdNavigation2(String locale_, String folder_, String relative_, StringMap<String> filesSec_, StringMap<String> files_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        nav_.getSession().getRenderFiles().add("page2.html");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static Navigation getStdNavigation3(String locale_, String folder_, String relative_, String content_, String html_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_, locale_, relative_), content_);
        files_.put("page1.html", html_);
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_1 = newNavigation(conf_);
        nav_1.setLanguage(locale_);

        nav_1.setFiles(files_);
        Navigation nav_ = nav_1;
        nav_.getSession().getRenderFiles().add("page1.html");
        analyze(cont_,nav_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(cont_,nav_.getSession());
        nav_.initializeRendSession();
        return nav_;
    }

    protected static boolean getStdNavigation7(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_, String s) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, s);
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        analyze(cont_,nav_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean getStdNavigation6(String locale_, String folder_, String relative_, StringMap<String> files_, StringMap<String> filesSec_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;
        setupFirstUrl(folder_, relative_, conf_, "page1.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);

        nav_.setFiles(files_);
        nav_.getSession().getRenderFiles().add("page1.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("page");
        i_.setClassName("pkg.BeanOne");
        nav_.getSession().getBeansInfos().addEntry("bean_one",i_);
        analyze(cont_,nav_);
        return !isEmptyErrors(cont_);
    }

    private static void addVal(Navigation _nav, String _valId, String _class) {
        ValidatorInfo v_ = new ValidatorInfo();
        v_.setClassName(_class);
        _nav.getSession().getLateValidators().addEntry(_valId,v_);
    }

    protected static void analyze(AnalyzedTestContext _cont,Navigation _nav) {
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        setupAna(new AnalyzingDoc(), _cont.getAnalyzing());
        _nav.initInstancesPattern();
        _nav.setupRenders();
    }

    protected static void setupFirstUrl(String folder_, String relative_, Configuration conf_, String _firstUrl) {
        setupNav(folder_, relative_, conf_, _firstUrl);
    }

    protected static Struct getExTwoPages(String folder_, String relative_, String html_, String htmlTwo_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(new StringMap<String>(), cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration conf_2 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document secDoc_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_2, "c:", doc_, html_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_2, "c:", secDoc_, htmlTwo_);
        conf_2.getRenders().put("page1.html", rendDocumentBlock_1);
        conf_2.getRenders().put("page2.html",rendSecDocumentBlock_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_2, analyzingDoc_);
        rendSecDocumentBlock_.buildFctInstructions(conf_2, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        conf_2.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_,conf_);
        return getException(conf_);
    }

    protected static String getResTwoPages(String folder_, String relative_, String html_, String htmlTwo_) {
        Configuration conf_1 =  EquallableExUtil.newConfiguration();
        conf_1.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_1, cont_);
        setStack(conf_1, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_1.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(new StringMap<String>(), cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_1);
        Configuration conf_ = conf_1;

        setup(folder_, relative_, conf_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration conf_2 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document secDoc_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_2, "c:", doc_, html_);
        RendDocumentBlock rendSecDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_2, "c:", secDoc_, htmlTwo_);
        conf_2.getRenders().put("page1.html", rendDocumentBlock_1);
        conf_2.getRenders().put("page2.html",rendSecDocumentBlock_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_2, analyzingDoc_);
        rendSecDocumentBlock_.buildFctInstructions(conf_2, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        conf_2.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res = RendBlock.getRes(rendDocumentBlock_, conf_);
        assertNull(getException(conf_));
        return res;
    }

    protected static String getResTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration context_1 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, context_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_two", b2_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(context_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_1, "c:", docSec_, htmlTwo_);
        context_1.getRenders().put("page1.html", rendDocumentBlock_1);
        context_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(context_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(context_1, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_1).getStruct();
        context_1.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, context_1).getStruct();
        context_1.getBuiltBeans().addEntry("bean_two", bean_);
        context_1.clearPages();
        context_1.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res = RendBlock.getRes(rendDocumentBlock_, context_);
        assertNull(getException(context_));
        return res;
    }

    protected static Struct getExTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration context_1 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, context_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_two", b2_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(context_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_1, "c:", docSec_, htmlTwo_);
        context_1.getRenders().put("page1.html", rendDocumentBlock_1);
        context_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(context_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(context_1, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_1).getStruct();
        context_1.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, context_1).getStruct();
        context_1.getBuiltBeans().addEntry("bean_two", bean_);
        context_1.clearPages();
        context_1.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_, context_);
        return getException(context_);
    }

    protected static String getResTwoPagesOne(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration context_1 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_1, analyzingDoc_);
        BeanInfo b_ = new BeanInfo();
        b_.setClassName("pkg.BeanOne");
        b_.setExps(ops_);
        b_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_one",b_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(context_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_1, "c:", docSec_, htmlTwo_);
        context_1.getRenders().put("page1.html", rendDocumentBlock_1);
        context_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(context_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(context_1, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, context_1).getStruct();
        context_1.getBuiltBeans().addEntry("bean_one", bean_);
        context_1.clearPages();
        context_1.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        String res = RendBlock.getRes(rendDocumentBlock_, context_);
        assertNull(getException(context_));
        return res;
    }


    protected static Struct getExThreeBeans(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        Document docThird_ = DocumentBuilder.parseSaxNotNullRowCol(htmlThree_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        Configuration conf_1 = a_.getConfiguration();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, conf_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_two", b2_);
        CustList<RendDynOperationNode> ops3_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanThree()", 0, conf_1, analyzingDoc_);
        BeanInfo b3_ = new BeanInfo();
        b3_.setExps(ops3_);
        b3_.setClassName("pkg.BeanThree");
        b3_.setResolvedClassName(ops3_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_three", b3_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docSec_, htmlTwo_);
        RendDocumentBlock rendDocumentBlockThird_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docThird_, htmlThree_);
        conf_1.getRenders().put("page1.html", rendDocumentBlock_1);
        conf_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_1.getRenders().put("page3.html",rendDocumentBlockThird_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockThird_.buildFctInstructions(conf_1, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_two", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops3_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_three", bean_);
        conf_1.clearPages();
        conf_1.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        RendBlock.getRes(rendDocumentBlock_, context_);
        return getException(context_);
    }

    protected static String getResThreeBean(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_, String _currentUrl) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        Document docThird_ = DocumentBuilder.parseSaxNotNullRowCol(htmlThree_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        Configuration conf_1 = a_.getConfiguration();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, conf_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_two", b2_);
        CustList<RendDynOperationNode> ops3_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanThree()", 0, conf_1, analyzingDoc_);
        BeanInfo b3_ = new BeanInfo();
        b3_.setExps(ops3_);
        b3_.setClassName("pkg.BeanThree");
        b3_.setResolvedClassName(ops3_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_three", b3_);
        RendDocumentBlock rendDocumentBlock_1 = RendBlock.newRendDocumentBlock(conf_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docSec_, htmlTwo_);
        RendDocumentBlock rendDocumentBlockThird_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docThird_, htmlThree_);
        conf_1.getRenders().put("page1.html", rendDocumentBlock_1);
        conf_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_1.getRenders().put("page3.html",rendDocumentBlockThird_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_1.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockThird_.buildFctInstructions(conf_1, analyzingDoc_);
        assertTrue(isEmptyErrors(cont_));
        tryInitStaticlyTypes(a_);
        Struct bean_ = RenderExpUtil.calculateReuse(ops_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_one", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops2_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_two", bean_);
        bean_ = RenderExpUtil.calculateReuse(ops3_, conf_1).getStruct();
        conf_1.getBuiltBeans().addEntry("bean_three", bean_);
        conf_1.clearPages();
        conf_1.setDocument(doc_);
        RendDocumentBlock rendDocumentBlock_ = rendDocumentBlock_1;
        context_.setCurrentUrl(_currentUrl);
        String res = RendBlock.getRes(rendDocumentBlock_, context_);
        assertNull(getException(context_));
        return res;
    }

    protected static boolean hasErrThree(String folder_, String relative_, String html_, String htmlTwo_, String htmlThree_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        Document docThird_ = DocumentBuilder.parseSaxNotNullRowCol(htmlThree_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        Configuration conf_1 = a_.getConfiguration();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, conf_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, conf_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_two", b2_);
        CustList<RendDynOperationNode> ops3_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanThree()", 0, conf_1, analyzingDoc_);
        BeanInfo b3_ = new BeanInfo();
        b3_.setExps(ops3_);
        b3_.setClassName("pkg.BeanThree");
        b3_.setResolvedClassName(ops3_.last().getResultClass().getSingleNameOrEmpty());
        conf_1.getBeansInfos().addEntry("bean_three", b3_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(conf_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docSec_, htmlTwo_);
        RendDocumentBlock rendDocumentBlockThird_ = RendBlock.newRendDocumentBlock(conf_1, "c:", docThird_, htmlThree_);
        conf_1.getRenders().put("page1.html",rendDocumentBlock_);
        conf_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        conf_1.getRenders().put("page3.html",rendDocumentBlockThird_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(conf_1, analyzingDoc_);
        rendDocumentBlockThird_.buildFctInstructions(conf_1, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

    protected static boolean hasErrTwoPagesTwo(String folder_, String relative_, String html_, String htmlTwo_, StringMap<String> filesSec_) {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        conf_.setPrefix("c:");
        Options opt_ = new Options();
        opt_.setReadOnly(true);

        AnalyzedTestContext cont_ = InitializationLgNames.buildStdThree(opt_);
        setCtx(conf_, cont_);
        setStack(conf_, cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        AnalyzedPageEl page_ = getHeaders(filesSec_, cont_);
        assertTrue(isEmptyErrors(cont_));
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        Configuration context_ = conf_;
        setup(folder_, relative_, context_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_,page_);
        Configuration context_1 = a_.getConfiguration();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(html_).getDocument();
        Document docSec_ = DocumentBuilder.parseSaxNotNullRowCol(htmlTwo_).getDocument();
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        CustList<RendDynOperationNode> ops_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanOne()", 0, context_1, analyzingDoc_);
        BeanInfo b1_ = new BeanInfo();
        b1_.setExps(ops_);
        b1_.setClassName("pkg.BeanOne");
        b1_.setResolvedClassName(ops_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_one",b1_);
        CustList<RendDynOperationNode> ops2_ = RenderExpUtil.getAnalyzedOperations("$new pkg.BeanTwo()", 0, context_1, analyzingDoc_);
        BeanInfo b2_ = new BeanInfo();
        b2_.setExps(ops2_);
        b2_.setClassName("pkg.BeanTwo");
        b2_.setResolvedClassName(ops2_.last().getResultClass().getSingleNameOrEmpty());
        context_1.getBeansInfos().addEntry("bean_two", b2_);
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_1, "c:", doc_, html_);
        RendDocumentBlock rendDocumentBlockSec_ = RendBlock.newRendDocumentBlock(context_1, "c:", docSec_, htmlTwo_);
        context_1.getRenders().put("page1.html",rendDocumentBlock_);
        context_1.getRenders().put("page2.html",rendDocumentBlockSec_);
        setLocalFiles(a_, analyzingDoc_);
        rendDocumentBlock_.buildFctInstructions(context_1, analyzingDoc_);
        rendDocumentBlockSec_.buildFctInstructions(context_1, analyzingDoc_);
        return !isEmptyErrors(cont_);
    }

}
