package code.formathtml;

import code.expressionlanguage.analyze.variables.AnaLocalVariable;

public final class PairVar {
    private final String name;
    private final AnaLocalVariable local;

    public PairVar(String name, AnaLocalVariable local) {
        this.name = name;
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public AnaLocalVariable getLocal() {
        return local;
    }
}
