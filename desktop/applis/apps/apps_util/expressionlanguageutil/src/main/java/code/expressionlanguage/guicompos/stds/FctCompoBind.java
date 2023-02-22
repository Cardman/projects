package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctCompoBind implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustComponentStruct inst_ = (CustComponentStruct)_instance;
        Struct action_ = ArgumentListCall.toStr(_firstArgs.getArgumentWrappers().get(0).getValue());
        Struct a_ = ArgumentListCall.toStr(_firstArgs.getArgumentWrappers().get(1).getValue());
        Struct b_ = ArgumentListCall.toStr(_firstArgs.getArgumentWrappers().get(2).getValue());
        inst_.registerKeyboardAction(action_,a_,b_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
