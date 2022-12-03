package code.bean.nat.exec;

import code.expressionlanguage.structs.Struct;
import code.sml.Document;
import code.util.CustList;

public class NatRendStackCall {

    private final CustList<NatImportingPageAbs> importing = new CustList<NatImportingPageAbs>();

    private Document document;

    private String beanName="";

    private Struct mainBean;

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
    public void clearPages() {
        importing.clear();
    }
    public void addPage(NatImportingPageAbs _page) {
        importing.add(_page);
    }
    public NatImportingPageAbs getLastPage() {
        return importing.last();
    }
    public void removeLastPage() {
        importing.removeQuicklyLast();
    }
    public CustList<NatImportingPageAbs> getImporting() {
        return importing;
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

}
