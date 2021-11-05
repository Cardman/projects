package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderSame implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct one_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct two_ = argumentWrappers_.get(1).getValue().getStruct();
        return new ArgumentWrapper(BooleanStruct.of(one_ == two_));
    }

}
