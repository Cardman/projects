package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.PanelStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class FctPanelCount implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        PanelStruct strPan_ = (PanelStruct) _instance;
        return new ArgumentWrapper(new IntStruct(strPan_.getComponentCount()));
    }
}
