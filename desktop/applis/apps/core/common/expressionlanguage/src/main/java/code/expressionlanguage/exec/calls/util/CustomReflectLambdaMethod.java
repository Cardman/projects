package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectLambdaMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Argument instance;
    private final ArgumentListCall array;

    public CustomReflectLambdaMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                                     Argument _instance, ArgumentListCall _array, boolean _lambda) {
        super(_gl, _lambda);
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

    public ArgumentListCall getArray() {
        return array;
    }

}
