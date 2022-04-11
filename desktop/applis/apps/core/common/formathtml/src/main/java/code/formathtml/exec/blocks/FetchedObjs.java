package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;

public abstract class FetchedObjs {
    private final Argument arg;
    protected FetchedObjs(Argument _arg) {
        this.arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }


}
