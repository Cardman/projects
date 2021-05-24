package code.formathtml.nat;

import code.bean.nat.NativeConverterCheck;
import code.bean.nat.NativeReducingOperations;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.DualConfigurationContext;
import code.util.StringMap;

class NativeOtherAnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanTestNatLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private final ContextEl context;
    private final DualConfigurationContext dual;
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();

    NativeOtherAnalyzedTestConfiguration(Configuration _configuration, NativeOtherAnalyzedTestContext _analyzing, Forwards _forwards, BeanTestNatLgNames _standards) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        dual = _analyzing.getDual();
        analyzingDoc.setContent(adv);
        analyzingDoc.setInputBuilder(new NatInputBuilder());
        analyzingDoc.setReducingOperations(new NativeReducingOperations());
        analyzingDoc.setConverterCheck(new NativeConverterCheck(_standards.getAliasObject()));
        this.analyzing = _analyzing.getAnalyzing();
        context = _analyzing.getContext();
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
        return context;
    }

    boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
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
