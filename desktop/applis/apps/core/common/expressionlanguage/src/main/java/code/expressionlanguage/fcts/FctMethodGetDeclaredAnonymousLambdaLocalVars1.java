package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctMethodGetDeclaredAnonymousLambdaLocalVars1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Cache cache_ = ((MethodMetaInfo)_instance).getCache();
        if (cache_ != null) {
            return new ArgumentWrapper(cache_.getLocalWrapperValue(NumParsers.getStringValue(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()),0, _cont, _stackCall));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
