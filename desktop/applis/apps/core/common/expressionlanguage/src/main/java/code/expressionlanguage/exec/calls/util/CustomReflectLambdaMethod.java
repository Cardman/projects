package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectLambdaMethod extends CustomAbstractReflectMethod {

    private final LambdaMethodStruct lambdaMethodStruct;
    private final ReflectingType reflect;

    private final ArgumentListCall array;
    private final int ref;
    public CustomReflectLambdaMethod(LambdaMethodStruct _l, ReflectingType _reflect, MethodMetaInfo _gl,
                                     ArgumentListCall _array, int _r) {
        super(_gl, true);
        lambdaMethodStruct = _l;
        reflect = _reflect;
        array = _array;
        ref = _r;
    }

    public LambdaMethodStruct getLambdaMethodStruct() {
        return lambdaMethodStruct;
    }

    public ReflectingType getReflect() {
        return reflect;
    }

    public ArgumentListCall getArray() {
        return array;
    }

    public int getRef() {
        return ref;
    }
}
