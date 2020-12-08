package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CustomReflectLambdaMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    private final Argument instance;
    private final ArgumentListCall array;
    private final Argument right;
    public CustomReflectLambdaMethod(ReflectingType _reflect, MethodMetaInfo _gl,
                                     Argument _instance, ArgumentListCall _array, Argument _right, boolean _lambda) {
        super(_gl, _lambda);
        reflect = _reflect;
        instance = _instance;
        array = _array;
        right = _right;
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

    public Argument getRight() {
        return right;
    }
}
