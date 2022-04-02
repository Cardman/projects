package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.FormParts;
import code.formathtml.HtmlPage;
import code.formathtml.util.NodeContainer;
import code.sml.Document;

public abstract class AbsRendStackCall {
    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;

    private final FormParts formParts = new FormParts();

    private Struct mainBean;

    public abstract NodeContainer create();

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
