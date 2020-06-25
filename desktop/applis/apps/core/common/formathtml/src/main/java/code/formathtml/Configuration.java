package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.options.ValidatorStandard;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.errors.stds.StdErrorList;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.instr.*;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
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
    private BeanLgNames standards;

    private int nextIndex;

    private final StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final StringMap<Struct> builtValidators = new StringMap<Struct>();

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl = "";

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    private AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private RendLocalThrowing rendLocalThrowing = new RendLocalThrowing();
    private StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();

    private LongMap<LongTreeMap<NodeContainer>> containersMap;
    private CustList<LongTreeMap<NodeContainer>> containersMapStack;
    private IndexesFormInput indexes;
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();
    private BooleanList constAnchors = new BooleanList();
    private StringList anchorsNames = new StringList();

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
    private final RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();
    private final RendKeyWords rendKeyWords = new RendKeyWords();
    private final StdErrorList stdErrorDet = new StdErrorList();
    private final ErrorList errorsDet = new ErrorList();
    private final WarningList warningsDet = new WarningList();

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
        String cl_ = getStandards().getAliasStackTraceElement();
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
        standards.build();
        ValidatorStandard.setupOverrides(context);
        renderFiles.removeAllString(firstUrl);
        renderFiles.add(firstUrl);
    }

    public void setupRenders(StringMap<String> _files) {
        renders.clear();
        analyzingDoc.setFiles(_files);
        setupInts();
        for (String s: renderFiles) {
            String link_ = RendExtractFromResources.getRealFilePath(currentLanguage,s);
            String file_ = _files.getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            if (document_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(getCurrentFileName());
                badEl_.setIndexFile(getCurrentLocationIndex());
                badEl_.buildError(getRendAnalysisMessages().getBadDocument(),
                        res_.getLocation().display());
                addError(badEl_);
                continue;
            }
            currentUrl = link_;
            renders.put(link_,RendBlock.newRendDocumentBlock(this,getPrefix(), document_, file_));
        }
        for (EntryCust<String,RendDocumentBlock> d: renders.entryList()) {
            d.getValue().buildFctInstructions(this);
        }
    }
    public void initForms() {
        callsExps = new CustList<CustList<RendDynOperationNode>>();
        anchorsArgs = new CustList<StringList>();
        anchorsVars = new CustList<StringList>();
        constAnchors = new BooleanList();
        anchorsNames = new StringList();
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
        standards.forwardDataBase(_bean,strBean_,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        standards.setStoredForms(strBean_, this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        standards.setLanguage(strBean_, _language,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        String str_ = standards.getScope(_bean,this);
        if (context.hasException()) {
            return NullStruct.NULL_VALUE;
        }
        standards.setScope(strBean_, str_,this);
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

    public LgNames getStandards() {
        return getAdvStandards();
    }

    public BeanLgNames getAdvStandards() {
        return standards;
    }
    public void setStandards(BeanLgNames _standards) {
        standards = _standards;
    }


    public String getGlobalClass() {
        return getAnalyzing().getGlobalClass();
    }

    public String getLocationFile(String _fileName, int _sum) {
        return StringList.concat(Integer.toString(_sum));
    }

    public void addWarning(FoundWarningInterpret _warning) {
        _warning.setLocationFile(getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        warningsDet.add(_warning);
    }

    public void addError(FoundErrorInterpret _error) {
        _error.setLocationFile(getLocationFile(_error.getFileName(),_error.getIndexFile()));
        errorsDet.add(_error);
    }

    public RendAnalysisMessages getRendAnalysisMessages() {
        return rendAnalysisMessages;
    }

    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public void addStdError(StdWordError _err) {
        stdErrorDet.add(_err);
    }

    public StdErrorList getStdErrorDet() {
        return stdErrorDet;
    }

    public boolean isEmptyStdErrors() {
        return stdErrorDet.isEmpty();
    }

    public boolean isEmptyErrors() {
        return standards.getHeaders().isEmptyErrors() && getErrorsDet().isEmpty();
    }

    public ErrorList getErrorsDet() {
        return errorsDet;
    }

    public Classes getClasses() {
        return getContext().getClasses();
    }

    public String getCurrentFileName() {
        return analyzingDoc.getFileName();
    }

    public StringMap<AnaLoopVariable> getVars() {
        StringMap<AnaLoopVariable> m_ = new StringMap<AnaLoopVariable>();
        for (EntryCust<String,LoopVariable> e: getLastPage().getVars().entryList()) {
            AnaLoopVariable a_ = new AnaLoopVariable();
            a_.setClassName(e.getValue().getClassName());
            a_.setIndexClassName(e.getValue().getIndexClassName());
            m_.addEntry(e.getKey(), a_);
        }
        return m_;
    }

    public StringMap<AnaLocalVariable> getLocalVars() {
        StringMap<AnaLocalVariable> m_ = new StringMap<AnaLocalVariable>();
        for (EntryCust<String,LocalVariable> e: getLastPage().getLocalVars().entryList()) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getValue().getClassName());
            m_.addEntry(e.getKey(), a_);
        }
        return m_;
    }

    public StringMap<AnaLocalVariable> getCatchVars() {
        StringMap<AnaLocalVariable> m_ = new StringMap<AnaLocalVariable>();
        for (EntryCust<String,LocalVariable> e: getLastPage().getCatchVars().entryList()) {
            AnaLocalVariable a_ = new AnaLocalVariable();
            a_.setClassName(e.getValue().getClassName());
            m_.addEntry(e.getKey(), a_);
        }
        return m_;
    }

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }
    public void setOpOffset(int _offset) {
        getLastPage().setOpOffset(_offset);
    }

    public boolean isStaticAccess() {
        return context.getAnalyzing().isStaticContext();
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public boolean hasDeclarator() {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        return currentBlock_.getPreviousSibling() instanceof RendDeclareVariable;
    }

    public void setupDeclaratorClass(String _className) {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        RendBlock previousSibling_ = currentBlock_.getPreviousSibling();
        ((RendDeclareVariable)previousSibling_).setImportedClassName(_className);
    }

    public boolean hasLoopDeclarator() {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        return currentBlock_ instanceof RendForMutableIterativeLoop;
    }

    public void setupLoopDeclaratorClass(String _className) {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        ((RendForMutableIterativeLoop)currentBlock_).setImportedClassName(_className);
    }

    public PageEl getPageEl() {
        return importing.last().getPageEl();
    }

    public void setException(Struct _struct) {
        context.setException(_struct);
    }

    public boolean isInternGlobal() {
        return !analyzingDoc.getInternGlobalClass().isEmpty();
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    public String getInternGlobalClass() {
        return analyzingDoc.getInternGlobalClass();
    }

    private static AccessedBlock getAccessingImportingBlock(AccessedBlock _r, ExecRootBlock _root) {
        AccessedBlock a_;
        if (_root != null) {
            a_ = _root;
        } else {
            a_ = _r;
        }
        return a_;
    }

    public AnalyzedPageEl getAnalyzing() {
        return context.getAnalyzing();
    }

    public CustList<StringMap<AnaLocalVariable>> getLocalVarsAna() {
        return context.getAnalyzing().getLocalVars();
    }

    public String getIndexClassName() {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        return ((RendForMutableIterativeLoop)currentBlock_).getImportedClassIndexName();
    }

    public KeyWords getKeyWords() {
        return context.getKeyWords();
    }

    public int getCurrentLocationIndex() {
        AnalyzedPageEl analyzing_ = context.getAnalyzing();
        int offset_ = analyzing_.getOffset();
        return analyzingDoc.getSum(offset_)+analyzing_.getTraceIndex()-offset_;
    }

    public boolean isValidSingleToken(String _id) {
        if (!isValidToken(_id)) {
            return false;
        }
        return ContextUtil.idDisjointToken(context,_id);
    }

    public boolean isValidToken(String _id) {
        return ContextUtil.isValidToken(context,_id,false);
    }

    public void processInternKeyWord(String _string, int _fr,
            ResultAfterInstKeyWord _out) {
        KeyWords keyWords_ = getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String sub_ = _string.substring(_fr);
        int i_ = _fr;
        if (isInternGlobal()) {
            if (StringExpUtil.startsWithKeyWord(sub_, keyWordIntern_)) {
                int afterSuper_ = i_ + keyWordIntern_.length();
                String trim_ = _string.substring(afterSuper_).trim();
                if (trim_.startsWith(".")) {
                    //_string.charAt(afterSuper_) != EXTERN_CLASS && !foundHat_
                    i_ = _string.indexOf('.',afterSuper_);
                    _out.setNextIndex(i_);
                }
            }
        }
    }

    public void setupAnalyzing() {
        boolean merged_ = false;
        boolean accept_ = false;
        String currentVarSetting_ = "";
        String globalClass_ = "";
        if (context.getAnalyzing() != null) {
            merged_ = getAnalyzing().isMerged();
            accept_ = getAnalyzing().isAcceptCommaInstr();
            currentVarSetting_ = context.getAnalyzing().getCurrentVarSetting();
            globalClass_ = getGlobalClass();
        }
        setupInts();
        context.getAnalyzing().setGlobalClass(globalClass_);
        context.getAnalyzing().initLocalVars();
        context.getAnalyzing().initMutableLoopVars();
        CustList<StringMap<AnaLocalVariable>> l_ = new CustList<StringMap<AnaLocalVariable>>();
        l_.add(getLocalVars());
        context.getAnalyzing().setLocalVars(l_);
        CustList<StringMap<AnaLoopVariable>> lv_ = new CustList<StringMap<AnaLoopVariable>>();
        lv_.add(getVars());
        context.getAnalyzing().setVars(lv_);
        CustList<StringMap<AnaLocalVariable>> lc_ = new CustList<StringMap<AnaLocalVariable>>();
        lc_.add(getCatchVars());
        context.getAnalyzing().setCatchVars(lc_);
        context.getAnalyzing().setMerged(merged_);
        context.getAnalyzing().setAcceptCommaInstr(accept_);
        context.getAnalyzing().setCurrentVarSetting(currentVarSetting_);
    }

    public void setupInts() {
        context.setAnalyzing();
        context.getAnalyzing().getAllFoundTypes().addAllElts(standards.getHeaders().getAllFound());
        context.getAnalyzing().setHeaders(standards.getHeaders());
        context.getAnalyzing().setProcessKeyWord(new AdvancedProcessKeyWord(this));
        context.getAnalyzing().setHiddenTypes(new AdvancedHiddenTypes(this));
        context.getAnalyzing().setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(this));
        context.getAnalyzing().setCurrentConstraints(new AdvancedCurrentConstraints());
        context.getAnalyzing().setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        context.getAnalyzing().setLoopDeclaring(new AdvancedLoopDeclaring(this));
        context.getAnalyzing().setLocalDeclaring(new AdvancedLocalDeclaring(this));
        context.getAnalyzing().setBuildingConstraints(new AdvancedBuildingConstraints(this));
        context.getAnalyzing().setLocalizer(new AdvancedLocalizer(this));
        context.getAnalyzing().setTokenValidation(new AdvancedTokenValidation(this));
    }

    public StringList getAddedFiles() {
        return addedFiles;
    }

    public void setAddedFiles(StringList _addedFiles) {
        addedFiles = _addedFiles;
    }

    public boolean hasToExit(String _className) {
        Classes classes_ = getClasses();
        String idCl_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock c_ = classes_.getClassBody(idCl_);
        if (c_ != null) {
            InitClassState res_ = classes_.getLocks().getState(getContext(), idCl_);
            if (res_ == InitClassState.NOT_YET) {
                getContext().setCallingState(new NotInitializedClass(idCl_));
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

    public AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
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

    public BooleanList getConstAnchors() {
        return constAnchors;
    }

    public StringList getAnchorsNames() {
        return anchorsNames;
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


    public RendDocumentBlock getCurrentGlobalBlock() {
        return getAnalyzingDoc().getCurrentDoc();
    }


    public AccessedBlock getCurrentGlobalBlock(AccessedBlock _bl) {
        String gl_ = getGlobalClass();
        ExecRootBlock root_ = getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(gl_));
        return getAccessingImportingBlock(_bl, root_);
    }

    public void buildCurrentConstraintsFull() {
        context.getAnalyzing().getAvailableVariables().clear();
    }
}
