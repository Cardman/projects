package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctClassGetDeclaredImplicits1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct params_ = argumentWrappers_.get(0).getValue().getStruct();
        CustList<MethodMetaInfo> methods_ = ((ClassMetaInfo)_instance).getImplicitsInfos();
        CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, params_);
        ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }
}
