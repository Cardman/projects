package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.BlocksFlags;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;

public final class AnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;

    public AnalyzedTestConfiguration(Configuration configuration, AnalyzedPageEl analyzing) {
        this.configuration = configuration;
        this.analyzing = analyzing;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public LgNames getStandards() {
        return configuration.getStandards();
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

    public void setNavigation(StringMap<StringMap<String>> stringMapStringMap) {
        configuration.setNavigation(stringMapStringMap);
    }

    public StringMap<StringMap<String>> getNavigation() {
        return configuration.getNavigation();
    }

    public BeanLgNames getAdvStandards() {
        return configuration.getAdvStandards();
    }
}
