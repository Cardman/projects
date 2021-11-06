package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctClassGetDeclaredConstructors0 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ConstructorMetaInfo> ctors_ = ((ClassMetaInfo)_instance).getConstructorsInfos();
        return new ArgumentWrapper(getConstructorsMeta(_cont, ctors_));
    }
}
