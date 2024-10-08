package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctErrorToString1 extends FctError {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        ErroneousStruct err_ = getError(_firstArgs.getArgumentWrappers().get(0).getValue(), _cont, _stackCall);
        return new ArgumentWrapper(new StringStruct(err_.getStringRep(_cont, err_.getFullStack())));
    }
}
