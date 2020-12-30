package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.FormParts;
import code.formathtml.HtmlPage;
import code.formathtml.ImportingPage;
import code.formathtml.SimplePageEl;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.util.CustList;
import code.util.core.StringUtil;

public final class RendStackCall {

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl = "";
    private final FormParts formParts = new FormParts();

    private Struct mainBean;

    public void init(String _firstUrl) {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = _firstUrl;
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
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
        importing.removeQuicklyLast();
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

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }
    public void setOpOffset(int _offset) {
        getLastPage().setOpOffset(_offset);
    }

    public SimplePageEl getPageEl() {
        return importing.last().getPageEl();
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    public boolean hasPages() {
        return !importing.isEmpty();
    }

    public FormParts getFormParts() {
        return formParts;
    }

    public Struct getMainBean() {
        return mainBean;
    }

    public void setMainBean(Struct _mainBean) {
        mainBean = _mainBean;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

}
