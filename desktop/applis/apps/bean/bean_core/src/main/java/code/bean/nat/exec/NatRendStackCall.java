package code.bean.nat.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.AbsRendStackCall;
import code.formathtml.util.NodeContainer;
import code.util.CustList;

public final class NatRendStackCall extends AbsRendStackCall {

    private final CustList<NatImportingPage> importing = new CustList<NatImportingPage>();

    @Override
    public NodeContainer create() {
        return new NatNodeContainer();
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
