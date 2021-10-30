package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ThreadStruct;
import code.util.CustList;

public final class FctThreadEq implements StdCaller {
    private final CustAliases custAliases;

    public FctThreadEq(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        if (one_ instanceof ThreadStruct) {
            if (two_ instanceof ThreadStruct) {
                return new ArgumentWrapper(BooleanStruct.of(((ThreadStruct)one_).getThread().getThread() == ((ThreadStruct)two_).getThread().getThread()));
            }
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        return new ArgumentWrapper(BooleanStruct.of(one_ == two_));
    }
}
