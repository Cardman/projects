package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class CustomReflectMethodDefVal extends CustomAbstractReflectMethod {

    public CustomReflectMethodDefVal(MethodMetaInfo _gl,
                                     CustList<Argument> _arguments, boolean _lambda) {
        super(_gl,_arguments,_lambda);
    }

    @Override
    public ReflectingType getReflect() {
        return ReflectingType.DEFAULT_VALUE;
    }
}
