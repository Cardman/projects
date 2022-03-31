package code.formathtml.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.CustomSeedGene;
import code.util.CustList;

public final class RendStackCall extends AbsRendStackCall implements AbstractStackCall {

    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private final StackCall stackCall;

    public RendStackCall(InitPhase _readOnlyOthers, ContextEl _ctx) {
        this(_readOnlyOthers,_ctx,new CustomSeedGene());
    }

    public RendStackCall(InitPhase _readOnlyOthers, ContextEl _ctx, CustomSeedGene _cust) {
        stackCall = StackCall.newInstance(_readOnlyOthers, _ctx,_cust);
    }

    public String formatVarType(String _varType) {
        return ExecInherits.quickFormat(getLastPage().getGlobalClass(),_varType);
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

    public StackCall getStackCall() {
        return stackCall;
    }
}
