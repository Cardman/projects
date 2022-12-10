package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.PanelStruct;
import code.expressionlanguage.guicompos.WindowStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctWindowContentPane implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        WindowStruct inst_ = (WindowStruct) _instance;
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (arg_ instanceof PanelStruct) {
            inst_.setContentPane(((PanelStruct)arg_).getPanel());
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
