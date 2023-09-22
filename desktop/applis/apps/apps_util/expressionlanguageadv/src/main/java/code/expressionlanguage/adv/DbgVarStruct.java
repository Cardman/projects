package code.expressionlanguage.adv;

import code.expressionlanguage.exec.variables.ViewVariable;
import code.expressionlanguage.structs.Struct;

public final class DbgVarStruct extends DbgAbsNodeStruct {

    private final String variable;
    private final String indexClassName;
    private final long index;
    private final int deep;
    private final Struct eval;
    private final String className;
    public DbgVarStruct(DbgAbsNodeStruct _par, ViewVariable _w) {
        super(_par);
        deep = _w.getDeep();
        this.variable = _w.getName();
        className = _w.getClassName();
        indexClassName = _w.getIndexClassName();
        index = _w.getIndex();
        eval = _w.getWrapper().getValue();
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        if (indexClassName.isEmpty()) {
            return deep+"|"+variable+"|"+className;
        }
        return deep+"|"+variable+"|"+className+"(["+index+"])";
    }

}
