package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectMethodDefVal extends CustomAbstractReflectMethod {

    public CustomReflectMethodDefVal(MethodMetaInfo _gl,
                                     boolean _lambda) {
        super(_gl, _lambda);
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.DEFAULT_VALUE;
    }
}
