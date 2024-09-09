package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.guicompos.TabbedPaneStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class FctTabbedPaneIndex implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        if (!(arg_ instanceof CustComponentStruct)) {
            return new ArgumentWrapper(new IntStruct(-1));
        }
        TabbedPaneStruct strPan_ = (TabbedPaneStruct) _instance;
        return new ArgumentWrapper(strPan_.index((CustComponentStruct)arg_));
    }
}
