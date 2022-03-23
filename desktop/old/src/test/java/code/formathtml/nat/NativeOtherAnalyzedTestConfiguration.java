package code.formathtml.nat;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.DualConfigurationContext;
import code.util.StringMap;

class NativeOtherAnalyzedTestConfiguration {
    private final Configuration configuration;
    private final NatAnalyzedCode analyzing;
    private final Forwards forwards;
    private final BeanTestNatLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private final DualConfigurationContext dual;
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();

    NativeOtherAnalyzedTestConfiguration(Configuration _configuration, NativeOtherAnalyzedTestContext _analyzing, Forwards _forwards, BeanTestNatLgNames _standards) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        dual = _analyzing.getDual();
        analyzingDoc.setContent(adv);
        this.analyzing = _analyzing.getAnalyzing();
    }

    Configuration getConfiguration() {
        return configuration;
    }

    NatAnalyzedCode getAnalyzing() {
        return analyzing;
    }

    Forwards getForwards() {
        return forwards;
    }

    AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
    }

    void setNavigation(StringMap<StringMap<String>> _stringMapStringMap) {
        configuration.setNavigation(_stringMapStringMap);
    }

    StringMap<StringMap<String>> getNavigation() {
        return configuration.getNavigation();
    }

    BeanTestNatLgNames getAdv() {
        return adv;
    }

    StringMap<AnaRendDocumentBlock> getAnalyzed() {
        return analyzed;
    }

    void setAnalyzed(StringMap<AnaRendDocumentBlock> _analyzed) {
        this.analyzed = _analyzed;
    }

    DualConfigurationContext getDual() {
        return dual;
    }

    public RendStackCall build(InitPhase _readOnlyOthers, ContextEl _ctx) {
        return new RendStackCall(_readOnlyOthers, _ctx);
    }
}
