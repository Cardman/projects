package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;
import code.sml.Document;

public abstract class AbsRendStackCall {

    private Document document;

    private String beanName="";

    private Struct mainBean;

    protected AbsRendStackCall() {
    }

    public void init() {
        document = null;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
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
