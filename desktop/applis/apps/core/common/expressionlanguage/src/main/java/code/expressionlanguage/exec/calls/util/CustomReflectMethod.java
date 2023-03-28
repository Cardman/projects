package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Argument instance;
    private final Argument array;
    private final boolean ref;
    public CustomReflectMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                               Argument _instance, Argument _array, boolean _refer) {
        super(_gl, false);
        reflect = _reflect;
        instance = _instance;
        array = _array;
        ref = _refer;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public Argument getInstance() {
        return instance;
    }

    public Argument getArray() {
        return array;
    }

    public boolean isRef() {
        return ref;
    }
}
