package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class FctClassGetDeclaredMethods0 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
        if (instanceClass_.isTypeArray()) {
            String instClassName_ = instanceClass_.getFormatted().getFormatted();
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _cont.getStandards().getContent().getCoreNames().getAliasClone(), new StringList());
            String idCl_ = StringExpUtil.getIdFromAllTypes(instClassName_);
            String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
            return new ArgumentWrapper(getMethodsMeta(_cont,new CustList<MethodMetaInfo>(new MethodMetaInfo(_cont,ret_, id_, instClassName_))));
        }
        ArrayStruct str_ = getMethodsMeta(_cont, methods_);
        return new ArgumentWrapper(str_);
    }
}
