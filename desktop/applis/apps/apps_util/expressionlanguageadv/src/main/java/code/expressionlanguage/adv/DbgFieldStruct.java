package code.expressionlanguage.adv;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public final class DbgFieldStruct extends DbgAbsNodeStruct {
    private final ClassField variable;
    private final Struct eval;
    public DbgFieldStruct(DbgAbsNodeStruct _par, ClassField _v, Struct _w) {
        super(_par);
        this.variable = _v;
        this.eval = _w;
        repr(value());
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return variable.getClassName()+"|"+variable.getFieldName();
    }

    public ClassField getVariable() {
        return variable;
    }
}
