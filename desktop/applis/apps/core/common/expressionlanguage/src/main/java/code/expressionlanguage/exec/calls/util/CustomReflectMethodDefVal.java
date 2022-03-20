package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class CustomReflectMethodDefVal extends CustomAbstractReflectMethod {

    public CustomReflectMethodDefVal(MethodMetaInfo _gl) {
        super(_gl, false);
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.DEFAULT_VALUE;
    }
}
