package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;

public final class ArrayStruct implements Struct {

    private final Struct[] instance;

    private final String className;

    public ArrayStruct(Struct[] _instance, String _className) {
        instance = _instance;
        className = _className;
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
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    public String getClassName() {
        return className;
    }

    public Struct[] getInstance() {
        return instance;
    }

}
