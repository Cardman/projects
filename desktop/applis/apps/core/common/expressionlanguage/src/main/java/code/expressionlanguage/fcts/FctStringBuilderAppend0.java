package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilderAppend0 implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        Struct arr_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        append(inst_,ExecCatOperation.getDisplayable(new Argument(arr_),_cont),_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void append(StringBuilderStruct _instance, StringStruct _b, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        _instance.getInstance().append(_b.getInstance());
    }
}
