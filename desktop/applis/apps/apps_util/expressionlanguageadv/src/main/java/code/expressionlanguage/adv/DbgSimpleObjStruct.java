package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public abstract class DbgSimpleObjStruct extends DbgAbsNodeStruct {
    private final Struct eval;
    protected DbgSimpleObjStruct(ContextEl _r, DbgAbsNodeStruct _par, Struct _w) {
        super(_r, _par.getOriginal(),_par);
        this.eval = _w;
    }
    protected DbgSimpleObjStruct(DbgAbsNodeStruct _par, Struct _w) {
        super(_par);
        this.eval = _w;
    }

    @Override
    public Struct value() {
        return eval;
    }

}
