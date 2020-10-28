package code.expressionlanguage.exec;

import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.BooleanStruct;

public interface AbstractMethodCriteria {
    BooleanStruct matches(MethodId _id);
}
