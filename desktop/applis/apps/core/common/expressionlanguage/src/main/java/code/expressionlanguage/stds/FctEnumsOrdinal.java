package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class FctEnumsOrdinal implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct str_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(str_ instanceof EnumerableStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(ApplyCoreMethodUtil.getNpe(_cont, _stackCall)));
            return new ArgumentWrapper(null);
        } else {
            EnumerableStruct en_ = (EnumerableStruct) str_;
            return new ArgumentWrapper(new IntStruct(en_.getOrdinal()));
        }
    }
}
