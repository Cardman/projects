package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CustomReflectMethod extends CustomAbstractReflectMethod {

    private final ReflectingType reflect;

    public CustomReflectMethod(ReflectingType _reflect, MethodMetaInfo _gl,
            CustList<Argument> _arguments, boolean _lambda) {
        super(_gl,_arguments,_lambda);
        reflect = _reflect;
    }

    public ReflectingType getReflect() {
        return reflect;
    }
}
