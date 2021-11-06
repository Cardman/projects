package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;

public final class FctStringBuilderSetLength implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        Struct index_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        setLength(inst_, NumParsers.convertToNumber(index_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void setLength(StringBuilderStruct _instance, NumberStruct _newLength, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int newLength_ = _newLength.intStruct();
        if (newLength_ < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getBeginMessage(newLength_), _stackCall)));
            return;
        }
        _instance.getInstance().setLength(newLength_);
    }

}
