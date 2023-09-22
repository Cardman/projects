package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;

public final class DbgWatchStruct extends DbgSimpleObjStruct {
    public DbgWatchStruct(ContextEl _r, DbgAbsNodeStruct _par, Struct _w) {
        super(_r,_par, _w);
    }

    @Override
    public String str() {
        return ":";
    }

}
