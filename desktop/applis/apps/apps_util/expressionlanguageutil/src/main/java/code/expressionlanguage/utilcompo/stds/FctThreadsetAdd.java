package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ThreadSetStruct;
import code.expressionlanguage.utilcompo.ThreadStruct;

public final class FctThreadsetAdd implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
        ins_.add(arg_);
        if (!(arg_ instanceof ThreadStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
