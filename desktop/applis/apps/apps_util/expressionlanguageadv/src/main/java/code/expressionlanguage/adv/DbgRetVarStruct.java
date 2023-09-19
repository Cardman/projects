package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;

public final class DbgRetVarStruct extends DbgAbsNodeStruct {

    private final Struct eval;
    private final String className;
    public DbgRetVarStruct(ContextEl _r, AbstractWrapper _w) {
        super(_r);
        className = _w.getClassName(_r);
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
