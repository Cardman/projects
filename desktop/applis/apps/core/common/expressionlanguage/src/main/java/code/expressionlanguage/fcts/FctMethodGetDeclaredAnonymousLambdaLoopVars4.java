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
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctMethodGetDeclaredAnonymousLambdaLoopVars4 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct index_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct indexSec_ = argumentWrappers_.get(2).getValue().getStruct();
        Cache cache_ = ((MethodMetaInfo)_instance).getCache();
        if (cache_ != null && indexSec_ instanceof NumberStruct) {
            cache_.putLoopValue(NumParsers.getStringValue(name_), NumParsers.convertToNumber(index_).longStruct(), ((NumberStruct) indexSec_).longStruct());
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
