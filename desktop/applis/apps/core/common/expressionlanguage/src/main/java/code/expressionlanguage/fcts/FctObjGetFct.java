package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctObjGetFct implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (arg_ instanceof AbstractFunctionalInstance) {
            Struct par_ = Argument.getNull(((AbstractFunctionalInstance)arg_).getFunctional());
            _stackCall.getInitializingTypeInfos().addSensibleField(arg_, par_);
            return new ArgumentWrapper(par_);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
