package code.formathtml.exec.blocks;

import code.expressionlanguage.structs.Struct;

public abstract class FetchedObjs {
    private final Struct arg;
    protected FetchedObjs(Struct _arg) {
        this.arg = _arg;
    }

    public Struct getArg() {
        return arg;
    }


}
