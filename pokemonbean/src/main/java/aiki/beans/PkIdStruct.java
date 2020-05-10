package aiki.beans;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.structs.RealInstanceStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class PkIdStruct implements RealInstanceStruct {

    private final Object instance;

    private final String className;

    PkIdStruct(Object _instance, String _className) {
        instance = _instance;
        className = _className;
    }
    static Struct newPkIdStruct(Object _instance,
                                CustList<PkIdStruct> _selected) {
        for (PkIdStruct s: _selected) {
            if (s.instance == _instance) {
                return s;
            }
        }
        return NullStruct.NULL_VALUE;
    }
    @Override
    public Object getInstance() {
        return instance;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
