package code.formathtml;
import java.util.concurrent.atomic.AtomicBoolean;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Options;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
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
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.MathFactory;

public class Configuration implements Analyzable {
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

    private final transient StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final transient StringMap<Struct> builtValidators = new StringMap<Struct>();
    private final transient StringMap<Struct> builtTranslators = new StringMap<Struct>();

    private transient HtmlPage htmlPage = new HtmlPage();

    private transient Document document;

    private final transient CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private transient String currentUrl;

    private transient String html;

    private transient String resourceUrl;

    private transient AtomicBoolean interrupt = new AtomicBoolean();
    private transient String currentVarSetting;
    @Override
    public boolean isMerged() {
        return context.isMerged();
    }

    @Override
    public void setMerged(boolean _merged) {
        context.setMerged(_merged);
    }

    @Override
    public boolean isEnabledDotted() {
        return context.isEnabledDotted();
    }

    @Override
    public void setEnabledDotted(boolean _enabled) {
        context.setEnabledDotted(_enabled);
    }

    public final void init() {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        if (context == null) {
            context = new ContextEl();
            context.setStandards(standards);
            context = toContextEl();
            context.initError();
        }
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
            ExtractObject.setDataBase(this, strBean_, new StdStruct(_dataBase, className_));
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
        context.clearPages();
        for (ImportingPage i: importing) {
            context.addPage(i.getPageEl());
        }
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
    public StringMap<LoopVariable> getVars() {
        return getLastPage().getVars();
    }

    @Override
    public StringMap<LocalVariable> getLocalVars() {
        return getLastPage().getLocalVars();
    }

    @Override
    public StringMap<LocalVariable> getCatchVars() {
        return getLastPage().getCatchVars();
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
    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return getLastPage().getPageEl().isStaticContext();
    }

    @Override
    public void setStaticContext(boolean _staticContext) {
        getLastPage().getPageEl().setStaticContext(_staticContext);
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
        return currentVarSetting;
    }

    public void setCurrentVarSetting(String _currentVarSetting) {
        currentVarSetting = _currentVarSetting;
    }

    @Override
    public boolean isAnalyzingRoot() {
        return context.isAnalyzingRoot();
    }

    @Override
    public boolean isRootAffect() {
        return context.isRootAffect();
    }

    @Override
    public void setAnalyzingRoot(boolean _b) {
        context.setAnalyzingRoot(_b);
    }

    @Override
    public void setRootAffect(boolean _b) {
        context.setRootAffect(_b);
    }

    @Override
    public Options getOptions() {
        return context.getOptions();
    }

}
