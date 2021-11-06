package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctClassGetDeclaredBlocks1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct stat_ = argumentWrappers_.get(1).getValue().getStruct();
        CustList<MethodMetaInfo> methods_ = instanceClass_.getBlocsInfos();
        CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, name_, stat_, NullStruct.NULL_VALUE,NullStruct.NULL_VALUE);
        ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }


}
