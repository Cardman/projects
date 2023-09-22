package code.expressionlanguage.adv;

import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;

public final class DbgRetVarStruct extends DbgAbsNodeStruct {

    private final Struct eval;
    private final String className;
    public DbgRetVarStruct(DbgAbsNodeStruct _par, AbstractWrapper _w) {
        super(_par);
        className = _w.getClassName(_par.getResult());
        eval = _w.getValue();
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return className;
    }

}
