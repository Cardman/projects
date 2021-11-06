package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecOperatorBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctClassGetDeclaredOperators0 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<MethodMetaInfo> operators_ = new CustList<MethodMetaInfo>();
        for (ExecOperatorBlock o: _cont.getClasses().getSortedOperators()) {
            MethodMetaInfo met_ = new MethodMetaInfo(o);
            operators_.add(met_);
        }
        ArrayStruct str_ = getMethodsMeta(_cont, operators_);
        return new ArgumentWrapper(str_);
    }
}
