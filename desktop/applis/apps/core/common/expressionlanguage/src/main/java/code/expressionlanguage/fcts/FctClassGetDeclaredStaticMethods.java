package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public abstract class FctClassGetDeclaredStaticMethods extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        if (instanceClass_.isTypeArray()) {
            return new ArgumentWrapper(getMethodsMeta(_cont,new CustList<MethodMetaInfo>()));
        }
        return stMeth(_exit,_cont,_instance,_firstArgs,_stackCall);
    }

    protected abstract ArgumentWrapper stMeth(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall);

    public static CustList<MethodMetaInfo> stMethods(CustList<MethodMetaInfo> _methods) {
        CustList<MethodMetaInfo> stMethods_ = new CustList<MethodMetaInfo>();
        for (MethodMetaInfo e: _methods) {
            if (e.getKind() == MethodAccessKind.INSTANCE) {
                continue;
            }
            stMethods_.add(e);
        }
        return stMethods_;
    }
}
