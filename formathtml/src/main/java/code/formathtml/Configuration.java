package code.formathtml;

import code.bean.Bean;
import code.bean.BeanInfo;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.*;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.TypeOwnersDepends;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.structs.*;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.util.*;
import code.util.ints.MathFactory;

public final class Configuration implements ExecutableCode {
    private static final String INSTANCE = "$new ";

    private static final String NO_PARAM = "()";

    private static final String RETURN_LINE = "\n";

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;

    private String firstUrl = EMPTY_STRING;

    private StringMap<Validator> validators = new StringMap<Validator>();

    private StringMap<Translator> translators = new StringMap<Translator>();

    private StringMap<Bean> beans = new StringMap<Bean>();
    private StringMap<BeanInfo> beansInfos = new StringMap<BeanInfo>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = EMPTY_STRING;

    private MathFactory mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String filesConfName;

    private ContextEl context;

    private StringMap<String> lateValidators = new StringMap<String>();
    private StringMap<String> lateTranslators = new StringMap<String>();

    private String prefix = EMPTY_STRING;
    private BeanLgNames standards;
    private String dataBaseClassName;

    private boolean uncompressed;

    private int nextIndex;

    private boolean staticContext;

    private final StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final StringMap<Struct> builtValidators = new StringMap<Struct>();
    private final StringMap<Struct> builtTranslators = new StringMap<Struct>();

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl;

    private String html;

    private String resourceUrl;

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();
    private Interrupt interrupt;

    private AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private RendLocalThrowing rendLocalThrowing = new RendLocalThrowing();
    private StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();

    private LongMap<LongTreeMap<NodeContainer>> containersMap;
    private LongTreeMap<NodeContainer> containers;
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
    private long currentForm;
    private Element curForm;


    @Override
    public boolean isMerged() {
        return context.isMerged();
    }

    @Override
    public void setMerged(boolean _merged) {
        context.setMerged(_merged);
    }

    public void init() {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        if (prefix == null || prefix.isEmpty()) {
            prefix = EMPTY_STRING;
        } else {
            prefix = StringList.concat(prefix,SEP);
        }
        if (lateValidators == null) {
            lateValidators = new StringMap<String>();
        }
        if (lateTranslators == null) {
            lateTranslators = new StringMap<String>();
        }
        standards.build();
        standards.setupOverrides(context);
        standards.buildIterables(this);
        renderFiles.removeAllString(firstUrl);
        renderFiles.add(firstUrl);
    }

    public void setupClasses(StringMap<String> _files) {
        if (!(standards instanceof BeanCustLgNames)) {
            setupValiatorsTranslators();
            return;
        }
        String conf_ = getFilesConfName();
        if (conf_ == null) {
            return;
        }
        if (context == null) {
            return;
        }
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringList.quickEq(e.getKey(),conf_)) {
                content_ = StringList.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
        }
        if (content_.isEmpty()) {
            return;
        }
        StringMap<String> classFiles_ = new StringMap<String>();
        for (String f: content_) {
            for (EntryCust<String, String> e: _files.entryList()) {
                if (StringList.quickEq(e.getKey(), f)) {
                    classFiles_.put(f, e.getValue());
                    break;
                }
            }
        }
        //!classFiles_.isEmpty()
        Classes.validateAll(classFiles_, context);
        if (!context.getClasses().getErrorsDet().isEmpty()) {
            return;
        }
        StringList types_ = new StringList();
        for (EntryCust<String, Bean> e: getBeans().entryList()) {
            types_.add(e.getValue().getClassName());
        }
        for (EntryCust<String, String> e: getLateValidators().entryList()) {
            types_.add(e.getValue());
        }
        for (EntryCust<String, String> e: getLateTranslators().entryList()) {
            types_.add(e.getValue());
        }
        for (String s: types_) {
            if (!context.getClasses().isCustomType(s)) {
                return;
            }
        }
        for (EntryCust<String, String> e: getLateValidators().entryList()) {
            Struct str_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,e.getValue(),NO_PARAM), 0, this).getStruct();
            getBuiltValidators().put(e.getKey(), str_);
        }
        for (EntryCust<String, String> e: getLateTranslators().entryList()) {
            Struct str_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,e.getValue(),NO_PARAM), 0, this).getStruct();
            getBuiltTranslators().put(e.getKey(), str_);
        }
    }

    public void setupRenders(StringMap<String> _files) {
        renders.clear();
        analyzingDoc.setFiles(_files);
        context.setAnalyzing(new AnalyzedPageEl());
        getAnalyzing().setEnabledInternVars(false);
        for (String s: renderFiles) {
            String file_ = _files.getVal(s);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            if (document_ == null) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(getClasses().getErrorsDet());
                badEl_.setFileName(getCurrentFileName());
                badEl_.setIndexFile(getCurrentLocationIndex());
                getClasses().addError(badEl_);
                continue;
            }
            renders.put(s,RendBlock.newRendDocumentBlock(this,getPrefix(), document_, file_));
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
        containers = new LongTreeMap<NodeContainer>();
        indexes = new IndexesFormInput();
        callsFormExps = new CustList<CustList<RendDynOperationNode>>();
        formsArgs = new CustList<StringList>();
        formsVars = new CustList<StringList>();
        formsNames = new StringList();
        currentForm = 0;
        curForm = null;
    }
    void setupValiatorsTranslatorsTmp() {
        for (EntryCust<String, Bean> e: getBeans().entryList()) {
            Struct str_ = new BeanStruct( e.getValue());
            getBuiltBeans().addEntry(e.getKey(), str_);
        }
        for (EntryCust<String, Validator> e: getValidators().entryList()) {
            Struct str_ = new ValidatorStruct(e.getValue());
            getBuiltValidators().put(e.getKey(), str_);
        }
        for (EntryCust<String, Translator> e: getTranslators().entryList()) {
            Struct str_ = new TranslatorStruct(e.getValue());
            getBuiltTranslators().put(e.getKey(), str_);
        }
    }

    void setupValiatorsTranslators() {
        for (EntryCust<String, Validator> e: getValidators().entryList()) {
            Struct str_ = new ValidatorStruct(e.getValue());
            getBuiltValidators().put(e.getKey(), str_);
        }
        for (EntryCust<String, Translator> e: getTranslators().entryList()) {
            Struct str_ = new TranslatorStruct(e.getValue());
            getBuiltTranslators().put(e.getKey(), str_);
        }
    }

    public Struct newSimpleBean(String _language, Object _dataBase, BeanInfo _bean) {
        addPage(new ImportingPage(false));
        Struct strBean_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,_bean.getClassName(),NO_PARAM), 0, this).getStruct();
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        if (_dataBase != null) {
            String className_ = getDataBaseClassName();
            ExtractObject.setDataBase(this, strBean_, StdStruct.wrapStd(_dataBase, context, className_));
        } else {
            ExtractObject.setDataBase(this, strBean_, NullStruct.NULL_VALUE);
        }
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setForms(this, strBean_, new StringMapObjectStruct(new StringMapObject()));
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setLanguage(this, strBean_, _language);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        if (_bean.getScope() != null) {
            ExtractObject.setScope(this, strBean_, _bean.getScope());
        } else {
            ExtractObject.setScope(this, strBean_, EMPTY_STRING);
        }
        removeLastPage();
        if (context.getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
    }
    public Struct newSimpleBean(String _language, Struct _dataBase, BeanInfo _bean) {
        addPage(new ImportingPage(false));
        Struct strBean_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,_bean.getClassName(),NO_PARAM), 0, this).getStruct();
        BeanStruct str_ = (BeanStruct) strBean_;
        Bean bean_ = str_.getBean();
        Object db_ = null;
        if (_dataBase instanceof StdStruct) {
            db_ = ((StdStruct)_dataBase).getInstance();
        }
        bean_.setDataBase(db_);
        bean_.setForms(new StringMapObject());
        bean_.setLanguage(_language);
        bean_.setScope(_bean.getScope());
        removeLastPage();
        return strBean_;
    }
    Struct newBean(String _language, Struct _bean) {
        addPage(new ImportingPage(false));
        Struct strBean_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,_bean.getClassName(getContext()),NO_PARAM), 0, this).getStruct();
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        Struct db_ = ExtractObject.getDataBase(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setDataBase(this, strBean_, db_);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        Struct forms_ = ExtractObject.getForms(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setForms(this, strBean_, forms_);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setLanguage(this, strBean_, _language);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        String str_ = ExtractObject.getScope(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setScope(this, strBean_, str_);
        removeLastPage();
        if (context.getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
    }
    Struct newBean(String _language, Struct _bean, BeanInfo _info) {
        addPage(new ImportingPage(false));
        Argument arg_ = ElRenderUtil.calculateReuse(_info.getExps(), this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        Struct strBean_ = arg_.getStruct();
        Struct db_ = ExtractObject.getDataBase(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setDataBase(this, strBean_, db_);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        Struct forms_ = ExtractObject.getForms(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setForms(this, strBean_, forms_);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setLanguage(this, strBean_, _language);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        String str_ = ExtractObject.getScope(this, _bean);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        ExtractObject.setScope(this, strBean_, str_);
        removeLastPage();
        if (context.getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
    }

    void setSepPrefix(String _prefix) {
        prefix = StringList.concat(_prefix,SEP);
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public void setFirstUrl(String _firstUrl) {
        firstUrl = _firstUrl;
        setCurrentUrl(currentUrl);
    }

    public StringMap<Validator> getValidators() {
        return validators;
    }

    public void setValidators(StringMap<Validator> _validators) {
        validators = _validators;
    }

    public StringMap<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(StringMap<Translator> _translators) {
        translators = _translators;
    }

    public StringMap<BeanInfo> getBeansInfos() {
        return beansInfos;
    }

    public void setBeansInfos(StringMap<BeanInfo> _beansInfos) {
        beansInfos = _beansInfos;
    }

    public StringMap<Bean> getBeans() {
        return beans;
    }

    public void setBeans(StringMap<Bean> _beans) {
        beans = _beans;
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

    public void setHtmlPage(HtmlPage _htmlPage) {
        htmlPage = _htmlPage;
    }

    public MathFactory getMathFactory() {
        return mathFactory;
    }

    public void setMathFactory(MathFactory _mathFactory) {
        mathFactory = _mathFactory;
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


    public String joinPages() {
        StringList l_ = new StringList();
        for (ImportingPage p: importing) {
            l_.add(p.getInfos(this));
        }
        return StringList.join(l_, RETURN_LINE);
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String _resourceUrl) {
        resourceUrl = _resourceUrl;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String _prefix) {
        prefix = _prefix;
    }

    public void setupInterrupt(Interrupt _i) {
        interrupt = _i;
    }
    public boolean isInterrupt() {
        return interrupt.get();
    }

    public void setInterrupt(boolean _interrupt) {
        interrupt.set(_interrupt);
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

    public StringMap<String> getLateValidators() {
        return lateValidators;
    }

    public void setLateValidators(StringMap<String> _lateValidators) {
        lateValidators = _lateValidators;
    }

    public StringMap<String> getLateTranslators() {
        return lateTranslators;
    }

    public void setLateTranslators(StringMap<String> _lateTranslators) {
        lateTranslators = _lateTranslators;
    }

    public StringMap<Struct> getBuiltBeans() {
        return builtBeans;
    }

    public StringMap<Struct> getBuiltValidators() {
        return builtValidators;
    }

    public StringMap<Struct> getBuiltTranslators() {
        return builtTranslators;
    }

    @Override
    public CustList<GeneType> getClassBodies() {
        return getContext().getClassBodies();
    }
    @Override
    public GeneType getClassBody(String _type) {
        return getContext().getClassBody(_type);
    }
    @Override
    public LgNames getStandards() {
        return getAdvStandards();
    }

    public BeanLgNames getAdvStandards() {
        return standards;
    }
    public void setStandards(BeanLgNames _standards) {
        standards = _standards;
    }

    public String getDataBaseClassName() {
        return dataBaseClassName;
    }

    public void setDataBaseClassName(String _dataBaseClassName) {
        dataBaseClassName = _dataBaseClassName;
    }

    public boolean isUncompressed() {
        return uncompressed;
    }

    public void setUncompressed(boolean _uncompressed) {
        uncompressed = _uncompressed;
    }

    @Override
    public String getGlobalClass() {
        if (importing.isEmpty()) {
            return context.getGlobalClass();
        }
        return getLastPage().getGlobalClass();
    }

    @Override
    public void setGlobalClass(String _globalClass) {
        getLastPage().setGlobalClass(_globalClass);
    }

    @Override
    public Classes getClasses() {
        return getContext().getClasses();
    }

    @Override
    public String getCurrentFileName() {
        if (importing.isEmpty()) {
            return "";
        }
        return getLastPage().getReadUrl();
    }

    @Override
    public LoopVariable getVar(String _var) {
        if (importing.isEmpty()) {
            return context.getVar(_var);
        }
        return getLastPage().getVars().getVal(_var);
    }

    public StringMap<LoopVariable> getVars() {
        return getLastPage().getVars();
    }

    @Override
    public LocalVariable getLocalVar(String _key) {
        if (importing.isEmpty()) {
            return context.getLocalVar(_key);
        }
        return getLastPage().getLocalVar(_key);
    }

    @Override
    public boolean isFinalLocalVar(String _key, int _index) {
        return context.getAnalyzing().isFinalLocalVar(_key, _index);
    }

    @Override
    public boolean containsLocalVar(String _key) {
        if (importing.isEmpty()) {
            return context.containsLocalVar(_key);
        }
        return getLastPage().containsLocalVar(_key);
    }

    @Override
    public void putLocalVar(String _key, LocalVariable _loc) {
        if (importing.isEmpty()) {
            context.putLocalVar(_key, _loc);
            return;
        }
        getLastPage().putLocalVar(_key, _loc);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return getLastPage().getLocalVars();
    }

    public StringMap<LocalVariable> getCatchVars() {
        return getLastPage().getCatchVars();
    }

    @Override
    public LocalVariable getCatchVar(String _key) {
        if (importing.isEmpty()) {
            return context.getCatchVar(_key);
        }
        return getLastPage().getCatchVars().getVal(_key);
    }

    @Override
    public StringMap<LocalVariable> getParameters() {
        return context.getParameters();
    }

    @Override
    public int getOffset() {
        return getLastPage().getOffset();
    }

    @Override
    public void setAnalyzedOffset(int _offset) {
        context.setAnalyzedOffset(_offset);
    }

    @Override
    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }
    public void setOpOffset(int _offset) {
        getLastPage().setOpOffset(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return staticContext;
    }

    @Override
    public void setStaticContext(boolean _staticContext) {
        staticContext = _staticContext;
    }

    @Override
    public ClassMetaInfo getClassMetaInfo(String _name) {
        return getContext().getClassMetaInfo(_name);
    }

    @Override
    public CustList<GeneMethod> getMethodBodiesById(String _genericClassName,
            MethodId _id) {
        return getContext().getMethodBodiesById(_genericClassName, _id);
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    @Override
    public int getCurrentChildTypeIndex() {
        return 0;
    }

    @Override
    public String getCurrentVarSetting() {
        return context.getCurrentVarSetting();
    }

    @Override
    public void setCurrentVarSetting(String _currentVarSetting) {
        context.setCurrentVarSetting(_currentVarSetting);
    }

    @Override
    public Options getOptions() {
        return context.getOptions();
    }

    @Override
    public boolean isFinalVariable() {
        return context.isFinalVariable();
    }

    @Override
    public void setFinalVariable(boolean _finalVariable) {
        context.setFinalVariable(_finalVariable);
    }

    @Override
    public AssignedVariablesBlock getAssignedVariables() {
        return context.getAssignedVariables();
    }

    @Override
    public Block getCurrentBlock() {
        return context.getCurrentBlock();
    }

    @Override
    public boolean hasDeclarator() {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        return currentBlock_.getPreviousSibling() instanceof RendDeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        RendBlock previousSibling_ = currentBlock_.getPreviousSibling();
        ((RendDeclareVariable)previousSibling_).setImportedClassName(_className);
    }

    @Override
    public boolean hasLoopDeclarator() {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        return currentBlock_ instanceof RendForMutableIterativeLoop;
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        RendBlock currentBlock_ = analyzingDoc.getCurrentBlock();
        ((RendForMutableIterativeLoop)currentBlock_).setImportedClassName(_className);
    }

    @Override
    public boolean isGearConst() {
        return context.isGearConst();
    }

    @Override
    public CustList<StringMap<LocalVariable>> getLocalVariables() {
        return context.getLocalVariables();
    }

    @Override
    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return context.getAnalyzing().isFinalMutableLoopVar(_key,_index);
    }

    @Override
    public PageEl getOperationPageEl() {
        return importing.last().getPageEl();
    }

    @Override
    public void setException(Struct _struct) {
        context.setException(_struct);
    }

    @Override
    public Struct getException() {
        return context.getException();
    }

    @Override
    public ContextEl getContextEl() {
        return context;
    }

    @Override
    public StringList getNeedInterfaces() {
        return context.getNeedInterfaces();
    }

    @Override
    public StringMap<LocalVariable> getInternVars() {
        return context.getInternVars();
    }

    @Override
    public boolean isEnabledInternVars() {
        return context.isEnabledInternVars();
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

    @Override
    public String resolveAccessibleIdType(String _in) {
        return resolveDynamicType(_in, null);
    }

    @Override
    public String resolveAccessibleIdTypeWithoutError(String _in) {
        return resolveDynamicTypeEmpty(_in, null);
    }
    @Override
    public String resolveCorrectType(String _in) {
        return resolveDynamicType(_in, null);
    }
    @Override
    public String resolveCorrectAccessibleType(String _in, String _fromType) {
        return resolveDynamicType(_in, null);
    }
    @Override
    public String resolveCorrectType(String _in, boolean _exact) {
        return resolveDynamicType(_in, null);
    }

    @Override
    public String resolveCorrectTypeWithoutErrors(String _in, boolean _exact) {
        return resolveDynamicTypeEmpty(_in, null);
    }

    @Override
    public ClassMetaInfo getExtendedClassMetaInfo(String _name) {
        return context.getExtendedClassMetaInfo(_name);
    }

    @Override
    public FieldInfo getFieldInfo(ClassField _classField) {
        return context.getFieldInfo(_classField);
    }

    public String resolveDynamicType(String _in, RootBlock _file) {
        String res_ = PartTypeUtil.processExec(_in, context);
        if (res_.isEmpty()) {
            String defPkg_ = standards.getDefaultPkg();
            String type_ = ContextEl.removeDottedSpaces(StringList.concat(defPkg_,".",_in));
            if (standards.getStandards().contains(type_)) {
                return type_;
            }
        }
        if (res_.isEmpty()) {
            return standards.getAliasObject();
        }
        return res_;
    }

    public String resolveDynamicTypeEmpty(String _in, RootBlock _file) {
        String res_ = PartTypeUtil.processExec(_in, context);
        if (res_.isEmpty()) {
            String defPkg_ = standards.getDefaultPkg();
            String type_ = ContextEl.removeDottedSpaces(StringList.concat(defPkg_,".",_in));
            if (standards.getStandards().contains(type_)) {
                return type_;
            }
        }
        return res_;
    }
    @Override
    public AnalyzedPageEl getAnalyzing() {
        return context.getAnalyzing();
    }
    public CustList<StringMap<LocalVariable>> getLocalVarsAna() {
        return context.getAnalyzing().getLocalVars();
    }

    @Override
    public ObjectMap<ClassMethodId,Integer> lookupImportStaticMethods(
            String _glClass, String _method, Block _rooted) {
        return new ObjectMap<ClassMethodId,Integer>();
    }

    @Override
    public ObjectMap<ClassField,Integer> lookupImportStaticFields(String _glClass,String _field,
            Block _rooted) {
        return new ObjectMap<ClassField,Integer>();
    }

    @Override
    public String lookupImportType(String _type, AccessingImportingBlock _rooted) {
        return ContextEl.removeDottedSpaces(_type);
    }
    @Override
    public String lookupSingleImportType(String _type,
            AccessingImportingBlock _rooted) {
        return ContextEl.removeDottedSpaces(_type);
    }

    @Override
    public TypeOwnersDepends lookupImportMemberTypeDeps(String _type,
                                                        RootBlock _rooted) {
        return new TypeOwnersDepends();
    }
    @Override
    public String lookupImportMemberType(String _type, AccessingImportingBlock _rooted, boolean _inherits) {
        return ContextEl.removeDottedSpaces(_type);
    }

    @Override
    public StringList getAvailableVariables() {
        return context.getAvailableVariables();
    }

    @Override
    public StringList getVariablesNames() {
        return context.getVariablesNames();
    }

    @Override
    public boolean isAssignedStaticFields() {
        return context.isAssignedStaticFields();
    }

    @Override
    public void setAssignedStaticFields(boolean _assignedStaticFields) {
        context.setAssignedStaticFields(_assignedStaticFields);
    }

    @Override
    public boolean isAssignedFields() {
        return context.isAssignedFields();
    }

    @Override
    public void setAssignedFields(boolean _assignedFields) {
        context.setAssignedFields(_assignedFields);
    }

    @Override
    public boolean containsMutableLoopVar(String _string) {
        return context.containsMutableLoopVar(_string);
    }

    @Override
    public LoopVariable getMutableLoopVar(String _key) {
        return context.getMutableLoopVar(_key);
    }

    @Override
    public void putMutableLoopVar(String _string, LoopVariable _loc) {
        context.putMutableLoopVar(_string, _loc);
    }

    @Override
    public ForLoopPart getForLoopPartState() {
        return context.getForLoopPartState();
    }

    @Override
    public void setForLoopPartState(ForLoopPart _state) {
        context.setForLoopPartState(_state);
    }

    @Override
    public AnalyzingEl getAnalysisAss() {
        return context.getAnalysisAss();
    }

    @Override
    public boolean isAnnotAnalysis() {
        return context.isAnnotAnalysis();
    }

    @Override
    public void setAnnotAnalysis(boolean _ana) {
        context.setAnnotAnalysis(_ana);
    }

    @Override
    public void putLocalVar(String _string) {
        context.putLocalVar(_string);
    }

    @Override
    public StringList getInfersLocalVars() {
        return context.getInfersLocalVars();
    }

    @Override
    public StringList getInfersMutableLocalVars() {
        return context.getInfersMutableLocalVars();
    }

    @Override
    public void putMutableLoopVar(String _string) {
        context.putMutableLoopVar(_string);
    }

    @Override
    public String getLookLocalClass() {
        return context.getLookLocalClass();
    }

    @Override
    public void setLookLocalClass(String _lookLocalClass) {
        context.setLookLocalClass(_lookLocalClass);
    }

    @Override
    public boolean isOkNumOp() {
        return context.isOkNumOp();
    }

    @Override
    public void setOkNumOp(boolean _okNumOp) {
        context.setOkNumOp(_okNumOp);
    }

    @Override
    public KeyWords getKeyWords() {
        return context.getKeyWords();
    }

    @Override
    public void setKeyWords(KeyWords _keyWords) {
        context.setKeyWords(_keyWords);
    }

    @Override
    public StringMap<StringList> getCurrentConstraints() {
        return context.getCurrentConstraints();
    }

    @Override
    public Ints getCurrentBadIndexes() {
        return context.getCurrentBadIndexes();
    }

    @Override
    public int getCurrentLocationIndex() {
        AnalyzedPageEl analyzing_ = context.getAnalyzing();
        if (analyzing_ == null) {
            return 0;
        }
        int offset_ = analyzing_.getOffset();
        return analyzingDoc.getSum(offset_)+analyzing_.getTraceIndex()-offset_;
    }

    @Override
    public boolean isValidSingleToken(String _id) {
        if (!isValidToken(_id)) {
            return false;
        }
        return context.idDisjointToken(_id);
    }

    @Override
    public boolean isValidToken(String _id) {
        return context.isValidToken(_id,false);
    }

    @Override
    public void processInternKeyWord(String _string, int _fr,
            ResultAfterInstKeyWord _out) {
        KeyWords keyWords_ = getKeyWords();
        String keyWordIntern_ = keyWords_.getKeyWordIntern();
        String sub_ = _string.substring(_fr);
        int i_ = _fr;
        if (isInternGlobal()) {
            if (ContextEl.startsWithKeyWord(sub_, keyWordIntern_)) {
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
        String currentVarSetting_ = "";
        if (context.getAnalyzing() != null) {
            merged_ = isMerged();
            currentVarSetting_ = getCurrentVarSetting();
        }
        context.setAnalyzing(new AnalyzedPageEl());
        context.getAnalyzing().setGlobalClass(getGlobalClass());
        context.getAnalyzing().initLocalVars();
        context.getAnalyzing().initMutableLoopVars();
        CustList<StringMap<LocalVariable>> l_ = new CustList<StringMap<LocalVariable>>();
        l_.add(getLocalVars());
        context.getAnalyzing().setLocalVars(l_);
        CustList<StringMap<LoopVariable>> lv_ = new CustList<StringMap<LoopVariable>>();
        lv_.add(getVars());
        context.getAnalyzing().setVars(lv_);
        CustList<StringMap<LocalVariable>> lc_ = new CustList<StringMap<LocalVariable>>();
        lc_.add(getCatchVars());
        context.getAnalyzing().setCatchVars(lc_);
        context.getAnalyzing().getParameters().putAllMap(getParameters());
        context.getAnalyzing().setMerged(merged_);
        context.getAnalyzing().setCurrentVarSetting(currentVarSetting_);
    }

    public StringList getAddedFiles() {
        return addedFiles;
    }

    public void setAddedFiles(StringList _addedFiles) {
        addedFiles = _addedFiles;
    }

    @Override
    public boolean hasToExit(String _className) {
        Classes classes_ = getClasses();
        if (classes_.isCustomType(_className)) {
            InitClassState res_ = classes_.getLocks().getState(getContextEl(), _className);
            if (res_ == InitClassState.NOT_YET) {
                getContextEl().setInitClass(new NotInitializedClass(_className));
                return true;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(_className,this);
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

    public void setAnalyzingDoc(AnalyzingDoc _analyzingDoc) {
        analyzingDoc = _analyzingDoc;
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

    public LongMap<LongTreeMap<NodeContainer>> getContainersMap() {
        return containersMap;
    }

    public LongTreeMap<NodeContainer> getContainers() {
        return containers;
    }

    public void setContainers(LongTreeMap<NodeContainer> _containers) {
        containers = _containers;
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

    public Element getCurForm() {
        return curForm;
    }

    public void setCurForm(Element _curForm) {
        curForm = _curForm;
    }
}
