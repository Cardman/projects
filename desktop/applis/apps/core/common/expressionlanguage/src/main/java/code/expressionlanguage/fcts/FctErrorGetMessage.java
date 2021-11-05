package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;

public final class FctErrorGetMessage extends FctError {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ErroneousStruct err_ = getError(_instance, _cont, _stackCall);
        return new ArgumentWrapper(err_.getMessage());
    }
}
