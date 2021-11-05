package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class FctMethodGetDeclaredAnonymousLambdaLocalVars0 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Cache cache_ = ((MethodMetaInfo)_instance).getCache();
        if (cache_ != null) {
            StringList localVars_ = cache_.getLocalWrappers();
            return buildStrArr(_cont, localVars_);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

}
