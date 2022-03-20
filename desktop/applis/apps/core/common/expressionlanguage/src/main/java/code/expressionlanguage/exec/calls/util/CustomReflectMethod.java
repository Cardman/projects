package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Argument instance;
    private final Argument array;
    public CustomReflectMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                               Argument _instance, Argument _array) {
        super(_gl, false);
        reflect = _reflect;
        instance = _instance;
        array = _array;
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

}
