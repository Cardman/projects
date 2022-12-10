package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.DimensionStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class FctDimension0 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(arg_ instanceof DimensionStruct)) {
            return new ArgumentWrapper(new DimensionStruct(0,0));
        }
        return new ArgumentWrapper(new DimensionStruct((DimensionStruct)arg_));
    }
}
