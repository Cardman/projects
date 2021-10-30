package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicLongStruct;
import code.threads.AbstractAtomicLong;

public final class FctAtomicLongGetSet extends FctAtomicAbs {
    @Override
    protected ArgumentWrapper atomic(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
        return new ArgumentWrapper(new LongStruct(re_.getAndSet(((NumberStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).longStruct())));
    }
}
