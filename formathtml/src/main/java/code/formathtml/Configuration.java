package code.formathtml;
import java.util.concurrent.atomic.AtomicBoolean;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeOwnersDepends;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.AnalyzingEl;
import code.expressionlanguage.methods.AssignedVariablesBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForLoopPart;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.types.PartTypeUtil;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanStruct;
import code.formathtml.util.StringMapObjectStruct;
import code.formathtml.util.TranslatorStruct;
import code.formathtml.util.ValidatorStruct;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.MathFactory;

public class Configuration implements ExecutableCode {
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

    private boolean ambigous;

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

    private AtomicBoolean interrupt = new AtomicBoolean();
    @Override
    public boolean isMerged() {
        return context.isMerged();
    }

    @Override
    public void setMerged(boolean _merged) {
        context.setMerged(_merged);
    }

    public final void init() {
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
        standards.setContext(context);
        standards.build();
        standards.setupOverrides(context);
    }

    public final void setupClasses(StringMap<String> _files) {
        String conf_ = getFilesConfName();
        if (conf_ == null) {
            setupValiatorsTranslators();
            return;
        }
        if (context == null) {
            setupValiatorsTranslators();
            return;
        }
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (e.getKey().equalsIgnoreCase(conf_)) {
                content_ = StringList.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
        }
        if (content_.isEmpty()) {
            setupValiatorsTranslators();
            return;
        }
        StringMap<String> classFiles_ = new StringMap<String>();
        boolean allFound_ = true;
        for (String f: content_) {
            boolean found_ = false;
            for (EntryCust<String, String> e: _files.entryList()) {
                if (StringList.quickEq(e.getKey(), f)) {
                    classFiles_.put(f, e.getValue());
                    found_ = true;
                    break;
                }
            }
            if (!found_) {
                allFound_ = false;
            }
        }
        if (!allFound_) {
            classFiles_.clear();
            for (String f: content_) {
                String contentFile_ = ResourceFiles.ressourceFichier(f);
                if (contentFile_.isEmpty()) {
                    setupValiatorsTranslators();
                    return;
                }
                classFiles_.put(f, contentFile_);
            }
        }
        //!classFiles_.isEmpty()
        StringList duplicates_;
        duplicates_ = new StringList();
        duplicates_.addAllElts(getLateValidators().getKeys());
        duplicates_.addAllElts(getValidators().getKeys());
        int len_ = duplicates_.size();
        duplicates_.removeDuplicates();
        if (len_ != duplicates_.size()) {
            setupValiatorsTranslators();
            return;
        }
        duplicates_.clear();
        duplicates_.addAllElts(getLateTranslators().getKeys());
        duplicates_.addAllElts(getTranslators().getKeys());
        len_ = duplicates_.size();
        duplicates_.removeDuplicates();
        if (len_ != duplicates_.size()) {
            setupValiatorsTranslators();
            return;
        }
        Classes.validateAll(classFiles_, context);
        if (!context.getClasses().getErrorsDet().isEmpty()) {
            setupValiatorsTranslators();
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
                setupValiatorsTranslators();
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
    void setupValiatorsTranslators(String _language) {
        for (EntryCust<String, Bean> e: getBeans().entryList()) {
            Struct str_ = newBean(_language, null, e.getValue(), false);
            if (context.getException() != null) {
                return;
            }
            getBuiltBeans().put(e.getKey(), str_);
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

    public Struct newBean(String _language, Object _dataBase, Bean _bean, boolean _set) {
        if (!_set) {
            return new BeanStruct(_bean);
        }
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
        if (_bean == null || _bean.getForms() == null) {
            ExtractObject.setForms(this, strBean_, new StringMapObjectStruct(new StringMapObject()));
        } else {
            ExtractObject.setForms(this, strBean_, new StringMapObjectStruct(_bean.getForms()));
        }
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

    Struct newBean(String _language, Struct _bean) {
        addPage(new ImportingPage(false));
        Struct strBean_ = ElRenderUtil.processEl(StringList.concat(INSTANCE,_bean.getClassName(toContextEl()),NO_PARAM), 0, this).getStruct();
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

    final void setSepPrefix(String _prefix) {
        prefix = StringList.concat(_prefix,SEP);
    }

    public final ContextEl toContextEl() {
        context.setHtml(html);
        return context;
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

    public final HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public final void setHtmlPage(HtmlPage _htmlPage) {
        htmlPage = _htmlPage;
    }

    public final MathFactory getMathFactory() {
        return mathFactory;
    }

    public final void setMathFactory(MathFactory _mathFactory) {
        mathFactory = _mathFactory;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public final Document getDocument() {
        return document;
    }

    public final void setDocument(Document _document) {
        document = _document;
    }

    @Override
    public final String joinPages() {
        StringList l_ = new StringList();
        for (ImportingPage p: importing) {
            l_.add(p.getInfos(this));
        }
        return l_.join(RETURN_LINE);
    }
    public final boolean noPages() {
        return importing.isEmpty();
    }
    public final void clearPages() {
        importing.clear();
    }
    public final void addPage(ImportingPage _page) {
        importing.add(_page);
    }
    public final ImportingPage getLastPage() {
        return importing.last();
    }
    public final void removeLastPage() {
        importing.removeLast();
    }
    public final CustList<ImportingPage> getImporting() {
        return importing;
    }

    public final String getCurrentUrl() {
        return currentUrl;
    }

    public final void setCurrentUrl(String _currentUrl) {
        currentUrl = _currentUrl;
    }

    public final String getHtml() {
        return html;
    }

    public final void setHtml(String _html) {
        html = _html;
    }

    public final String getResourceUrl() {
        return resourceUrl;
    }

    public final void setResourceUrl(String _resourceUrl) {
        resourceUrl = _resourceUrl;
    }

    public final String getPrefix() {
        return prefix;
    }

    public final void setPrefix(String _prefix) {
        prefix = _prefix;
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
        return toContextEl().getClassBodies();
    }
    @Override
    public GeneType getClassBody(String _type) {
        return toContextEl().getClassBody(_type);
    }
    @Override
    public final BeanLgNames getStandards() {
        return standards;
    }

    public final void setStandards(BeanLgNames _standards) {
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
        return getLastPage().getGlobalClass();
    }

    @Override
    public void setGlobalClass(String _globalClass) {
        getLastPage().setGlobalClass(_globalClass);
    }

    @Override
    public Classes getClasses() {
        return toContextEl().getClasses();
    }

    @Override
    public String getCurrentFileName() {
        return getLastPage().getReadUrl();
    }

    @Override
    public RowCol getCurrentLocation() {
        return getLastPage().getRowCol();
    }

    @Override
    public LoopVariable getVar(String _var) {
        return getLastPage().getVars().getVal(_var);
    }

    public StringMap<LoopVariable> getVars() {
        return getLastPage().getVars();
    }

    @Override
    public LocalVariable getLocalVar(String _key) {
        return getLastPage().getLocalVar(_key);
    }

    @Override
    public boolean containsLocalVar(String _key) {
        return getLastPage().containsLocalVar(_key);
    }

    @Override
    public void putLocalVar(String _key, LocalVariable _loc) {
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
        return getLastPage().getCatchVars().getVal(_key);
    }

    @Override
    public StringMap<LocalVariable> getParameters() {
        return getLastPage().getParameters();
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

    @Override
    public boolean isStaticContext() {
        return staticContext;
    }

    @Override
    public void setStaticContext(boolean _staticContext) {
        staticContext = _staticContext;
    }

    @Override
    public boolean isAmbigous() {
        return ambigous;
    }

    @Override
    public void setAmbigous(boolean _ambigous) {
        ambigous = _ambigous;
    }

    @Override
    public ClassMetaInfo getClassMetaInfo(String _name) {
        return toContextEl().getClassMetaInfo(_name);
    }

    @Override
    public CustList<GeneMethod> getMethodBodiesById(String _genericClassName,
            MethodId _id) {
        return toContextEl().getMethodBodiesById(_genericClassName, _id);
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
    public void setCurrentChildTypeIndex(int _index) {
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
    public boolean isGearConst() {
        return context.isGearConst();
    }

    public void setGearConst(boolean _gearConst) {
        context.setGearConst(_gearConst);
    }

    @Override
    public CustList<OperationNode> getTextualSortedOperations() {
        return context.getTextualSortedOperations();
    }

    @Override
    public CustList<StringMap<LocalVariable>> getLocalVariables() {
        return context.getLocalVariables();
    }

    @Override
    public LocalVariable getLocalVar(String _key, int _index) {
        return context.getLocalVar(_key, _index);
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
        return false;
    }

    @Override
    public boolean isInternGlobal() {
        return getLastPage().isInternGlobal();
    }

    public void setInternGlobal(Struct _internGlobal) {
        getLastPage().setInternGlobal(_internGlobal);
    }

    @Override
    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    @Override
    public String getInternGlobalClass() {
        return getLastPage().getInternGlobal().getClassName(this);
    }

    @Override
    public String resolveIdType(String _in) {
        return resolveDynamicType(_in, null);
    }
    @Override
    public String resolveAccessibleIdType(String _in) {
        return resolveDynamicType(_in, null);
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
        return PartTypeUtil.processExec(_in, context);
    }

    @Override
    public int getGlobalOffset() {
        return context.getGlobalOffset();
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
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName("");
            un_.setRc(new RowCol());
            un_.setType(_in);
            context.getClasses().addError(un_);
            res_ = standards.getAliasObject();
        }
        return res_;
    }

    @Override
    public AnalyzedPageEl getAnalyzing() {
        return context.getAnalyzing();
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
    public boolean isDirectImport() {
        return context.isDirectImport();
    }

    @Override
    public void setDirectImport(boolean _directImport) {
        context.setDirectImport(_directImport);
    }

    @Override
    public String lookupImportType(String _type, AccessingImportingBlock _rooted) {
        return ContextEl.removeDottedSpaces(_type);
    }

    @Override
    public TypeOwnersDepends lookupImportMemberTypeDeps(String _type,
            AccessingImportingBlock _rooted) {
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
    public LoopVariable getMutableLoopVar(String _key, int _index) {
        return context.getMutableLoopVar(_key, _index);
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
    public SortedClassField getCurrentInitizedField() {
        return context.getCurrentInitizedField();
    }

    @Override
    public void setCurrentInitizedField(SortedClassField _currentInitizedField) {
        context.setCurrentInitizedField(_currentInitizedField);
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
}
