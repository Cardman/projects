package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctClassGetDeclaredStaticMethods0 extends FctClassGetDeclaredStaticMethods {
    @Override
    public ArgumentWrapper stMeth(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
        CustList<MethodMetaInfo> stMethods_ = stMethods(methods_);
        ArrayStruct str_ = getMethodsMeta(_cont, stMethods_);
        return new ArgumentWrapper(str_);
    }
}
