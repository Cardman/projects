package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class FctClassGetDeclaredConstructors1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ConstructorMetaInfo> ctors_ = ((ClassMetaInfo)_instance).getConstructorsInfos();
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct vararg_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct params_ = argumentWrappers_.get(1).getValue().getStruct();
        CustList<ConstructorMetaInfo> candidates_ = new CustList<ConstructorMetaInfo>();
        for (ConstructorMetaInfo e: ctors_) {
            ConstructorId id_ = e.getFid();
            if (eqStatic(id_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, vararg_, params_, BooleanStruct.of(false))) {
                candidates_.add(e);
            }
        }
        ArrayStruct str_ = getConstructorsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }
}
