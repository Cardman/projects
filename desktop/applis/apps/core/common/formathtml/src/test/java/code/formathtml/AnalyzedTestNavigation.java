package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.util.BeanCustLgNames;
import code.util.StringMap;

public final class AnalyzedTestNavigation {
    private final Navigation nav;
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanCustLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private final ContextEl context;
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();
    private AnalyzedTestConfiguration gl;

    public AnalyzedTestNavigation(Navigation _nav, AnalyzedTestConfiguration _analyzing) {
        nav = _nav;
        gl = _analyzing;
        this.configuration = _analyzing.getConfiguration();
        forwards = _analyzing.getForwards();
        adv= _analyzing.getAdvStandards();
        analyzingDoc.setContent(adv);
        analyzingDoc.setInputBuilder(new DefaultInputBuilder());
        context = _analyzing.getContext();
        this.analyzing = _analyzing.getAnalyzing();
    }

    public Navigation getNav() {
        return nav;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
    }

    public LgNames getStandards() {
        return context.getStandards();
    }

    public ContextEl getContext() {
        return context;
    }

    public ImportingPage getLastPage() {
        return configuration.getLastPage();
    }

    public boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
    }

    public Classes getClasses() {
        return context.getClasses();
    }

    public boolean hasPages() {
        return configuration.hasPages();
    }

    public void setNavigation(StringMap<StringMap<String>> _stringMapStringMap) {
        configuration.setNavigation(_stringMapStringMap);
    }

    public StringMap<StringMap<String>> getNavigation() {
        return configuration.getNavigation();
    }

    public BeanCustLgNames getAdvStandards() {
        return adv;
    }

    public StringMap<AnaRendDocumentBlock> getAnalyzed() {
        return analyzed;
    }

    public AnalyzedTestConfiguration getGl() {
        return gl;
    }
}
