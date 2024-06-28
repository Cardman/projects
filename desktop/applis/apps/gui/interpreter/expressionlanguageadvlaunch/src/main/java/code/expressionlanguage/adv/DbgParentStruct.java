package code.expressionlanguage.adv;

import code.expressionlanguage.structs.Struct;

public final class DbgParentStruct extends DbgSimpleObjStruct {
    public DbgParentStruct(DbgAbsNodeStruct _par, Struct _w) {
        super(_par, _w);
    }

    @Override
    public String str() {
        return "|";
    }

}
