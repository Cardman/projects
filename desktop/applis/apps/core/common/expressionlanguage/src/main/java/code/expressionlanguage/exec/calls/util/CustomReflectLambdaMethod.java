package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectLambdaMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Argument instance;
    private final ArgumentListCall array;
    private final int ref;
    public CustomReflectLambdaMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                                     Argument _instance, ArgumentListCall _array, int _r) {
        super(_gl, true);
        reflect = _reflect;
        instance = _instance;
        array = _array;
        ref = _r;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public Argument getInstance() {
        return instance;
    }

    public ArgumentListCall getArray() {
        return array;
    }

    public int getRef() {
        return ref;
    }
}
