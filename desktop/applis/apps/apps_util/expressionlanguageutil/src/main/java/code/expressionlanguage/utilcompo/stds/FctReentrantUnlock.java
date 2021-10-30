package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbstractLockStruct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.AbstractLock;

public final class FctReentrantUnlock extends FctReentrantAbs {

    @Override
    protected ArgumentWrapper lk(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractLock re_ = ((AbstractLockStruct) _instance).getInstance();
        return new ArgumentWrapper(new IntStruct(re_.unlock((RunnableContextEl)_cont)));
    }
}
