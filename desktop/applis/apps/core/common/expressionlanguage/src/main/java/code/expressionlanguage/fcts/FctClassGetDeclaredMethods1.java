package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.AbstractMethodCriteria;
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

public final class FctClassGetDeclaredMethods1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ClassMetaInfo instanceClass_ = (ClassMetaInfo) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct stat_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct vararg_ = argumentWrappers_.get(2).getValue().getStruct();
        Struct params_ = argumentWrappers_.get(3).getValue().getStruct();
        CustList<MethodMetaInfo> methods_ = instanceClass_.getMethodsInfos();
        if (instanceClass_.isTypeArray()) {
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _cont.getStandards().getContent().getCoreNames().getAliasClone(), new StringList());
            AbstractMethodCriteria abs_ = _cont.getDefCriteria();
            if (!eqStatic(id_, name_, stat_, vararg_, params_, abs_.matches(id_))) {
                return new ArgumentWrapper(getMethodsMeta(_cont,new CustList<MethodMetaInfo>()));
            }
            String instClassName_ = instanceClass_.getFormatted().getFormatted();
            String idCl_ = StringExpUtil.getIdFromAllTypes(instClassName_);
            String ret_ = getReturnTypeClone(_cont, instClassName_, idCl_);
            return new ArgumentWrapper(getMethodsMeta(_cont,new CustList<MethodMetaInfo>(new MethodMetaInfo(_cont,ret_, id_, instClassName_))));
        }
        CustList<MethodMetaInfo> candidates_ = filterMethods(_cont, methods_, name_, stat_, vararg_, params_);
        ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }


}
