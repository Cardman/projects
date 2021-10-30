package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AtomicBooleanStruct;
import code.expressionlanguage.utilcompo.AtomicIntegerStruct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.util.CustList;

public final class FctAtomicIntegerCompare extends FctAtomicAbs {
    @Override
    protected ArgumentWrapper atomic(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return new ArgumentWrapper(BooleanStruct.of(re_.compareAndSet(((NumberStruct)one_).intStruct(),((NumberStruct)two_).intStruct())));
    }
}
