package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;

public final class DbgParentStruct extends DbgAbsNodeStruct {
    private final Struct eval;
    public DbgParentStruct(ResultContext _r, Struct _w) {
        super(_r);
        this.eval = _w;
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return "|";
    }

}
