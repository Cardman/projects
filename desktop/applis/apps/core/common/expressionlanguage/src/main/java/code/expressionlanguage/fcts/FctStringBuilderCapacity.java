package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilderCapacity implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        return new ArgumentWrapper(new IntStruct(inst_.getInstance().capacity()));
    }

}
