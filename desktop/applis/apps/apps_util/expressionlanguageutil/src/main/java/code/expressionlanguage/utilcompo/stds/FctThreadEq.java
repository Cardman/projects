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
import code.expressionlanguage.utilcompo.AbsThreadStruct;
import code.expressionlanguage.utilcompo.CustAliases;
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
        Struct one_ = argumentWrappers_.get(0).getValue();
        Struct two_ = argumentWrappers_.get(1).getValue();
        if (one_ instanceof AbsThreadStruct) {
            if (two_ instanceof AbsThreadStruct) {
                return new ArgumentWrapper(BooleanStruct.of(((AbsThreadStruct)one_).getRunnable() == ((AbsThreadStruct)two_).getRunnable()));
            }
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        return new ArgumentWrapper(BooleanStruct.of(one_ == two_));
    }
}
