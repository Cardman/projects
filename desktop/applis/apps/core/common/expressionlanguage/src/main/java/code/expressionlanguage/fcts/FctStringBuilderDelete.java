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
import code.util.CustList;

public final class FctStringBuilderDelete implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct index_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct arr_ = argumentWrappers_.get(1).getValue().getStruct();
        delete(inst_, NumParsers.convertToNumber(index_),NumParsers.convertToNumber(arr_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void delete(StringBuilderStruct _instance, NumberStruct _start, NumberStruct _end, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return;
        }
        int start_ = _start.intStruct();
        int end_ = _end.intStruct();
        if (start_ < 0 || start_ > _instance.getInstance().length() || start_ > end_) {
            exc(_instance, _an, _stackCall, start_, end_);
            return;
        }
        _instance.getInstance().delete(start_, end_);
    }

    static void exc(StringBuilderStruct _instance, ContextEl _an, StackCall _stackCall, int _start, int _end) {
        if (_start < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getBeginMessage(_start), _stackCall)));
        } else if (_start > _instance.getInstance().length()) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getEndMessage(_start, ">", _instance.getInstance().length()), _stackCall)));
        } else {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getEndMessage(_start, ">", _end), _stackCall)));
        }
    }

}
