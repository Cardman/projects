package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicRefStruct;
import code.threads.AbstractAtomicRef;

public final class FctAtomicRefGetSet extends FctAtomicAbs {
    @Override
    protected ArgumentWrapper atomic(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicRef<Struct> re_ = ((AtomicRefStruct) _instance).getInstance();
        return new ArgumentWrapper(re_.getAndSet(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()));
    }
}
