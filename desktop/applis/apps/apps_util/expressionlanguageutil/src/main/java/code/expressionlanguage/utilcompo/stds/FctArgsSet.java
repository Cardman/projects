package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CollCapacity;
import code.util.StringList;

public final class FctArgsSet implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ArrayStruct arr_ = (ArrayStruct) _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        int size_ = arr_.getLength();
        StringList args_ = new StringList(new CollCapacity(size_));
        for (int i = 0; i < size_; i++) {
            args_.add(NumParsers.getString(arr_.get(i)).getInstance());
        }
        ((RunnableContextEl) _cont).setArgs(args_);
        return new ArgumentWrapper(null);
    }
}
