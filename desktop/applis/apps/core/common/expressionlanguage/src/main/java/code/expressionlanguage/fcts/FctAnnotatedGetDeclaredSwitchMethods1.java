package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.AnnotatedStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctAnnotatedGetDeclaredSwitchMethods1 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AnnotatedStruct annotated_ = NumParsers.getAnnotated(_instance);
        CustList<MethodMetaInfo> methods_ = AliasReflection.listSwitchMethod(_cont, annotated_);
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct stat_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct vararg_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct params_ = argumentWrappers_.get(3).getValue().getStruct();
        CustList<MethodMetaInfo> candidates_ = AliasReflection.filterMethods(_cont, methods_, name_, stat_, vararg_, params_);
        return new ArgumentWrapper(AliasReflection.getMethodsMeta(_cont, candidates_));
    }
}
