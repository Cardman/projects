package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public final class DbgFieldStruct extends DbgAbsNodeStruct {
    private final ClassField variable;
    private final Struct eval;
    public DbgFieldStruct(ContextEl _r, ClassField _v, Struct _w) {
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
        return variable.getClassName()+"|"+variable.getFieldName();
    }

    public ClassField getVariable() {
        return variable;
    }
}
