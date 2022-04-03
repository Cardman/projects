package code.bean.nat.exec;

import code.bean.nat.NatHtmlPage;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.AbsRendStackCall;
import code.util.CustList;

public final class NatRendStackCall extends AbsRendStackCall {
    private final NatHtmlPage htmlPage = new NatHtmlPage();

    private final CustList<NatImportingPage> importing = new CustList<NatImportingPage>();
    private final NatFormParts natFormParts;

    public NatRendStackCall() {
        super();
        natFormParts = new NatFormParts();
    }

    public NatHtmlPage getHtmlPage() {
        return htmlPage;
    }

    public NatFormParts getFormParts() {
        return natFormParts;
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

}
