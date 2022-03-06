package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.util.StringMap;

public final class AnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanCustLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private ContextEl context;
    private final DualConfigurationContext dual;
    private final Options opt;

    public AnalyzedTestConfiguration(Configuration _configuration, Forwards _forwards, BeanCustLgNames _standards, DualConfigurationContext _dual, Options _opt, AnalyzedPageEl _page) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        analyzingDoc.setContent(adv);
        dual = _dual;
        opt = _opt;
        this.analyzing = _page;
    }

    public Options getOpt() {
        return opt;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public ContextEl generate(){
        return forwards.generate();
    }
    public Forwards getForwards() {
        return forwards;
    }

    public AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
    }

    public LgNames getStandards() {
        return adv;
    }

    public ContextEl getContext() {
        return context;
    }

    public void setContext(ContextEl _context) {
        this.context = _context;
    }

    public boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
    }

    public Classes getClasses() {
        return forwards.getClasses();
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

    public DualConfigurationContext getDual() {
        return dual;
    }

}
