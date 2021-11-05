package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.Struct;

public final class FctCharSeqSplitStrings0 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct one_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        return FctCharSeqSplitStrings1.splitStrings((CharSequenceStruct) _instance, one_, _cont, _stackCall);
    }
}
