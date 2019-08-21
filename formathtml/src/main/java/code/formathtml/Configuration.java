package code.formathtml;

import code.bean.Bean;
import code.bean.BeanInfo;
import code.bean.validator.Validator;
import code.bean.validator.ValidatorInfo;
import code.expressionlanguage.*;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.errors.custom.IllegalCallCtorByType;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeOwnersDepends;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.structs.*;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.*;

public final class Configuration implements ExecutableCode {
    private static final String INSTANCE = "$new ";

    private static final String NO_PARAM = "()";

    private static final String RETURN_LINE = "\n";

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;

    private String firstUrl = EMPTY_STRING;

    private StringMap<Validator> validators = new StringMap<Validator>();

    private StringMap<Bean> beans = new StringMap<Bean>();
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
    private String dataBaseClassName = EMPTY_STRING;

    private int nextIndex;

    private boolean staticContext;

    private final StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final StringMap<Struct> builtValidators = new StringMap<Struct>();

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl = "";

    private String html;

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();
    private Interrupt interrupt;

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

    @Override
    public boolean isMerged() {
        return context.isMerged();
    }

    @Override
    public ExecutableCode getExecutingInstance() {
        return this;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        int count_ = importing.size();
        int lenArrCtx_ = context.nbPages();
        Struct[] arr_ = new Struct[count_+ lenArrCtx_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(i);
        }
        for (int i = 0; i < lenArrCtx_; i++) {
            arr_[i+count_] = context.newStackTraceElement(i);
        }
        String cl_ = getStandards().getAliasStackTraceElement();
        cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    @Override
    public StackTraceElementStruct newStackTraceElement(int _index) {
        ImportingPage call_ = importing.get(_index);
        int indexFileType_ = call_.getSum();
        int row_ = call_.getRowFile(indexFileType_);
        int col_ = call_.getColFile(indexFileType_,row_);
        String fileName_ = call_.getReadUrl();
        String currentClassName_ = call_.getGlobalClass();
        return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,"");
    }
    @Override
    public void setMerged(boolean _merged) {
        context.setMerged(_merged);
    }

    public void init() {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        prefix = StringList.concat(prefix,SEP);
        standards.build();
        standards.setupOverrides(context);
        renderFiles.removeAllString(firstUrl);
        renderFiles.add(firstUrl);
        context.setExecutingInstance(this);
    }

    public void setupRendClasses(StringMap<String> _files) {
        if (!(standards instanceof BeanCustLgNames)) {
            return;
        }
        String conf_ = getFilesConfName();
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringList.quickEq(e.getKey(),conf_)) {
                content_ = StringList.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
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
        standards.buildIterables(this);
    }
    public void setupRenders(StringMap<String> _files) {
        renders.clear();
        analyzingDoc.setFiles(_files);
        context.setAnalyzing(new AnalyzedPageEl());
        getAnalyzing().setEnabledInternVars(false);
        for (String s: renderFiles) {
            String link_ = RendExtractFromResources.getRealFilePath(currentLanguage,s);
            String file_ = _files.getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            if (document_ == null) {
                BadElError badEl_ = new BadElError();
                badEl_.setFileName(getCurrentFileName());
                badEl_.setIndexFile(getCurrentLocationIndex());
                getClasses().addError(badEl_);
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

    public Struct newSimpleBean(String _language, Struct _dataBase, BeanInfo _bean) {
        addPage(new ImportingPage());
        Struct strBean_ = RenderExpUtil.processEl(StringList.concat(INSTANCE,_bean.getClassName(),NO_PARAM), 0, this).getStruct();
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

    Struct newBean(String _language, Struct _bean, BeanInfo _info) {
        addPage(new ImportingPage());
        Argument arg_ = RenderExpUtil.calculateReuse(_info.getExps(), this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        Struct strBean_ = arg_.getStruct();
        standards.forwardDataBase(_bean,strBean_,this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        standards.setStoredForms(strBean_, this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        standards.setLanguage(strBean_, _language,this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        String str_ = standards.getScope(_bean,this);
        if (context.getException() != null) {
            removeLastPage();
            return NullStruct.NULL_VALUE;
        }
        standards.setScope(strBean_, str_,this);
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String _html) {
        html = _html;
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

    @Override
    public String getGlobalClass() {
        return context.getGlobalClass();
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
        return analyzingDoc.getFileName();
    }

    @Override
    public LoopVariable getVar(String _var) {
        return context.getVar(_var);
    }

    public StringMap<LoopVariable> getVars() {
        return getLastPage().getVars();
    }

    @Override
    public LocalVariable getLocalVar(String _key) {
        return context.getLocalVar(_key);
    }

    @Override
    public boolean isFinalLocalVar(String _key, int _index) {
        return context.getAnalyzing().isFinalLocalVar(_key, _index);
    }

    @Override
    public boolean containsLocalVar(String _key) {
        return context.containsLocalVar(_key);
    }

    @Override
    public void putLocalVar(String _key, LocalVariable _loc) {
        context.putLocalVar(_key, _loc);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return getLastPage().getLocalVars();
    }

    public StringMap<LocalVariable> getCatchVars() {
        return getLastPage().getCatchVars();
    }

    @Override
    public LocalVariable getCatchVar(String _key) {
        return context.getCatchVar(_key);
    }

    @Override
    public StringMap<LocalVariable> getParameters() {
        return context.getParameters();
    }

    @Override
    public int getOffset() {
        return context.getOffset();
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
    public int getCurrentChildTypeIndex(OperationNode _op, GeneType _type, String _fieldName, String _realClassName) {
        if (ContextEl.isEnumType(_type)) {
            IllegalCallCtorByType call_ = new IllegalCallCtorByType();
            call_.setType(_realClassName);
            call_.setFileName(getCurrentFileName());
            call_.setIndexFile(getCurrentLocationIndex());
            getClasses().addError(call_);
            _op.setResultClass(new ClassArgumentMatching(_realClassName));
            return -2;
        }
        return -1;
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
    public AnalyzedBlock getCurrentAnaBlock() {
        return analyzingDoc.getCurrentBlock();
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
    public ExecutableCode getCurrentExec() {
        return this;
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
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(analyzingDoc.getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            getClasses().addError(un_);
            return "";
        }
        StringList inners_ = Templates.getAllInnerTypes(_in);
        String base_ = inners_.first().trim();
        String res_ = ContextEl.removeDottedSpaces(base_);
        if (standards.getStandards().contains(res_)) {
            return res_;
        }
        RootBlock b_ = getClasses().getClassBody(res_);
        if (b_ == null) {
            String defPkg_ = standards.getDefaultPkg();
            String type_ = ContextEl.removeDottedSpaces(StringList.concat(defPkg_,".",_in));
            if (standards.getStandards().contains(type_)) {
                return type_;
            }
            UnknownClassName undef_;
            undef_ = new UnknownClassName();
            undef_.setClassName(base_);
            undef_.setFileName(analyzingDoc.getFileName());
            undef_.setIndexFile(rc_);
            getClasses().addError(undef_);
            return "";
        }
        for (String i: inners_.mid(1)) {
            String resId_ = StringList.concat(res_,"..",i.trim());
            RootBlock inner_ = getClasses().getClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                UnknownClassName undef_;
                undef_ = new UnknownClassName();
                undef_.setClassName(base_);
                undef_.setFileName(analyzingDoc.getFileName());
                undef_.setIndexFile(rc_);
                getClasses().addError(undef_);
                return "";
            }
            res_ = resId_;
        }
        return res_;
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
        int rc_ = getCurrentLocationIndex();
        String void_ = standards.getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(analyzingDoc.getFileName());
            un_.setIndexFile(rc_);
            un_.setType(_in);
            getClasses().addError(un_);
            return standards.getAliasObject();
        }
        AccessedBlock r_ = analyzingDoc.getCurrentDoc();
        StringList varsList_ = new StringList();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = Templates.getIdFromAllTypes(_fromType);
        GeneType from_ = getClassBody(idFromType_);
        for (TypeVar t: from_.getParamTypesMapValues()) {
            varsList_.add(t.getName());
            vars_.put(t.getName(), t.getConstraints());
        }
        getAvailableVariables().clear();
        getAvailableVariables().addAllElts(varsList_);
        String resType_ = PartTypeUtil.processAnalyzeAccessibleId(_in, this, r_);
        if (resType_.trim().isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(analyzingDoc.getFileName());
            un_.setIndexFile(rc_);
            getClasses().addError(un_);
            return standards.getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, this, true)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(_in);
            un_.setFileName(analyzingDoc.getFileName());
            un_.setIndexFile(rc_);
            getClasses().addError(un_);
            return standards.getAliasObject();
        }
        return resType_;
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
    public String lookupImportType(String _type, AccessedBlock _rooted) {
        return ContextEl.removeDottedSpaces(_type);
    }
    @Override
    public String lookupSingleImportType(String _type,
                                         AccessedBlock _rooted) {
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
        String globalClass_ = "";
        if (context.getAnalyzing() != null) {
            merged_ = isMerged();
            currentVarSetting_ = getCurrentVarSetting();
            globalClass_ = getGlobalClass();
        }
        context.setAnalyzing(new AnalyzedPageEl());
        context.getAnalyzing().setGlobalClass(globalClass_);
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
                getContextEl().setCallingState(new NotInitializedClass(_className));
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
}
