package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CommandStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;

public final class FctCommandBinding1 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct str_ = _firstArgs.getArgumentWrappers().get(0).getValue();
        ((CommandStruct)_instance).setBinding(str_);
        return new ArgumentWrapper(null);
    }
}
