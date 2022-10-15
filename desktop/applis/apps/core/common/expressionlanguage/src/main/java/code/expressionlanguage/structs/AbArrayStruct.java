package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;

public abstract class AbArrayStruct extends WithoutParentIdStruct {

    private final Struct[] instance;

    protected AbArrayStruct(int _len) {
        instance = new Struct[_len];
    }

    public int getLength() {
        return instance.length;
    }
    public Struct get(int _i) {
        return Argument.getNull(instance[_i]);
    }
    public void set(int _i, Struct _str) {
        instance[_i]=_str;
    }
    public Struct[] getInstance() {
        return instance;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    public abstract String getClassName();
}
