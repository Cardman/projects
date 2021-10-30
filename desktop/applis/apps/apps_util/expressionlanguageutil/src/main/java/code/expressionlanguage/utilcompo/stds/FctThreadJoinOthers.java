package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.CustInitializer;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctThreadJoinOthers implements StdCaller {
    private final CustAliases custAliases;

    public FctThreadJoinOthers(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
        cust_.joinOthers((RunnableContextEl) _cont, _stackCall);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
