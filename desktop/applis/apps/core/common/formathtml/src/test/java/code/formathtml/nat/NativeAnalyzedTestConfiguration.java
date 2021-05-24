package code.formathtml.nat;

import code.bean.nat.BeanNatLgNames;
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
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.util.DualConfigurationContext;
import code.util.StringMap;

class NativeAnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanNatLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private final ContextEl context;
    private final DualConfigurationContext dual;
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();
    private RendStackCall stackCall;

    NativeAnalyzedTestConfiguration(Configuration _configuration, NativeAnalyzedTestContext _analyzing, Forwards _forwards, BeanNatLgNames _standards) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        dual = _analyzing.getDual();
        analyzingDoc.setReducingOperations(new NativeReducingOperations());
        analyzingDoc.setContent(adv);
        analyzingDoc.setInputBuilder(new DefaultInputBuilder());
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

    BeanNatLgNames getAdv() {
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

    public RendStackCall getStackCall() {
        return stackCall;
    }

    public RendStackCall build(InitPhase _readOnlyOthers, ContextEl _ctx) {
        RendStackCall rendStackCall_ = new RendStackCall(_readOnlyOthers, _ctx);
        stackCall = rendStackCall_;
        return rendStackCall_;
    }
}
