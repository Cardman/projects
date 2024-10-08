package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicBooleanStruct;
import code.threads.AbstractAtomicBoolean;

public final class FctAtomicBooleanSet extends FctAtomicAbs {
    @Override
    protected ArgumentWrapper atomic(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
        re_.set(BooleanStruct.isTrue(_firstArgs.getArgumentWrappers().get(0).getValue()));
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
