package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.EntryBinaryStruct;

public final class FctEntryBinaryValue implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        EntryBinaryStruct inst_ = (EntryBinaryStruct) _instance;
        ArrayStruct bin_ = inst_.getBinary();
        _stackCall.getInitializingTypeInfos().addSensibleField(inst_, bin_);
        return new ArgumentWrapper(bin_);
    }
}
