package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;

public interface LgAdv {

    ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Struct... _args);
    ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args);
}
