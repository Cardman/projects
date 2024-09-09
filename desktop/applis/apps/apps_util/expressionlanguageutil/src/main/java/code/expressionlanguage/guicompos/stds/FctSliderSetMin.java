package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.SliderStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctSliderSetMin implements StdCaller {

    private final String intro;

    public FctSliderSetMin(String _id) {
        intro = _id;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        SliderStruct inst_ = (SliderStruct) _instance;
        inst_.setMin(_firstArgs.getArgumentWrappers().get(0).getValue(),_stackCall, intro);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}

