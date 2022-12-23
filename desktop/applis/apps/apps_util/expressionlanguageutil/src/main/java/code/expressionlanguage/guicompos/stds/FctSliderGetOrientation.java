package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.SliderStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class FctSliderGetOrientation implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        SliderStruct inst_ = (SliderStruct) _instance;
        return new ArgumentWrapper(inst_.getOrientation());
    }
}