package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class FctThreadMillis implements StdCaller {
    private final CustAliases custAliases;
    private final String id;

    public FctThreadMillis(CustAliases _custAliases, String _i) {
        this.custAliases = _custAliases;
        id = _i;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        long millis_ = ((RunnableContextEl) _cont).getCurrentThreadFactory().millis();
        FctThreadSleep.log(_stackCall,Long.toString(millis_), id);
        return new ArgumentWrapper(new LongStruct(millis_));
    }
}
