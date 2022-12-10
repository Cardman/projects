package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.guicompos.PanelBorderStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctPanelBorder implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct compo_ = argumentWrappers_.get(0).getValue().getStruct();
        if (!(compo_ instanceof CustComponentStruct)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        PanelBorderStruct strPan_ = (PanelBorderStruct) _instance;
        strPan_.add((CustComponentStruct)compo_,argumentWrappers_.get(1).getValue().getStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
