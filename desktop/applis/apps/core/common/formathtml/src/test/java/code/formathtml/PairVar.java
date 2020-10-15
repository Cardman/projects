package code.formathtml;

import code.expressionlanguage.analyze.variables.AnaLocalVariable;

public final class PairVar {
    private final String name;
    private final AnaLocalVariable local;

    public PairVar(String _name, AnaLocalVariable _local) {
        this.name = _name;
        this.local = _local;
    }

    public String getName() {
        return name;
    }

    public AnaLocalVariable getLocal() {
        return local;
    }
}
