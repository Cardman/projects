package code.expressionlanguage;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.DisplayedStrings;

public final class AnalyzedTestContext {
    private final ContextEl context;
    private final AnalyzedPageEl analyzing;
    private final Forwards forwards;

    public AnalyzedTestContext(ContextEl context, AnalyzedPageEl analyzing, Forwards _forwards) {
        this.context = context;
        this.analyzing = analyzing;
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
}
