package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.DisplayedStrings;
import code.expressionlanguage.stds.LgNames;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;
    private StackCall stackCall;

    public AnalyzedTestContext(ContextEl _context, AnalyzedPageEl _analyzing, Forwards _forwards) {
        this.context = _context;
        this.analyzing = _analyzing;
        forwards = _forwards;
    }

    public ContextEl getContext() {
        return context;
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
        return context.getInit();
    }

    public Classes getClasses() {
        return context.getClasses();
    }

    public LgNames getStandards() {
        return context.getStandards();
    }
}
