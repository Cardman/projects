package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
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
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();
    private final StringMap<LocalVariable> localVariables = new StringMap<LocalVariable>();
    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private Argument argument = Argument.createVoid();
    private String argumentClass = "";
    private RendStackCall rendStackCall;

    public AnalyzedTestConfiguration(Configuration _configuration, AnalyzedTestContextRender _analyzing, Forwards _forwards, BeanCustLgNames _standards) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        analyzingDoc.setContent(adv);
        dual = _analyzing.getDual();
        opt = _analyzing.getOpt();
        this.analyzing = _analyzing.getAnalyzing();
    }

    public Options getOpt() {
        return opt;
    }

    public StringMap<LocalVariable> getLocalVariables() {
        return localVariables;
    }

    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public ContextEl generate(){
        return forwards.generate(opt);
    }
    public Forwards getForwards() {
        return forwards;
    }

    public AnalyzingDoc getAnalyzingDoc() {
        return analyzingDoc;
    }

    public String getAliasPrimLong() {
        return analyzing.getAliasPrimLong();
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

    public String getAliasByte() {
        return analyzing.getAliasByte();
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

    public void setAnalyzed(StringMap<AnaRendDocumentBlock> _analyzed) {
        this.analyzed = _analyzed;
    }

    public DualConfigurationContext getDual() {
        return dual;
    }

    public RendStackCall build(InitPhase _readOnlyOthers, ContextEl _ctx) {
        rendStackCall = new RendStackCall(_readOnlyOthers,_ctx);
        return rendStackCall;
    }

    public RendStackCall getRendStackCall() {
        return rendStackCall;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        this.argument = _argument;
    }

    public String getArgumentClass() {
        return argumentClass;
    }

    public void setArgumentClass(String _argumentClass) {
        this.argumentClass = _argumentClass;
    }
}
