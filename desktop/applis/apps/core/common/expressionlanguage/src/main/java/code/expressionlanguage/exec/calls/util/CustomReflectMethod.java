package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;

public final class CustomReflectMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Struct instance;

    private final ArrayRefState arrRef;
    public CustomReflectMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                               Struct _instance, ArrayRefState _a) {
        super(_gl, false);
        reflect = _reflect;
        instance = _instance;
        arrRef = _a;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public Struct getInstance() {
        return instance;
    }

    public ArrayRefState getArrRef() {
        return arrRef;
    }
}
