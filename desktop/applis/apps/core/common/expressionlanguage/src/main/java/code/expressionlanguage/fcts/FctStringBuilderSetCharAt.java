package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctStringBuilderSetCharAt implements StdCaller {

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringBuilderStruct inst_ = (StringBuilderStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct index_ = argumentWrappers_.get(0).getValue();
        Struct arr_ = argumentWrappers_.get(1).getValue();
        setCharAt(inst_, NumParsers.convertToNumber(index_),NumParsers.convertToChar(arr_),_cont,_stackCall);
        return new ArgumentWrapper(inst_);
    }

    private static void setCharAt(StringBuilderStruct _instance, NumberStruct _index, CharStruct _ch, ContextEl _an, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.failInitEnums();
            return;
        }
        int index_ = _index.intStruct();
        if (index_ < 0 || index_ >= _instance.getInstance().length()) {
            exc(_instance, _an, _stackCall, index_);
            return;
        }
        _instance.getInstance().setCharAt(index_, _ch.getChar());
    }

    static void exc(StringBuilderStruct _instance, ContextEl _an, StackCall _stackCall, int _index) {
        if (_index < 0) {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getBeginMessage(_index), _stackCall)));
        } else {
            _stackCall.setCallingState(new CustomFoundExc(FctUtil.getBadIndex(_an, FctUtil.getEndMessage(_index, ">=", _instance.getInstance().length()), _stackCall)));
        }
    }

}
