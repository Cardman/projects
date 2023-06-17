package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;

public final class DbgArrEltStruct extends DbgAbsNodeStruct {
    private final int variable;
    private final Struct eval;
    public DbgArrEltStruct(ResultContext _r,int _v,  Struct _w) {
        super(_r);
        this.variable = _v;
        this.eval = _w;
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return "["+ variable +"]";
    }

}