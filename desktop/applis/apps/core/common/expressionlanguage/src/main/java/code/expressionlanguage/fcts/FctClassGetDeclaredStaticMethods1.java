package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractMethodCriteria;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctClassGetDeclaredStaticMethods1 extends FctClassGetDeclaredStaticMethods {
    @Override
    public ArgumentWrapper stMeth(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
        CustList<MethodMetaInfo> stMethods_ = stMethods(methods_);
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct stat_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct vararg_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct params_ = argumentWrappers_.get(3).getValue().getStruct();
        AbstractMethodCriteria abs_ = _cont.getStaticCriteria();
        CustList<MethodMetaInfo> candidates_ = filterMethods(stMethods_, name_, stat_, vararg_, params_, abs_);
        ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }


}
