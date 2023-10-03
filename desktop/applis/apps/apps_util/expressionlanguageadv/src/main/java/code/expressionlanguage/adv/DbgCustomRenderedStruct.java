package code.expressionlanguage.adv;

import code.expressionlanguage.structs.Struct;

public final class DbgCustomRenderedStruct extends DbgAbsNodeStruct {
    private final String prefix;
    private final Struct eval;
    public DbgCustomRenderedStruct(DbgAbsNodeStruct _par, String _p, Struct _w) {
        super(_par);
        this.prefix = _p;
        this.eval = _w;
        repr(value());
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return prefix;
    }

}
