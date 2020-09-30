package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;

public final class AnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();

    public AnalyzedTestConfiguration(Configuration configuration, AnalyzedTestContext analyzing, Forwards _forwards, BeanLgNames _standards) {
        this.configuration = configuration;
        forwards = _forwards;
        adv= _standards;
        this.configuration.setContext(analyzing.getContext());
        this.analyzing = analyzing.getAnalyzing();
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

    public LgNames getAnaStandards() {
        return adv;
    }

    public String getAliasLong() {
        return analyzing.getAliasLong();
    }

    public String getAliasPrimLong() {
        return analyzing.getAliasPrimLong();
    }

    public String getAliasVoid() {
        return analyzing.getAliasVoid();
    }

    public String getAliasBoolean() {
        return analyzing.getAliasBoolean();
    }

    public String getAliasPrimBoolean() {
        return analyzing.getAliasPrimBoolean();
    }

    public String getAliasObject() {
        return analyzing.getAliasObject();
    }

    public String getAliasNumber() {
        return analyzing.getAliasNumber();
    }

    public String getAliasString() {
        return analyzing.getAliasString();
    }

    public String getAliasInteger() {
        return analyzing.getAliasInteger();
    }

    public String getAliasPrimInteger() {
        return analyzing.getAliasPrimInteger();
    }

    public LgNames getStandards() {
        return configuration.getContext().getStandards();
    }

    public ContextEl getContext() {
        return configuration.getContext();
    }

    public ImportingPage getLastPage() {
        return configuration.getLastPage();
    }

    public boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
    }

    public Classes getClasses() {
        return configuration.getClasses();
    }

    public boolean hasPages() {
        return configuration.hasPages();
    }

    public String getAliasByte() {
        return analyzing.getAliasByte();
    }
    public void setNavigation(StringMap<StringMap<String>> stringMapStringMap) {
        configuration.setNavigation(stringMapStringMap);
    }

    public StringMap<StringMap<String>> getNavigation() {
        return configuration.getNavigation();
    }

    public BeanLgNames getAdvStandards() {
        return adv;
    }

    public StringMap<AnaRendDocumentBlock> getAnalyzed() {
        return analyzed;
    }

    public void setAnalyzed(StringMap<AnaRendDocumentBlock> analyzed) {
        this.analyzed = analyzed;
    }

}
