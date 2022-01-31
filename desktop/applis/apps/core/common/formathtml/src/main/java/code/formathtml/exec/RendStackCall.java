package code.formathtml.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.formathtml.FormParts;
import code.formathtml.HtmlPage;
import code.formathtml.ImportingPage;
import code.formathtml.SimplePageEl;
import code.maths.montecarlo.CustomSeedGene;
import code.sml.Document;
import code.util.CustList;

public final class RendStackCall {

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private final FormParts formParts = new FormParts();

    private Struct mainBean;

    private final StackCall stackCall;

    public RendStackCall(InitPhase _readOnlyOthers, ContextEl _ctx) {
        this(_readOnlyOthers,_ctx,new CustomSeedGene());
    }

    public RendStackCall(InitPhase _readOnlyOthers, ContextEl _ctx, CustomSeedGene _cust) {
        stackCall = StackCall.newInstance(_readOnlyOthers, _ctx,_cust);
    }

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

    public StackCall getStackCall() {
        return stackCall;
    }
}
