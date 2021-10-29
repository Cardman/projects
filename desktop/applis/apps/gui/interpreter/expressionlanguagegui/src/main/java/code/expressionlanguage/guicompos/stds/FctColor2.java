package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.ColorStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public class FctColor2 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        int r_ = ((NumberStruct) argumentWrappers_.get(0).getValue().getStruct()).intStruct();
        int g_ = ((NumberStruct) argumentWrappers_.get(1).getValue().getStruct()).intStruct();
        int b_ = ((NumberStruct) argumentWrappers_.get(2).getValue().getStruct()).intStruct();
        return new ArgumentWrapper(new ColorStruct(r_,g_,b_));
    }
}
