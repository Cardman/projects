package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.DefaultConverterCheck;
import code.formathtml.analyze.DefaultReducingOperations;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.util.StringMap;

public final class AnalyzedTestConfiguration {
    private final Configuration configuration;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private final BeanCustLgNames adv;
    private final AnalyzingDoc analyzingDoc = new AnalyzingDoc();
    private final ContextEl context;
    private final DualConfigurationContext dual;
    private StringMap<AnaRendDocumentBlock> analyzed = new StringMap<AnaRendDocumentBlock>();
    private final StringMap<LocalVariable> localVariables = new StringMap<LocalVariable>();
    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private Argument argument = Argument.createVoid();
    private RendStackCall rendStackCall;

    public AnalyzedTestConfiguration(Configuration _configuration, AnalyzedTestContext _analyzing, Forwards _forwards, BeanCustLgNames _standards) {
        this.configuration = _configuration;
        forwards = _forwards;
        adv= _standards;
        analyzingDoc.setReducingOperations(new DefaultReducingOperations());
        analyzingDoc.setContent(adv);
        analyzingDoc.setInputBuilder(new DefaultInputBuilder());
        analyzingDoc.setConverterCheck(new DefaultConverterCheck(adv.getContent().getPrimTypes().getPrimitiveTypes(), adv.getContent().getCharSeq().getAliasString()));
        context = _analyzing.getContext();
        dual = _analyzing.getDual();
        dual.setContext(context);
        this.analyzing = _analyzing.getAnalyzing();
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
        return context.getStandards();
    }

    public ContextEl getContext() {
        return context;
    }

    public boolean isEmptyErrors() {
        return analyzing.isEmptyErrors();
    }

    public Classes getClasses() {
        return context.getClasses();
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

}
