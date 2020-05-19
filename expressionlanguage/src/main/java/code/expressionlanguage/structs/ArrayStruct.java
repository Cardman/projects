package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class ArrayStruct implements Struct {

    private final Struct[] instance;

    private final String className;

    public ArrayStruct(Struct[] _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public ArrayStruct swallowCopy() {
        int len_ = instance.length;
        Struct[] inst_ = new Struct[len_];
        for (int i = 0; i < len_; i++) {
            set(i,inst_,instance);
        }
        return new ArrayStruct(inst_,className);
    }
    private static void set(int _i, Struct[] _dest, Struct[] _orig) {
        _dest[_i] = _orig[_i];
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }


    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public String getClassName() {
        return className;
    }

    public Struct[] getInstance() {
        return instance;
    }

}
