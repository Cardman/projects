package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class FctClassArrayGetLength extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        if (!(arg_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(getClassIssue(_cont, arg_.getClassName(_cont), _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ArrayStruct arr_ = (ArrayStruct) arg_;
        int len_ = arr_.getLength();
        return new ArgumentWrapper(new IntStruct(len_));
    }
}
