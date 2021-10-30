package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicIntegerStruct;
import code.threads.AbstractAtomicInteger;

public final class FctAtomicIntegerSet extends FctAtomicAbs {
    @Override
    protected ArgumentWrapper atomic(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
        re_.set(((NumberStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).intStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
