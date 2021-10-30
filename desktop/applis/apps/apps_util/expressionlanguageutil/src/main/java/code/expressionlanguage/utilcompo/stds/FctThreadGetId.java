package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ThreadStruct;
import code.threads.AbstractThread;

public final class FctThreadGetId implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        AbstractThread thread_ = ((ThreadStruct) _instance).getThread();
        return new ArgumentWrapper(new LongStruct(thread_.getId()));
    }
}
