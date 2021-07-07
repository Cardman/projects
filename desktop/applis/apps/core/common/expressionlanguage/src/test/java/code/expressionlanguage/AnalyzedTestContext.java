package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;

public final class AnalyzedTestContext {
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private StackCall stackCall;
    private Options opt;

    public AnalyzedTestContext(Options _opt, AnalyzedPageEl _analyzing, Forwards _forwards) {
        this.analyzing = _analyzing;
        forwards = _forwards;
        opt = _opt;
    }

    public Options getOpt() {
        return opt;
    }

    public ContextEl getContext() {
        return forwards.getContext();
    }

    public AnalyzedPageEl getAnalyzing() {
        return analyzing;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public DisplayedStrings getDisplayedStrings() {
        return analyzing.getDisplayedStrings();
    }
    public String getAliasVoid() {
        return analyzing.getAliasVoid();
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

    public StackCall getStackCall() {
        return stackCall;
    }

    public void setStackCall(StackCall _stackCall) {
        stackCall = _stackCall;
    }

    public Initializer getInit() {
        return forwards.getContext().getInit();
    }

    public Classes getClasses() {
        return forwards.getContext().getClasses();
    }

    public LgNames getStandards() {
        return forwards.getContext().getStandards();
    }
}
