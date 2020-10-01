package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.util.StringMap;

class NativeAnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanNatLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();

    NativeAnalyzedTestConfiguration(Configuration configuration, NativeAnalyzedTestContext analyzing, Forwards _forwards, BeanNatLgNames _standards) {
        this.configuration = configuration;
        forwards = _forwards;
        adv= _standards;
        analyzingDoc.setContent(adv);
        this.configuration.setContext(analyzing.getContext());
        this.analyzing = analyzing.getAnalyzing();
    }

    Configuration getConfiguration() {
        return configuration;
    }

    AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    Forwards getForwards() {
        return forwards;
    }

    AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
    }

    ContextEl getContext() {
        return configuration.getContext();
    }

    boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
    }

    void setNavigation(StringMap<StringMap<String>> stringMapStringMap) {
        configuration.setNavigation(stringMapStringMap);
    }

    StringMap<StringMap<String>> getNavigation() {
        return configuration.getNavigation();
    }

    BeanNatLgNames getAdv() {
        return adv;
    }

    StringMap<AnaRendDocumentBlock> getAnalyzed() {
        return analyzed;
    }

    void setAnalyzed(StringMap<AnaRendDocumentBlock> analyzed) {
        this.analyzed = analyzed;
    }
}
