package code.formathtml;
import org.w3c.dom.Document;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.AccessValue;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.ints.MathFactory;

@RwXml
public class Configuration {

    private static final String RETURN_LINE = "\n";

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;

//    private static final String UNPREFIXED_END = "</c_";

//    private static final String UNPREFIXED_BEGIN = "<c_";

//    private static final String PREFIXED_END = "</c:";

//    private static final String PREFIXED_BEGIN = "<c:";

    private String firstUrl = EMPTY_STRING;

    private StringMap<Validator> validators = new StringMap<Validator>();

    private StringMap<Translator> translators = new StringMap<Translator>();

    private StringMap<Bean> beans = new StringMap<Bean>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = EMPTY_STRING;

    private MathFactory<?> mathFactory;

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private String namespaceUri;

    private AccessValue accessValue = new HtmlAccessValue();

    private String filesConfName;

    private ContextEl context;

    private StringMap<String> lateValidators = new StringMap<String>();
    private StringMap<String> lateTranslators = new StringMap<String>();

    private transient StringMap<Struct> builtBeans = new StringMap<Struct>();
    private transient StringMap<Struct> builtValidators = new StringMap<Struct>();
    private transient StringMap<Struct> builtTranslators = new StringMap<Struct>();

    private transient Classes classes;

    private transient HtmlPage htmlPage = new HtmlPage();

    private transient Document document;

    private final transient CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private transient String currentUrl;

    private transient String html;

    private transient String resourceUrl;

    private transient String prefix = EMPTY_STRING;

    public Configuration() {
        if (namespaceUri == null) {
            namespaceUri = EMPTY_STRING;
        }
        if (namespaceUri.isEmpty()) {
            namespaceUri = FormatHtml.NAMESPACE_URI;
        }
    }

    public final void init() {
        builtBeans = new StringMap<Struct>();
        builtValidators = new StringMap<Struct>();
        builtTranslators = new StringMap<Struct>();
        htmlPage = new HtmlPage();
        document = null;
//        importing = new List<ImportingPage>();
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
    }

    public final ContextEl toContextEl() {
        if (context != null) {
            context.setCurrentUrl(currentUrl);
            context.setDocument(document);
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
        context_.setDocument(document);
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

    public final MathFactory<?> getMathFactory() {
        return mathFactory;
    }

    public final void setMathFactory(MathFactory<?> _mathFactory) {
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

//    public final void setImporting(List<ImportingPage> _importing) {
//        importing = _importing;
//    }

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
//        Map<String,String> map_ = new Map<String,String>();
//        map_.put(PREFIXED_BEGIN, UNPREFIXED_BEGIN);
//        map_.put(PREFIXED_END, UNPREFIXED_END);
//        String res_ = StringList.replace(_html, map_);
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
}
