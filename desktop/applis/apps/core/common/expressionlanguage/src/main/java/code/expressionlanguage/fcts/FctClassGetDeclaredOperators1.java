package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.AbstractMethodCriteria;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecOperatorBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctClassGetDeclaredOperators1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct vararg_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct params_ = argumentWrappers_.get(2).getValue().getStruct();
        AbstractMethodCriteria abs_ = _cont.getDefCriteria();
        CustList<MethodMetaInfo> candidates_ = new CustList<MethodMetaInfo>();
        for (ExecOperatorBlock o: _cont.getClasses().getSortedOperators()) {
            MethodId id_ = o.getId();
            if (eqStatic(id_, name_, NullStruct.NULL_VALUE, vararg_, params_, abs_.matches(id_))) {
                MethodMetaInfo met_ = new MethodMetaInfo(o);
                candidates_.add(met_);
            }
        }
        ArrayStruct str_ = getMethodsMeta(_cont, candidates_);
        return new ArgumentWrapper(str_);
    }


}
