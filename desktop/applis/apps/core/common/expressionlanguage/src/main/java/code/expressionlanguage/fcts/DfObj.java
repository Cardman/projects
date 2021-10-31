package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.SimpleObjectStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class DfObj implements DfInstancer {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new SimpleObjectStruct());
    }

    public static Struct convert(ArgumentListCall _firstArgs) {
        Struct previous_ = NullStruct.NULL_VALUE;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        if (!argumentWrappers_.isEmpty()) {
            previous_ = argumentWrappers_.get(0).getValue().getStruct();
        }
        return previous_;
    }
}
