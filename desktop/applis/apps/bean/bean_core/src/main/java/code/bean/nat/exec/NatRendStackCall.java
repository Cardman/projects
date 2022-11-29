package code.bean.nat.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.AbsRendStackCall;
import code.util.CustList;

public class NatRendStackCall extends AbsRendStackCall {

    private final CustList<NatImportingPageAbs> importing = new CustList<NatImportingPageAbs>();

    public NatRendStackCall() {
        super();
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
