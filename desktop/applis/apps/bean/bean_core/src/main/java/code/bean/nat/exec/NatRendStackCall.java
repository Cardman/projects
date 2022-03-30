package code.bean.nat.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.FormParts;
import code.formathtml.HtmlPage;
import code.sml.Document;
import code.util.CustList;

public final class NatRendStackCall {
    HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<NatImportingPage> importing = new CustList<NatImportingPage>();

    private final FormParts formParts = new FormParts();

    private Struct mainBean;

    public void init() {
        htmlPage = new HtmlPage();
        document = null;
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
    public void addPage(NatImportingPage _page) {
        importing.add(_page);
    }
    public NatImportingPage getLastPage() {
        return importing.last();
    }
    public void removeLastPage() {
        importing.removeQuicklyLast();
    }
    public CustList<NatImportingPage> getImporting() {
        return importing;
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
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
