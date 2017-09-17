package code.formathtml;
import org.w3c.dom.Document;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.AccessValue;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.Struct;
import code.resources.ResourceFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.ints.MathFactory;

@RwXml
public class Configuration {
    private static final String INSTANCE = "^new.";

    private static final char BEGIN_ARGS = '(';
    private static final char END_ARGS = ')';

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

    private String namespaceUri;

    private AccessValue accessValue = new HtmlAccessValue();

    private String filesConfName;

    private ContextEl context;

    private StringMap<String> lateValidators = new StringMap<String>();
    private StringMap<String> lateTranslators = new StringMap<String>();

    private final transient StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final transient StringMap<Struct> builtValidators = new StringMap<Struct>();
    private final transient StringMap<Struct> builtTranslators = new StringMap<Struct>();

    private transient HtmlPage htmlPage = new HtmlPage();

    private transient Document document;

    private final transient CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private transient String currentUrl;

    private transient String html;

    private transient String resourceUrl;

    private transient String prefix = EMPTY_STRING;

    private transient volatile boolean interrupt;

    public Configuration() {
        if (namespaceUri == null) {
            namespaceUri = EMPTY_STRING;
        }
        if (namespaceUri.isEmpty()) {
            namespaceUri = FormatHtml.NAMESPACE_URI;
        }
    }

    public final void init() {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        prefix = EMPTY_STRING;
        if (namespaceUri == null) {
            namespaceUri = EMPTY_STRING;
        }
        if (namespaceUri.isEmpty()) {
            namespaceUri = FormatHtml.NAMESPACE_URI;
        }
        if (accessValue == null) {
            accessValue = new HtmlAccessValue();
        }
        if (lateValidators == null) {
            lateValidators = new StringMap<String>();
        }
        if (lateTranslators == null) {
            lateTranslators = new StringMap<String>();
        }
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
                if (e.getKey().equalsIgnoreCase(f)) {
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
        for (EntryCust<String, String> e: getLateValidators().entryList()) {
            Struct str_ = ElUtil.processEl(INSTANCE+e.getValue()+BEGIN_ARGS+END_ARGS, 0, context).getStruct();
            getBuiltValidators().put(e.getKey(), str_);
        }
        for (EntryCust<String, String> e: getLateTranslators().entryList()) {
            Struct str_ = ElUtil.processEl(INSTANCE+e.getValue()+BEGIN_ARGS+END_ARGS, 0, context).getStruct();
            getBuiltTranslators().put(e.getKey(), str_);
        }
        setupValiatorsTranslators();
    }
    void setupValiatorsTranslators(String _language) {
        for (EntryCust<String, Bean> e: getBeans().entryList()) {
            Struct str_ = newBean(_language, null, e.getValue(), false);
            getBuiltBeans().put(e.getKey(), str_);
        }
        setupValiatorsTranslators();
    }

    void setupValiatorsTranslators() {
        for (EntryCust<String, Validator> e: getValidators().entryList()) {
            Struct str_ = new Struct(e.getValue());
            getBuiltValidators().put(e.getKey(), str_);
        }
        for (EntryCust<String, Translator> e: getTranslators().entryList()) {
            Struct str_ = new Struct(e.getValue());
            getBuiltTranslators().put(e.getKey(), str_);
        }
    }

    Struct newBean(String _language, Object _dataBase, Bean _bean, boolean _set) {
        if (!_set) {
            return new Struct(_bean);
        }
        addPage(new ImportingPage(false));
        Struct strBean_ = ElUtil.processEl(INSTANCE+_bean.getClassName()+BEGIN_ARGS+END_ARGS, 0, toContextEl()).getStruct();
        if (_dataBase != null) {
            ExtractObject.setDataBase(this, strBean_, Struct.wrapOrId(_dataBase));
        } else {
            ExtractObject.setDataBase(this, strBean_, new Struct());
        }
        if (_bean == null || _bean.getForms() == null) {
            ExtractObject.setForms(this, strBean_, new Struct(new StringMap<Object>()));
        } else {
            ExtractObject.setForms(this, strBean_, new Struct(_bean.getForms()));
        }
        ExtractObject.setLanguage(this, strBean_, _language);
        if (_bean.getScope() != null) {
            ExtractObject.setScope(this, strBean_, _bean.getScope());
        } else {
            ExtractObject.setScope(this, strBean_, EMPTY_STRING);
        }
        removeLastPage();
        return strBean_;
    }

    Struct newBean(String _language, Struct _bean) {
        addPage(new ImportingPage(false));
        Struct strBean_ = ElUtil.processEl(INSTANCE+_bean.getClassName()+BEGIN_ARGS+END_ARGS, 0, toContextEl()).getStruct();
        ExtractObject.setDataBase(this, strBean_, ExtractObject.getDataBase(this, _bean));
        ExtractObject.setForms(this, strBean_, ExtractObject.getForms(this, _bean));
        ExtractObject.setLanguage(this, strBean_, _language);
        ExtractObject.setScope(this, strBean_, ExtractObject.getScope(this, _bean));
        removeLastPage();
        return strBean_;
    }

    public final ContextEl toContextEl() {
        if (context != null) {
            context.setCurrentUrl(currentUrl);
            context.setHtml(html);
            context.setResourceUrl(resourceUrl);
            context.clearPages();
            for (ImportingPage i: importing) {
                context.addPage(i.getPageEl());
            }
            return context;
        }
        ContextEl context_ = new ContextEl();
        context_.setAccessValue(accessValue);
        context_.setCurrentUrl(currentUrl);
        context_.setHtml(html);
        context_.setMathFactory(mathFactory);
        context_.setResourceUrl(resourceUrl);
        context_.setTabWidth(tabWidth);
        for (ImportingPage i: importing) {
            context_.addPage(i.getPageEl());
        }
        return context_;
    }

    public AccessValue getAccessValue() {
        return accessValue;
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
        prefix = document.lookupPrefix(namespaceUri);
        if (prefix == null) {
            prefix = EMPTY_STRING;
        } else {
            prefix += SEP;
        }
    }

    public final String joinPages() {
        return importing.join(RETURN_LINE);
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

    public final String getNamespaceUri() {
        return namespaceUri;
    }

    public void setNamespaceUri(String _namespaceUri) {
        namespaceUri = _namespaceUri;
    }

    public boolean isInterrupt() {
        return interrupt;
    }

    public void setInterrupt(boolean _interrupt) {
        interrupt = _interrupt;
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
}
