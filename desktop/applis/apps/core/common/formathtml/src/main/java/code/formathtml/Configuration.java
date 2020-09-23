package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.*;

public final class Configuration {

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;

    private String firstUrl = EMPTY_STRING;

    private StringMap<BeanInfo> beansInfos = new StringMap<BeanInfo>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = EMPTY_STRING;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName = "";

    private ContextEl context;

    private StringMap<ValidatorInfo> lateValidators = new StringMap<ValidatorInfo>();

    private String prefix = EMPTY_STRING;

    private final StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final StringMap<Struct> builtValidators = new StringMap<Struct>();

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl = "";

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    private RendLocalThrowing rendLocalThrowing = new RendLocalThrowing();
    private StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();

    private LongMap<LongTreeMap<NodeContainer>> containersMap;
    private CustList<LongTreeMap<NodeContainer>> containersMapStack;
    private IndexesFormInput indexes;
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();

    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();
    private StringList formsNames = new StringList();
    private LongMap<StringList> formatIdMap = new LongMap<StringList>();
    private CustList<StringList> formatIdMapStack = new CustList<StringList>();
    private Longs formsNb = new Longs();
    private Longs inputs = new Longs();
    private long currentForm;

    private Struct mainBean;
    private String currentLanguage = "";
    private final RendKeyWords rendKeyWords = new RendKeyWords();
    private RendDocumentBlock rendDocumentBlock;
    private StringMap<String> files = new StringMap<String>();

    public ArrayStruct newStackTraceElementArray() {
        int count_ = importing.size();
        int lenArrCtx_ = context.nbPages();
        Struct[] arr_ = new Struct[count_+ lenArrCtx_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(i);
        }
        for (int i = 0; i < lenArrCtx_; i++) {
            arr_[i+count_] = ExecutingUtil.newStackTraceElement(context,i);
        }
        String cl_ = context.getStandards().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    private StackTraceElementStruct newStackTraceElement(int _index) {
        ImportingPage call_ = importing.get(_index);
        int indexFileType_ = call_.getSum();
        int row_ = call_.getRowFile(indexFileType_);
        int col_ = call_.getColFile(indexFileType_,row_);
        String fileName_ = call_.getReadUrl();
        String currentClassName_ = call_.getGlobalClass();
        return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,"");
    }

    public void init() {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        prefix = StringList.concat(prefix,SEP);
        renderFiles.removeAllString(firstUrl);
        renderFiles.add(firstUrl);
    }

    public void setupRenders(StringMap<String> _files, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        renders.clear();
        setFiles(_files);
        setupInts(_page, _analyzingDoc);
        for (String s: renderFiles) {
            String link_ = RendExtractFromResources.getRealFilePath(currentLanguage,s);
            String file_ = _files.getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            if (document_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_analyzingDoc.getFileName());
                badEl_.setIndexFile(getCurrentLocationIndex(_page, _analyzingDoc));
                badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getBadDocument(),
                        res_.getLocation().display());
                addError(badEl_, _analyzingDoc, _page);
                continue;
            }
            currentUrl = link_;
            renders.put(link_,RendBlock.newRendDocumentBlock(this,getPrefix(), document_, file_, _page.getStandards()));
        }
        for (EntryCust<String,RendDocumentBlock> d: renders.entryList()) {
            d.getValue().buildFctInstructions(this, _analyzingDoc, _page);
        }
        String currentUrl_ = getFirstUrl();
        String realFilePath_ = RendExtractFromResources.getRealFilePath(currentLanguage, currentUrl_);
        rendDocumentBlock = getRenders().getVal(realFilePath_);
        if (rendDocumentBlock == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_analyzingDoc.getFileName());
            badEl_.setIndexFile(getCurrentLocationIndex(_page, _analyzingDoc));
            badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantFile(),
                    realFilePath_);
            addError(badEl_, _analyzingDoc, _page);
        }
    }
    public void initForms() {
        callsExps = new CustList<CustList<RendDynOperationNode>>();
        anchorsArgs = new CustList<StringList>();
        anchorsVars = new CustList<StringList>();
        containersMap = new LongMap<LongTreeMap<NodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<NodeContainer>>();
        indexes = new IndexesFormInput();
        callsFormExps = new CustList<CustList<RendDynOperationNode>>();
        formatIdMap = new LongMap<StringList>();
        formatIdMapStack = new CustList<StringList>();
        formsNb = new Longs();
        inputs = new Longs();
        formsArgs = new CustList<StringList>();
        formsVars = new CustList<StringList>();
        formsNames = new StringList();
        currentForm = 0;
    }

    Struct newBean(String _language, Struct _bean, BeanInfo _info) {
        Argument arg_ = RenderExpUtil.calculateReuse(_info.getExps(), this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct strBean_ = arg_.getStruct();
        getAdvStandards().forwardDataBase(_bean,strBean_,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        getAdvStandards().setStoredForms(strBean_, this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        getAdvStandards().setLanguage(strBean_, _language,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        String str_ = getAdvStandards().getScope(_bean,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        getAdvStandards().setScope(strBean_, str_,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public void setFirstUrl(String _firstUrl) {
        firstUrl = _firstUrl;
        setCurrentUrl(currentUrl);
    }

    public StringMap<BeanInfo> getBeansInfos() {
        return beansInfos;
    }

    public void setBeansInfos(StringMap<BeanInfo> _beansInfos) {
        beansInfos = _beansInfos;
    }

    public StringMap<StringMap<String>> getNavigation() {
        return navigation;
    }

    public void setNavigation(StringMap<StringMap<String>> _navigation) {
        navigation = _navigation;
    }

    public StringMap<String> getProperties() {
        return properties;
    }

    public void setProperties(StringMap<String> _properties) {
        properties = _properties;
    }

    public String getMessagesFolder() {
        return messagesFolder;
    }

    public void setMessagesFolder(String _messagesFolder) {
        messagesFolder = _messagesFolder;
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }


    public boolean noPages() {
        return importing.isEmpty();
    }
    public void clearPages() {
        importing.clear();
    }
    public void addPage(ImportingPage _page) {
        importing.add(_page);
    }
    public ImportingPage getLastPage() {
        return importing.last();
    }
    public void removeLastPage() {
        importing.removeLast();
    }
    public CustList<ImportingPage> getImporting() {
        return importing;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _currentUrl) {
        currentUrl = _currentUrl;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String _prefix) {
        prefix = _prefix;
    }

    public String getFilesConfName() {
        return filesConfName;
    }

    public void setFilesConfName(String _filesConfName) {
        filesConfName = _filesConfName;
    }

    public ContextEl getContext() {
        return context;
    }

    public void setContext(ContextEl _context) {
        context = _context;
    }

    public StringMap<ValidatorInfo> getLateValidators() {
        return lateValidators;
    }

    public void setLateValidators(StringMap<String> _lateValidators) {
        StringMap<ValidatorInfo> lateValidators_ = new StringMap<ValidatorInfo>();
        for (EntryCust<String, String> e: _lateValidators.entryList()) {
            ValidatorInfo val_ = new ValidatorInfo();
            val_.setClassName(e.getValue());
            lateValidators_.addEntry(e.getKey(), val_);
        }
        lateValidators = lateValidators_;
    }

    public StringMap<Struct> getBuiltBeans() {
        return builtBeans;
    }

    public StringMap<Struct> getBuiltValidators() {
        return builtValidators;
    }

    public BeanLgNames getAdvStandards() {
        return (BeanLgNames)context.getStandards();
    }


    public static void addWarning(FoundWarningInterpret _warning, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _warning.setLocationFile(_analyzingDoc.getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        _analyzing.addWarning(_warning);
    }

    public static void addError(FoundErrorInterpret _error, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _error.setLocationFile(_analyzingDoc.getLocationFile(_error.getFileName(),_error.getIndexFile()));
        _analyzing.addError(_error);
    }

    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public Classes getClasses() {
        return getContext().getClasses();
    }

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }
    public void setOpOffset(int _offset) {
        getLastPage().setOpOffset(_offset);
    }

    public PageEl getPageEl() {
        return importing.last().getPageEl();
    }

    public void setException(Struct _struct) {
        context.setException(_struct);
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    public static int getCurrentLocationIndex(AnalyzedPageEl _analyzing, AnalyzingDoc _analyzingDoc) {
        int offset_ = _analyzing.getOffset();
        return _analyzingDoc.getSum(offset_)+ _analyzing.getTraceIndex()-offset_;
    }

    public static void setupInts(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        _page.getMappingLocal().clear();
        _page.setCurrentAnaBlock(null);
        _page.setProcessKeyWord(new AdvancedProcessKeyWord(_page, _analyzingDoc));
        _page.setHiddenTypes(new AdvancedHiddenTypes(_page));
        _page.setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(_page, _analyzingDoc));
        _page.setCurrentConstraints(new AdvancedCurrentConstraints());
        _page.setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        _page.setLoopDeclaring(new AdvancedLoopDeclaring(_analyzingDoc));
        _page.setLocalDeclaring(new AdvancedLocalDeclaring(_analyzingDoc));
        _page.setBuildingConstraints(new AdvancedBuildingConstraints(_page));
        _page.setLocalizer(new AdvancedLocalizer(_page, _analyzingDoc));
        _page.setTokenValidation(new AdvancedTokenValidation(_page));
    }

    public StringList getAddedFiles() {
        return addedFiles;
    }

    public void setAddedFiles(StringList _addedFiles) {
        addedFiles = _addedFiles;
    }

    public boolean hasToExit(String _className) {
        return hasToExit(_className,null);
    }
    public boolean hasToExit(String _className, Argument _arg) {
        Classes classes_ = getClasses();
        String idCl_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock c_ = classes_.getClassBody(idCl_);
        if (c_ != null) {
            InitClassState res_ = context.getLocks().getState(getContext(), idCl_);
            if (res_ == InitClassState.NOT_YET) {
                getContext().setCallingState(new NotInitializedClass(idCl_,c_, _arg));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(idCl_,context);
                setException(causing_);
                return true;
            }
        }
        return false;
    }

    public boolean hasPages() {
        return !noPages();
    }

    public RendLocalThrowing getRendLocalThrowing() {
        return rendLocalThrowing;
    }

    public StringMap<RendDocumentBlock> getRenders() {
        return renders;
    }

    public void setRenderFiles(StringList _renderFiles) {
        renderFiles = _renderFiles;
    }

    public StringList getRenderFiles() {
        return renderFiles;
    }

    public IndexesFormInput getIndexes() {
        return indexes;
    }

    public CustList<LongTreeMap<NodeContainer>> getContainersMapStack() {
        return containersMapStack;
    }

    public LongMap<LongTreeMap<NodeContainer>> getContainersMap() {
        return containersMap;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<StringList> getAnchorsArgs() {
        return anchorsArgs;
    }

    public CustList<StringList> getAnchorsVars() {
        return anchorsVars;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public CustList<StringList> getFormatIdMapStack() {
        return formatIdMapStack;
    }

    public Longs getFormsNb() {
        return formsNb;
    }

    public Longs getInputs() {
        return inputs;
    }

    public LongMap<StringList> getFormatIdMap() {
        return formatIdMap;
    }

    public StringList getFormsNames() {
        return formsNames;
    }

    public CustList<StringList> getFormsVars() {
        return formsVars;
    }

    public CustList<StringList> getFormsArgs() {
        return formsArgs;
    }

    public long getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(long _currentForm) {
        currentForm = _currentForm;
    }

    public Struct getMainBean() {
        return mainBean;
    }

    public void setMainBean(Struct _mainBean) {
        mainBean = _mainBean;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String _currentLanguage) {
        currentLanguage = _currentLanguage;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }


    public RendDocumentBlock getRendDocumentBlock() {
        return rendDocumentBlock;
    }

    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public StringMap<String> getFiles() {
        return files;
    }

}
