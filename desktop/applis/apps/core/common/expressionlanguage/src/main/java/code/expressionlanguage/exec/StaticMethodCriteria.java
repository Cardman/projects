package code.expressionlanguage.exec;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.BooleanStruct;

public final class StaticMethodCriteria implements AbstractMethodCriteria {
    @Override
    public BooleanStruct matches(MethodId _id) {
        return BooleanStruct.of(!_id.canAccessParamTypes());
    }
}
