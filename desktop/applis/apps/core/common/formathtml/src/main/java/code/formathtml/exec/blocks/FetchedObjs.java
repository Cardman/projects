package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FetchedObjs {
    private final CustList<Struct> obj;
    private final Argument arg;
    protected FetchedObjs(CustList<Struct> _obj, Argument _arg) {
        this.obj = _obj;
        this.arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }


    public CustList<Struct> getObj() {
        return obj;
    }


}
