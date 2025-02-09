package code.bean.nat.exec;

import code.bean.nat.*;
import code.sml.Document;
import code.util.CustList;

public class NatRendStackCall {

    private final CustList<NatImportingPageAbs> importing = new CustList<NatImportingPageAbs>();

    private Document document;

    private String beanName="";

    private NaSt mainBean;

    private BeanNatCommonLgNames current;
    public void init() {
        document = null;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }

    public NaSt getMainBean() {
        return mainBean;
    }

    public void setMainBean(NaSt _mainBean) {
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

    public NaSt getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    public BeanNatCommonLgNames getCurrent() {
        return current;
    }

    public void setCurrent(BeanNatCommonLgNames _c) {
        this.current = _c;
    }
}
